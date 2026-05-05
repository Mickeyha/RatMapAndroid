package com.mickey.threepayinfo.data

import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName
import com.google.maps.android.clustering.ClusterItem

data class RatModel(
    @SerializedName("name")        val name: String?,
    @SerializedName("address")     val address: String?,
    @SerializedName("district")    val district: String?,
    @SerializedName("lat")         val lat: Double?,
    @SerializedName("lng")         val lng: Double?,
    @SerializedName("date")        val date: String?,
    @SerializedName("severity")    val severity: Int?,
    @SerializedName("description") val description: String?
) : ClusterItem {
    override fun getTitle(): String = name ?: ""
    override fun getSnippet(): String = address ?: ""
    override fun getPosition(): LatLng = LatLng(lat ?: 0.0, lng ?: 0.0)
    override fun getZIndex(): Float? = null
}
