package by.fro.data.remote.location


import android.content.Context
import android.location.*
import android.os.Bundle
import android.util.Log
import by.fro.data.remote.model.CityRemoteModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import io.reactivex.Observable
import java.io.IOException
import java.lang.NullPointerException
import java.util.*
import android.location.Geocoder




class LocationAccessObject(val context: Context) {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private fun getLastKnownLocation(): Location? {

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        var lastLocation: Location? = null
        try {
            fusedLocationClient.lastLocation
                .addOnSuccessListener {
                    // Got last known location. In some rare situations this can be null.
                    lastLocation = it
                }
        } catch (e: SecurityException){
            e.printStackTrace()
        }
        return lastLocation

    }

    private fun updateLocation(): Location?{

        var newLocation: Location? = null
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        try {
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0f, object : LocationListener{
            override fun onLocationChanged(location: Location?) {
                newLocation = location
                Log.e("onLocationChanged", "onLocationChanged invoked")
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                Log.e("onStatusChanged", "onStatusChanged invoked")

            }

            override fun onProviderEnabled(provider: String?) {
                Log.e("onProviderEnabled", "onProviderEnabled invoked: $provider")

            }

            override fun onProviderDisabled(provider: String?) {
                Log.e("onProviderDisabled", "onProviderDisabled invoked: $provider")

            }
        })
        } catch (e: SecurityException){
            e.printStackTrace()
        }

        return newLocation
    }

    fun getCity(): Observable<CityRemoteModel> {

        var location = getLastKnownLocation()

        if (location == null) location = updateLocation()


        val lat: Double = location?.latitude ?: 0.0
        val lng: Double = location?.longitude ?: 0.0

        val geocoder = Geocoder(context, Locale.ENGLISH)
        var cityName: String? = null
        var countryCode: String? = null

        location?.let {
            try {
                val address = geocoder.getFromLocation(lat, lng, 1)
                cityName = address[0].getAddressLine(0)
                countryCode = address[0].countryCode
            } catch (e: IOException){
                e.printStackTrace()
            } catch (e: NullPointerException) {
                e.printStackTrace()
            }
        }

        return Observable.just(CityRemoteModel(cityName ?: "London", countryCode ?: "GBR", //set London to default
            current = true,
            favorite = true //add to favorites automatically
        ))
    }
}