package com.example.metroapp.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.metroapp.domain.model.Trip
import com.example.metroapp.domain.usecase.GetTripStationsUseCase

class MetroViewModel(
    private val getTripStationsUseCase: GetTripStationsUseCase = GetTripStationsUseCase()
) : ViewModel() {

    private val _recentTrips = mutableStateListOf<Trip>()
    val recentTrips: List<Trip> = _recentTrips

    fun getTripStations(start: String, end: String): List<String> {
        return getTripStationsUseCase(start, end)
    }

    fun addTrip(start: String, end: String) {
        val newTrip = Trip(start, end)
        if (_recentTrips.contains(newTrip)) {
            _recentTrips.remove(newTrip)
        }
        _recentTrips.add(0, newTrip)
        
        // Keep only last 3
        while (_recentTrips.size > 3) {
            _recentTrips.removeAt(_recentTrips.size - 1)
        }
    }
}
