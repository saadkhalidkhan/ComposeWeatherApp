package com.droidgeeks.slweatherapp.data.location

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import com.droidgeeks.slweatherapp.domain.location_interface.WeatherLocation
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume


class DefaultWeatherLocation @Inject constructor(
    private val locationClient: FusedLocationProviderClient,
    private val application: Application,
) : WeatherLocation {

    override suspend fun getCurrentLocation(): Location? {
        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (!hasAccessCoarseLocationPermission || !hasAccessFineLocationPermission) {
            Log.d("Location", "No Permission Granted")
        } else if (!isLocationEnabled(application)) {
            Log.d("Location", "No GPS Granted")
        }

        return suspendCancellableCoroutine { cont ->
            locationClient.lastLocation.apply {
                if (isComplete) {
                    if (isSuccessful) cont.resume(result)
                    else cont.resume(null)
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener {
                    cont.resume(it)
                }
                addOnFailureListener {
                    cont.cancel(it.cause)
                }
                addOnCanceledListener {
                    cont.cancel()
                }
            }
        }
    }

    private fun isLocationEnabled(application: Application): Boolean {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            val provider: String = Settings.Secure.getString(
                application.contentResolver,
                Settings.Secure.LOCATION_PROVIDERS_ALLOWED
            )
            provider != ""
        } else {
            val locationManager = application.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
            val gps = locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
            val network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            gps || network
        }
    }

}