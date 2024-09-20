package com.example.myapplication.modules.map.presentation

import android.Manifest
import android.location.Location
import android.view.MotionEvent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.example.myapplication.modules.map.data.models.Stop
import com.example.myapplication.modules.shared.CustomDialogMessage
import com.example.myapplication.modules.shared.CustomInfoWindow
import com.example.myapplication.modules.shared.CustomProgressBar
import com.example.myapplication.modules.shared.CustomTopAppBar
import com.example.myapplication.utils.PERMISSIONS
import com.example.myapplication.utils.getCurrentLocation
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

@Composable
fun MapScreen(
    state: MapState,
    onEvent: (MapEvent) -> Unit = {},
    onExit: () -> Unit = {}
) {
    val context = LocalContext.current
    var userLocation by remember { mutableStateOf<Location?>(null) }
    var hasPermission by remember { mutableStateOf(false) }

    val requestMultiplePermissionsLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            val fineLocationGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
            val coarseLocationGranted = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false
            hasPermission = fineLocationGranted || coarseLocationGranted
        }
    )

    LaunchedEffect(hasPermission) {
        if (!hasPermission) {
            requestMultiplePermissionsLauncher.launch(PERMISSIONS)
        } else {
            getCurrentLocation(context) { location -> userLocation = location }
        }
    }

    LaunchedEffect(userLocation != null) {
        if (userLocation != null) {
            onEvent(
                MapEvent.LoadStops(
                    latitude = userLocation!!.latitude,
                    longitude = userLocation!!.longitude,
                    radius = 1000,
                )
            )
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            hasPermission = false
            userLocation = null
        }
    }

    when {
        state.isLoading -> { CustomProgressBar() }

        state.errorMessage != null -> {
            CustomDialogMessage(
                message = state.errorMessage,
                onDismiss = { onExit() }
            )
        }

        state.exception != null -> {
            CustomDialogMessage(
                message = state.exception.message ?: "Hubo un problema",
                onDismiss = { onExit() }
            )
        }

        userLocation != null -> {
            MapViewOsmDroid(
                userLocation = userLocation!!,
                stops = state.stops,
                onExit = onExit
            )
        }

    }
}

@Composable
fun MapViewOsmDroid(
    modifier: Modifier = Modifier,
    userLocation: Location,
    stops: List<Stop>,
    onExit: () -> Unit = { }
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        Configuration.getInstance().userAgentValue = context.packageName
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Mapa",
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                onClickNavigation = onExit
            )
        }
    ) { paddingValues ->
        AndroidView(
            modifier = modifier.padding(paddingValues),
            factory = { context ->
            MapView(context).apply {
                setTileSource(TileSourceFactory.MAPNIK)
                setMultiTouchControls(true)

                val mapController = controller
                mapController.setZoom(15.0)

                val startPoint = GeoPoint(userLocation.latitude, userLocation.longitude)
                //val startPointSimulate = GeoPoint(4.60971, -74.08175)
                mapController.setCenter(startPoint)

                stops.forEach { stop ->
                    val marker = Marker(this)
                    marker.position = GeoPoint(stop.lat, stop.lon)
                    marker.title = stop.name
                    marker.setAnchor(1.0f, 1.0f)

                    marker.infoWindow = CustomInfoWindow(mapView = this, stop = stop)

                    marker.setOnMarkerClickListener { clickedMarker, mapView ->
                        mapView.overlays.forEach { overlay ->
                            if (overlay is Marker && overlay.infoWindow.isOpen && overlay != clickedMarker) {
                                overlay.infoWindow.close()
                            }
                        }
                        if (!clickedMarker.infoWindow.isOpen) { clickedMarker.showInfoWindow() }
                        true
                    }

                    setOnTouchListener { _, event ->
                        if (event.action == MotionEvent.ACTION_UP) {
                            overlays.forEach { overlay ->
                                if (overlay is Marker && overlay.infoWindow.isOpen) {
                                    overlay.infoWindow.close()
                                }
                            }
                        }
                        false
                    }

                    overlays.add(marker)
                }
            }
        })
    }
}

@Preview
@Composable
private fun MapScreenPreview() {
    MapScreen(
        state = MapState()
    )
}