package com.lightspeed.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lightspeed.data.implementation.FirstParsingImpl
import com.lightspeed.domain.model.Lightspeed
import com.lightspeed.data.local.LightspeedDatabase
import com.lightspeed.domain.repository.FirstRepository
import com.lightspeed.presentation.ui.FirstActivity
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