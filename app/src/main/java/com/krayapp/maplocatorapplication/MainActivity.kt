package com.krayapp.maplocatorapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.krayapp.maplocatorapplication.databinding.ActivityMainBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView

class MainActivity : AppCompatActivity() {

    private lateinit var mapView:MapView
    private val TARGET_LOCATION = Point(59.945933, 30.320045)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey("4f9515fa-8a48-48c0-a24b-8dd64f08f47d")
        setContentView(R.layout.activity_main)
        mapView = findViewById(R.id.mapview)
        mapInit()
    }

    private fun mapInit() {
        MapKitFactory.initialize(this)
        mapView
            .map
            .move(
                CameraPosition(
                TARGET_LOCATION,
                15.0f, 0.0f, 0.0f),
                Animation(Animation.Type.SMOOTH,0f),null)
    }

    override fun onStart() {
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
        super.onStart()
    }

    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }
}