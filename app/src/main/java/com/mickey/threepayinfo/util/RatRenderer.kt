package com.mickey.threepayinfo.util

import android.content.Context
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.mickey.threepayinfo.R
import com.mickey.threepayinfo.data.RatModel

class RatRenderer(
    private val context: Context,
    map: GoogleMap,
    clusterManager: ClusterManager<RatModel>
) : DefaultClusterRenderer<RatModel>(context, map, clusterManager) {

    private val iconLow: BitmapDescriptor by lazy {
        BitmapHelper.vectorToBitmap(context, R.drawable.ic_rat,
            ContextCompat.getColor(context, R.color.severityLow))
    }
    private val iconMed: BitmapDescriptor by lazy {
        BitmapHelper.vectorToBitmap(context, R.drawable.ic_rat,
            ContextCompat.getColor(context, R.color.severityMed))
    }
    private val iconHigh: BitmapDescriptor by lazy {
        BitmapHelper.vectorToBitmap(context, R.drawable.ic_rat,
            ContextCompat.getColor(context, R.color.severityHigh))
    }

    private fun iconFor(item: RatModel): BitmapDescriptor = when {
        (item.severity ?: 1) >= 4 -> iconHigh
        (item.severity ?: 1) >= 3 -> iconMed
        else -> iconLow
    }

    override fun onBeforeClusterItemRendered(item: RatModel, markerOptions: MarkerOptions) {
        markerOptions.title(item.name).icon(iconFor(item))
    }

    override fun onClusterItemRendered(clusterItem: RatModel, marker: Marker) {
        marker.tag = clusterItem
    }
}
