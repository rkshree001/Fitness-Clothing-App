package com.fca.fitnessclothingapp.sportscenter

import com.google.android.libraries.places.api.model.AutocompletePrediction

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fca.fitnessclothingapp.R
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient


class LocationAdapter(
    private val locations: List<AutocompletePrediction>,
    private val onItemClick: (AutocompletePrediction) -> Unit,
    private val placesClient: PlacesClient
) : RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val locationName: TextView = itemView.findViewById(R.id.locationNameTextView)
        val fullAddress: TextView = itemView.findViewById(R.id.fullAddress)
        val ratings: TextView = itemView.findViewById(R.id.ratings)
        val phoneNumber: TextView = itemView.findViewById(R.id.phoneNumber)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_location, parent, false)
        return LocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val location = locations[position]
        holder.locationName.text = location.getPrimaryText(null).toString()


        val placeId = location.placeId
        val request = FetchPlaceRequest.newInstance(
            placeId, listOf(Place.Field.ADDRESS, Place.Field.RATING, Place.Field.PHONE_NUMBER)
        )

        placesClient.fetchPlace(request)
            .addOnSuccessListener { response ->
                val place = response.place
                holder.fullAddress.text = place.address ?: "Address not available"
                holder.ratings.text = "Rating: ${place.rating ?: "N/A"}"
                holder.phoneNumber.text = "Phone: ${place.phoneNumber ?: "N/A"}"
            }
            .addOnFailureListener {
                holder.fullAddress.text = "Address not available"
                holder.ratings.text = "Rating: N/A"
                holder.phoneNumber.text = "Phone: N/A"
            }

        holder.itemView.setOnClickListener {
            onItemClick(location)
        }
    }

    override fun getItemCount(): Int {
        return locations.size
    }
}
