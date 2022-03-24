package com.krayapp.maplocatorapplication

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.krayapp.maplocatorapplication.databinding.ActivityMainBinding
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.user_location.UserLocationLayer

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var mapView: MapView
    private val viewBinding: ActivityMainBinding by viewBinding()
    private lateinit var mapKit: MapKit
    private lateinit var userLocationLayer: UserLocationLayer
    private var currentLocation:Point? = null

    private val API_KEY = "4f9515fa-8a48-48c0-a24b-8dd64f08f47d"
    private val presenter: MainPresenter = Presenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onAttach()
        MapKitFactory.setApiKey(API_KEY)
        setContentView(R.layout.activity_main)
        mapView = viewBinding.mapview
        mapKit = MapKitFactory.getInstance()
        initClickListeners()
        setLocationManager()
        mapInit()
        userLocation()
    }

    private fun userLocation() {
        userLocationLayer = mapKit.createUserLocationLayer(mapView.mapWindow)
        userLocationLayer.isVisible = true
        userLocationLayer.isAutoZoomEnabled = true
        userLocationLayer.isHeadingEnabled = true

    }

    private fun setLocationManager() {
        presenter.setLocationManager(getSystemService(LOCATION_SERVICE) as LocationManager)
    }

    private fun mapInit() {
        MapKitFactory.initialize(this)
        presenter.getLocation()
    }

    private fun initClickListeners() {
        viewBinding.findLocationButton.setOnClickListener {
            if(currentLocation != null){
                mapView.map.move(CameraPosition(currentLocation!!, 18f, 0.0f, 0.0f))
            }else{
                Toast.makeText(this,"Please wait", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        mapKit.onStart()
        mapView.onStart()
        super.onStart()
    }

    override fun onStop() {
        mapView.onStop()
        mapKit.onStop()
        super.onStop()
    }


    override fun updateLocation(lat: Double, long: Double) {
        currentLocation = Point(lat,long)
        mapView.map.move(CameraPosition(Point(lat, long), 18f, 0.0f, 0.0f))
    }
}