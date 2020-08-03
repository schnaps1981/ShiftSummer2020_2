package com.example.server

import DatabaseFactory
import com.example.server.repository.CitiesRepository
import exapmle.com.common.CreateCityDto
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.*
import io.ktor.util.KtorExperimentalAPI
import java.net.URI

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@KtorExperimentalAPI
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        gson {
        }
    }

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

    val repository = CitiesRepository()

    routing {
        route("/cities") {
            get {
                val cities = repository.getAll()
                call.respond(cities)
            }
            post {
                val city = call.receive<CreateCityDto>()
                repository.add(city)
                call.respond(HttpStatusCode.OK)
            }
            delete {
                val id = call.request.queryParameters["id"]?.toLong()
                if (id == null)
                    call.respond(HttpStatusCode.NotFound)
                else {
                    repository.delete(id)
                    call.respond(HttpStatusCode.OK)
                }
            }
        }
    }
}

