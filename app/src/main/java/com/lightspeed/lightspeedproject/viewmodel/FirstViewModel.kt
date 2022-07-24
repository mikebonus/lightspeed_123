package com.lightspeed.lightspeedproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lightspeed.lightspeedproject.api.implementation.FirstParsingImpl
import com.lightspeed.lightspeedproject.data.Lightspeed
import com.lightspeed.lightspeedproject.data.LightspeedDatabase
import com.lightspeed.lightspeedproject.repository.FirstRepository
import com.lightspeed.lightspeedproject.ui.FirstActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(
    db: LightspeedDatabase
) : ViewModel() {

    private val dataRepository = FirstRepository(db, FirstParsingImpl())
    private val lightspeedLiveData = MutableLiveData<List<Lightspeed>>()
    val lightspeed: LiveData<List<Lightspeed>> = lightspeedLiveData

    init {
        viewModelScope.launch {
            // if online...
            if (FirstActivity.onlineStatus == "online") {
                val lightspeed123 = withContext(Dispatchers.IO) {
                    dataRepository.getLightspeedFromAPI()
                }
                lightspeedLiveData.value = lightspeed123

            // if offline...
            } else {
                val lightspeed123 = withContext(Dispatchers.IO) {
                    dataRepository.getLightspeedFromDB()
                }
                lightspeedLiveData.value = lightspeed123
            }
        }
    }
}