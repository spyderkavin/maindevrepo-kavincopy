package com.example.mapboxsdktest3

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.Constants.MessageNotificationKeys.TAG
import com.mapbox.geojson.Point
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.annotation.generated.CircleAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat



var mapView: MapView? = null
//Timeline Reset
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mapView = findViewById(R.id.mapView)
        //Style link commeneted here for conveinence purposes
        //val STYLE_URL = "mapbox://styles/spyderkavin/cli1anwx900v901poaa5e9cxg"
        mapView?.getMapboxMap()?.loadStyleUri(getString(R.string.STYLE_URL))
    }
    // [START ask_post_notifications]
    // Declare the launcher at the top of your Activity/Fragment:
        private val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission(),
        ) { isGranted: Boolean ->
            if (isGranted) {
                // FCM SDK (and your app) can post notifications.
            } else {
                // TODO: Inform user that that your app will not show notifications.
            }
        }

        private fun askNotificationPermission() {
            // This is only necessary for API level >= 33 (TIRAMISU)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    // FCM SDK (and your app) can post notifications.
                } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                    // TODO: display an educational UI explaining to the user the features that will be enabled
                    //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                    //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                    //       If the user selects "No thanks," allow the user to continue without notifications.
                } else {
                    // Directly ask for the permission
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        }
    // [END ask_post_notifications]

    @SuppressLint("Lifecycle")
    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    @SuppressLint("Lifecycle")
    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    @SuppressLint("Lifecycle")
    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    @SuppressLint("Lifecycle")
    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }
}