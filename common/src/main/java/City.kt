import com.shiftsummer2020_2.screens.main.data.entities.apimodel.WeatherParams
import java.io.Serializable

data class City(
    val id : Long,
    val city: String //название города
) : Serializable