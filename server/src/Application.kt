package com.example.server

import DatabaseFactory
import com.example.server.repository.CitiesRepository
import com.example.server.repository.WeatherAPIRepository
import exapmle.com.common.CityDto
import io.ktor.application.Application
import io.ktor.application.ApplicationEnvironment
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import io.ktor.util.KtorExperimentalAPI
import kotlinx.coroutines.*
import okhttp3.logging.HttpLoggingInterceptor
import java.net.URI
import kotlin.concurrent.fixedRateTimer

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@KtorExperimentalAPI
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
            serializeNulls()
        }
    }

    initDataBase(environment)

    val citiesRepository = CitiesRepository()

    val httpClient = initHttpClient()
    initParserSchedule(httpClient, citiesRepository)

    routing {
        route("/cities") {
            get {
                val cities = citiesRepository.getAll()
                call.respond(cities)
            }
            post {
                val city = call.receive<CityDto>()
                citiesRepository.add(city)
                call.respond(HttpStatusCode.OK)
            }
            delete {
                val id = call.request.queryParameters["id"]?.toLong()
                if (id == null)
                    call.respond(HttpStatusCode.NotFound)
                else {
                    citiesRepository.delete(id)
                    call.respond(HttpStatusCode.OK)
                }
            }
            patch {
                val id = call.request.queryParameters["id"]?.toLong()
                val createCityDto = call.receive<CityDto>()
                if (id == null)
                    call.respond(HttpStatusCode.NotFound)
                else {
                    citiesRepository.update(id = id, createCityDto = createCityDto)
                    call.respond(HttpStatusCode.OK)
                }

            }
        }
    }
}

fun initHttpClient(): HttpClient = HttpClient(OkHttp)
{
    install(JsonFeature) {
        serializer = GsonSerializer()
    }
    engine {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        addNetworkInterceptor(loggingInterceptor)
    }
}

fun initParserSchedule(httpClient: HttpClient, citiesRepository: CitiesRepository) {
    fixedRateTimer(name = "WeatherParser", daemon = false, initialDelay = 0L, period = 3 * 60 * 1000) //minutes * seconds * mills
    {
        val weatherAPIRepository = WeatherAPIRepository(httpClient, citiesRepository)
        CoroutineScope(Dispatchers.IO).launch {
            weatherAPIRepository.updateWeather()
        }
    }
}

@KtorExperimentalAPI
fun initDataBase(environment: ApplicationEnvironment) {
    val dbUri = URI(environment.config.property("db.jdbcUrl").getString())

    val username: String = dbUri.userInfo.split(":")[0]
    val password: String = dbUri.userInfo.split(":")[1]
    val dbUrl = ("jdbc:postgresql://${dbUri.host}:${dbUri.port}${dbUri.path}")

    DatabaseFactory(
        dbUrl = dbUrl,
        dbPassword = password,
        dbUser = username
    ).apply {
        init()
    }
}

