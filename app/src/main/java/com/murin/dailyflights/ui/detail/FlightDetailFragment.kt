package com.murin.dailyflights.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.annotations.PolylineOptions
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.geometry.LatLngBounds
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.murin.dailyflights.R
import com.murin.dailyflights.databinding.FragmentFlightDetailBinding

class FlightDetailFragment : Fragment() {
    private val kiwiPackageName = "com.skypicker.main"
    private lateinit var binding: FragmentFlightDetailBinding
    private lateinit var mapView: MapView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFlightDetailBinding.inflate(inflater, container, false)

        with(binding) {
            flight = arguments?.getParcelable("flight")
            kiwiClickListener = View.OnClickListener {
                val kiwiIntent = Intent(Intent.ACTION_VIEW, Uri.parse(binding.flight?.deepLink ?: ""))
                startActivity(kiwiIntent)
            }

            activity?.packageManager?.let { pm ->
                if (pm.getInstalledPackages(0).any { it.packageName == kiwiPackageName }) {
                    ivShowInKiwi.setImageDrawable(pm.getApplicationIcon(kiwiPackageName))
                    llShowInKiwi.visibility = VISIBLE
                }
            }

            mapView = mvFlightMap.apply {
                onCreate(savedInstanceState)
            }
            mapView.getMapAsync { mapbox ->
                showFlightOnMap(mapbox)
            }
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun showFlightOnMap(mapboxMap: MapboxMap) {
        binding.flight?.run {
            val departureCoors = route?.get(0)?.run {
                LatLng(latFrom.toDouble(), lngFrom.toDouble())
            } ?: LatLng(0.0, 0.0)

            val arrivalCoors = route?.get(0)?.run {
                LatLng(latTo.toDouble(), lngTo.toDouble())
            } ?: LatLng(0.0, 0.0)

            val departureMarkerOptions = MarkerOptions()
                .position(departureCoors)
                .title("${cityFrom ?: ""} (${flyFrom ?: ""})")
                .snippet("Departure: ${timeString(departureTime)}")

            val arrivalMarkerOptions = MarkerOptions()
                .position(arrivalCoors)
                .title("${cityTo ?: ""} (${flyTo ?: ""})")
                .snippet("Arrival: ${timeString(arrivalTime)}")

            mapboxMap.addMarker(departureMarkerOptions)
            mapboxMap.addMarker(arrivalMarkerOptions)

            mapboxMap.addPolyline(PolylineOptions()
                .addAll(listOf(departureCoors, arrivalCoors))
                .color(resources.getColor(R.color.flight_polyline_color))
                .width(3f)
                .alpha(0.75f)
            )

            val latLngBounds = LatLngBounds.Builder()
                .include(departureCoors)
                .include(arrivalCoors)
                .build()
            mapboxMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 50))
        }
    }
}
