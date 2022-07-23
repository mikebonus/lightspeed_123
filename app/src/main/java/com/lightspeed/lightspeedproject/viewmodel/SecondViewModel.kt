package com.lightspeed.lightspeedproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lightspeed.lightspeedproject.api.ParsingImpl
import com.lightspeed.lightspeedproject.data.Lightspeed
import com.lightspeed.lightspeedproject.data.LightspeedDatabase
import com.lightspeed.lightspeedproject.repository.SecondRepository
import com.lightspeed.lightspeedproject.ui.SecondActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SecondViewModel @Inject constructor(
    db: LightspeedDatabase
) : ViewModel() {

    private val dataRepository = SecondRepository(db, ParsingImpl())
    private val lightspeedLiveData = MutableLiveData<List<Lightspeed>>()
    val lightspeed: LiveData<List<Lightspeed>> = lightspeedLiveData

    init {
        viewModelScope.launch {
            // if online...
            if (SecondActivity.onlineStatus == "online") {
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