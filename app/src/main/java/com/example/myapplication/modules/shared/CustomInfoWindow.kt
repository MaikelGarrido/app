package com.example.myapplication.modules.shared

import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.modules.map.data.models.Stop
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.infowindow.InfoWindow

class CustomInfoWindow(mapView: MapView, private val stop: Stop) : InfoWindow(R.layout.custom_info_window, mapView) {
    override fun onOpen(item: Any?) {
        val marker = item as Marker
        val idStopTv = mView.findViewById<TextView>(R.id.idStop)
        val codeTv = mView.findViewById<TextView>(R.id.code)
        val nameTv = mView.findViewById<TextView>(R.id.name)
        val latTv = mView.findViewById<TextView>(R.id.latitude)
        val lonTv = mView.findViewById<TextView>(R.id.longitude)
        val disTv = mView.findViewById<TextView>(R.id.dis)

        idStopTv.text = "ID: ${stop.id.trim()}"
        codeTv.text = "Código ${stop.code.trim()}"
        nameTv.text = "Nombre: ${stop.name.trim()}"
        latTv.text = "Latitud: ${stop.lat.toString().trim()}"
        lonTv.text = "Longitud: ${stop.lon.toString().trim()}"
        disTv.text = "Dis: ${stop.dist.toString().trim()}"
    }

    override fun onClose() { }
}