package com.krayapp.maplocatorapplication


import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.core.app.ActivityCompat


class Presenter(private val view:MainView):MainPresenter {

    private var locationManager: LocationManager? = null
    private val locationListener:LocationListener = object :LocationListener{
        private var changed:Boolean = false
        override fun onLocationChanged(location: Location) {
            if(!changed)
            view.updateLocation(location.latitude, location.longitude)
            changed = true
        }
    }
    override fun onAttach() {

    }

    override fun getLocation() {
        view.checkPermission()
        locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L,0F, locationListener)
    }

    override fun makeMark() {
        TODO("Not yet implemented")
    }

    override fun markDetails() {
        TODO("Not yet implemented")
    }

    override fun setLocationManager(locationManager: LocationManager) {
        this.locationManager = locationManager
    }

}