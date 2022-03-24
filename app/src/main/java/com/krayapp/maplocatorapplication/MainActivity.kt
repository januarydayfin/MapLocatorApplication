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
    private val viewBinding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mapInit()
    }

    private fun mapInit() {
        MapKitFactory.setApiKey("4f9515fa-8a48-48c0-a24b-8dd64f08f47d")
        MapKitFactory.initialize(this)
        val mapView: MapView = viewBinding.mapview

        mapView
            .map
            .move(
                CameraPosition(
                Point(55.751574, 37.573856),
                11.0f, 0.0f, 0.0f),
                Animation(Animation.Type.SMOOTH,0f),null)
    }
}