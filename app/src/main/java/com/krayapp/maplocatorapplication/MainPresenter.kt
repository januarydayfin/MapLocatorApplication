package com.krayapp.maplocatorapplication

import android.location.LocationManager

interface MainPresenter {
    fun onAttach()
    fun getLocation()
    fun makeMark()
    fun markDetails()
    fun setLocationManager(locationManager: LocationManager)
}