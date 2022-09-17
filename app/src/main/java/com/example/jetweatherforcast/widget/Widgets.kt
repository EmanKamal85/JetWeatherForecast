package com.example.jetweatherforcast.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.jetweatherforcast.R
import com.example.jetweatherforcast.model.WeatherItem
import com.example.jetweatherforcast.utils.formatDateTime
import com.example.jetweatherforcast.utils.formatDayName
import com.example.jetweatherforcast.utils.formatDecimal

@Composable
fun SevenDaysForecastItem(weather: WeatherItem) {
    val dayImageUrl = "https://openweathermap.org/img/wn/${weather.weather[0].icon}.png"
    Surface(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth(),
        //.clip(RoundedCornerShape(33.dp, 0.dp, 33.dp, 33.dp)),
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        elevation = 4.dp,

        ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {

            Text(text = formatDayName(weather.dt))

            WeatherStateImage(imageUrl = dayImageUrl)

            Surface(modifier = Modifier
                .padding(2.dp),
                shape = CircleShape,
                //.clip(RoundedCornerShape(20.dp)),
                color = Color(0xffffc400)
            ) {
                Text(text = weather.weather[0].description,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(4.dp))

            }

            Text(text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                    color = Color.Blue.copy(alpha = 0.7f),
                    fontWeight = FontWeight.SemiBold
                )
                ){
                    append(formatDecimal(weather.temp.max) +"°")
                }

                withStyle(
                    SpanStyle(
                    color = Color.LightGray
                )
                ){
                    append(formatDecimal(weather.temp.min) +"°")
                }
            })

//        Text(text = "${formatDecimal(weather.temp.max)} °",
//            style = MaterialTheme.typography.caption,
//            fontWeight = FontWeight.Bold,
//            color = Color(0xFF2441FF))
//
//        Text(text = "${formatDecimal(weather.temp.min)} °",
//            style = MaterialTheme.typography.caption,
//        color = Color.LightGray)
        }
    }

}

@Composable
fun SunriseAndSunSet(weather: WeatherItem) {

    Row(modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(modifier = Modifier.padding(4.dp),
            verticalAlignment = Alignment.CenterVertically) {

            Image(painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "Sunrise Icon",
                modifier = Modifier
                    .padding(4.dp)
                    .size(20.dp))

            Text(text = formatDateTime(weather.sunrise),
                style = MaterialTheme.typography.caption,
            )
        }

        Row(modifier = Modifier.padding(4.dp),
            verticalAlignment = Alignment.CenterVertically) {

            Image(painter = painterResource(id = R.drawable.sunset),
                contentDescription = "Sunset Icon",
                modifier = Modifier
                    .padding(4.dp)
                    .size(20.dp))

            Text(text = formatDateTime(weather.sunset),
                style = MaterialTheme.typography.caption)
        }
    }

}

@Composable
fun HumidityWindPressureRow(weather: WeatherItem) {
    Row(modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {

        Row(modifier = Modifier.padding(4.dp)) {
            Image(painter = painterResource(id = R.drawable.humidity),
                contentDescription = "Humidity Icon",
                modifier = Modifier.size(20.dp))

            Text(text = "${weather.humidity} %",
                style = MaterialTheme.typography.caption)
        }

        Row(modifier = Modifier.padding(4.dp)) {
            Image(painter = painterResource(id = R.drawable.pressure),
                contentDescription = "Pressure Icon",
                modifier = Modifier.size(20.dp))

            Text(text = "${weather.humidity} psi",
                style = MaterialTheme.typography.caption)
        }

        Row() {
            Icon(painter = painterResource(id = R.drawable.wind),
                contentDescription = "Wind Icon",
                modifier = Modifier.size(20.dp))

            Text(text = "${weather.humidity} mph",
                style = MaterialTheme.typography.caption)
        }

    }
}

@Composable
fun WeatherStateImage(imageUrl: String) {
    Image(painter = rememberImagePainter(data = imageUrl),
        contentDescription = "Image URL",
        modifier = Modifier.size(80.dp))
}
