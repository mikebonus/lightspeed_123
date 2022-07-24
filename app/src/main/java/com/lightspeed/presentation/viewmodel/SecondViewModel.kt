package com.lightspeed.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lightspeed.data.implementation.SecondParsingImpl
import com.lightspeed.domain.model.Lightspeed
import com.lightspeed.data.local.LightspeedDatabase
import com.lightspeed.domain.repository.SecondRepository
import com.lightspeed.presentation.ui.SecondActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SecondViewModel @Inject constructor(
    db: LightspeedDatabase
) : ViewModel() {

    private val dataRepository = SecondRepository(db, SecondParsingImpl())
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