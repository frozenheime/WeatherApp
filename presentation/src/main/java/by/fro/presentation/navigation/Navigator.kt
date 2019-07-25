package by.fro.presentation.navigation

import android.app.Activity
import android.content.Intent
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import android.view.View
import by.fro.presentation.home.HomeActivity
import by.fro.presentation.weather.detail.WeatherActivity

class Navigator {

    companion object {
        private val EXTRA_WEATHER = "${WeatherActivity::class.java.`package`.name}.extra.WEATHER"
    }

    fun navigateToHome(activity: Activity) {
        val intent = Intent(activity, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        activity.startActivity(intent)
    }

    fun navigateToWeather(activity: Activity, weather: String, sharedViews: Array<Pair<View, String>>) {
        val intent = Intent(activity, WeatherActivity::class.java)
        intent.putExtra(EXTRA_WEATHER, weather)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, *sharedViews).toBundle()
        ActivityCompat.startActivity(activity, intent, options)
    }

    fun getCityName(activity: Activity) = activity.intent.getStringExtra(EXTRA_WEATHER)
}