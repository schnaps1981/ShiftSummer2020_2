import com.shiftsummer2020_2.screens.main.data.entities.apimodel.WeatherParams
import java.io.Serializable

data class City(
    val city: Pair<String, WeatherParams?> //название города + погода
) : Serializable