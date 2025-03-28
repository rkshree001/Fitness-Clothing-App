package com.fca.fitnessclothingapp.sportscenter


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fca.fitnessclothingapp.R
import com.fca.fitnessclothingapp.notification.NotificationsInboxActivity
import com.fca.fitnessclothingapp.shoppingprocess.ShoppingProcessFragment
import com.fca.fitnessclothingapp.useraccountactivity.UserAccountActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText

class SportsCenterActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var adapter: LocationAdapter
    private lateinit var placesClient: PlacesClient
    private lateinit var searchLocationEditText: TextInputEditText
    private lateinit var searchedAreasRecyclerView: RecyclerView

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1001
    }
    private var locationList = mutableListOf<AutocompletePrediction>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sports_center)
        mapView = findViewById(R.id.mapView)
        searchLocationEditText = findViewById(R.id.searchLocationEditText)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        searchedAreasRecyclerView = findViewById(R.id.searchedAreasRecyclerview)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        checkLocationPermission()

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        bottomNavigationView.itemRippleColor = null
        bottomNavigationView.selectedItemId = R.id.navigation_location

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(android.R.id.content, ShoppingProcessFragment())
                        .addToBackStack(null)
                        .commit()
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                    true
                }
                R.id.navigation_user -> {
                    startActivity(Intent(this, UserAccountActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    true
                }
                R.id.navigation_notification -> {
                    startActivity(Intent(this, NotificationsInboxActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    true
                }
                else -> false
            }
        }

        Places.initialize(applicationContext, "AIzaSyC1FJK-u6g2cLcjeWJBDuwKTTPF2cTpgGE")
        placesClient = Places.createClient(this)



        searchedAreasRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = LocationAdapter(locationList, { selectedPlace ->
            val placeId = selectedPlace.placeId
            val placeRequest = FetchPlaceRequest.newInstance(
                placeId, listOf(Place.Field.LAT_LNG, Place.Field.ADDRESS, Place.Field.RATING, Place.Field.PHONE_NUMBER)
            )

            placesClient.fetchPlace(placeRequest)
                .addOnSuccessListener { response ->
                    val place = response.place
                    val latLng = place.latLng
                    if (latLng != null) {
                        googleMap.clear()
                        googleMap.addMarker(
                            MarkerOptions()
                                .position(latLng)
                                .title(place.address ?: "Selected Location")
                                .snippet("Rating: ${place.rating ?: "N/A"} | Phone: ${place.phoneNumber ?: "N/A"}")
                        )
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
                    }
                }
                .addOnFailureListener { exception ->
                    Log.e("PlacesAPI", "Place not found: ${exception.message}")
                }
        }, placesClient)

        searchedAreasRecyclerView.adapter = adapter

        searchLocationEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                fetchPlaceSuggestions(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                fetchPlaceSuggestions(s.toString())
            }
        })

    }
    private fun fetchPlaceSuggestions(query: String) {
        if (query.isEmpty()) {
            locationList.clear()
            adapter.notifyDataSetChanged()
            return
        }

        val request = FindAutocompletePredictionsRequest.builder()
            .setQuery(query)
            .build()

        placesClient.findAutocompletePredictions(request)
            .addOnSuccessListener { response ->
                locationList.clear()
                locationList.addAll(response.autocompletePredictions)
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.e("PlacesAPI", "Error fetching place predictions: ${exception.message}")
            }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        if (hasLocationPermission()) {
            try {
                googleMap.isMyLocationEnabled = true
                getCurrentLocation()
            } catch (e: SecurityException) {
                e.printStackTrace()
            }
        } else {
            requestLocationPermission()
        }

        googleMap.setOnMapClickListener { latLng ->
            googleMap.clear()
            googleMap.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title("Selected Location")
//                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_location))

            )
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
        }
    }


    private fun checkLocationPermission() {
        if (!hasLocationPermission()) {
            requestLocationPermission()
        } else {
            getCurrentLocation()
        }
    }

    private fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    private fun getCurrentLocation() {
        if (!hasLocationPermission() || !::googleMap.isInitialized) return

        try {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                location?.let {
                    val currentLatLng = LatLng(it.latitude, it.longitude)
                    googleMap.addMarker(MarkerOptions().position(currentLatLng).title("You are here"))
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
                }
            }
//            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
//                location?.let {
//                    val currentLatLng = LatLng(it.latitude, it.longitude)
//                    googleMap.addMarker(
//                        MarkerOptions()
//                            .position(currentLatLng)
//                            .title("You are here")
//                            .icon(getBitmapDescriptorFromVector(R.drawable.icon_location)) // Custom Icon
//                    )
//
//                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
//                }
//            }

        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    private fun getBitmapDescriptorFromVector(resId: Int): BitmapDescriptor {
        val vectorDrawable = ContextCompat.getDrawable(this, resId) ?: return BitmapDescriptorFactory.defaultMarker()
        vectorDrawable.setBounds(0, 0, vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)

        val bitmap = Bitmap.createBitmap(vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)

        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                try {
                    googleMap.isMyLocationEnabled = true
                    getCurrentLocation()
                } catch (e: SecurityException) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onResume() { super.onResume(); mapView.onResume() }
    override fun onPause() { super.onPause(); mapView.onPause() }
    override fun onDestroy() { super.onDestroy(); mapView.onDestroy() }
    override fun onLowMemory() { super.onLowMemory(); mapView.onLowMemory() }
}
