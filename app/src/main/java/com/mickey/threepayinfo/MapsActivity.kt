package com.mickey.threepayinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.maps.android.clustering.ClusterManager
import com.mickey.threepayinfo.data.RatModel
import com.mickey.threepayinfo.util.RatRenderer
import java.io.BufferedReader
import java.io.InputStreamReader

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private val ratList = mutableListOf<RatModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        ratList.addAll(loadRatData())

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val clusterManager = ClusterManager<RatModel>(this, googleMap)
        clusterManager.renderer = RatRenderer(this, googleMap, clusterManager)

        clusterManager.markerCollection.setInfoWindowAdapter(object : GoogleMap.InfoWindowAdapter {
            override fun getInfoWindow(marker: Marker): View? = null
            override fun getInfoContents(marker: Marker): View? {
                val rat = marker.tag as? RatModel ?: return null
                val view = LayoutInflater.from(this@MapsActivity)
                    .inflate(R.layout.marker_info_contents, null)
                view.findViewById<TextView>(R.id.text_view_title).text = rat.name
                view.findViewById<TextView>(R.id.text_view_address).text =
                    "${rat.district}　${rat.date}"
                view.findViewById<TextView>(R.id.text_view_severity).text =
                    "嚴重程度：${"★".repeat(rat.severity ?: 1)}${"☆".repeat(5 - (rat.severity ?: 1))}"
                view.findViewById<TextView>(R.id.text_view_description).text = rat.description
                return view
            }
        })

        clusterManager.addItems(ratList)
        clusterManager.cluster()

        googleMap.setOnCameraIdleListener { clusterManager.onCameraIdle() }
        googleMap.setOnMarkerClickListener(clusterManager)

        val taipei = LatLng(25.0478, 121.5319)
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(taipei, 12f))
    }

    private fun loadRatData(): List<RatModel> {
        val json = BufferedReader(InputStreamReader(resources.openRawResource(R.raw.rat_data)))
            .readText()
        val type = object : TypeToken<List<RatModel>>() {}.type
        return Gson().fromJson(json, type)
    }
}
