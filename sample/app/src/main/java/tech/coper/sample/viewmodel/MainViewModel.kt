package tech.coper.sample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import tech.coper.sample.Profile
import tech.coper.sample.Repository


class MainViewModel() : ViewModel(){

    private val repository = Repository()

    private val _profile = MutableStateFlow<Profile?>(null)
    val profile: Flow<Profile?> = _profile

    fun onResume(){
        viewModelScope.launch {
            try {
                val res  = repository.getProfile(isForce = false)
                _profile.value = res
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }

    fun forceStore(){
        viewModelScope.launch {
            try {
                repository.delete()
                repository.store(Profile("Trump","Donald","aaaaaa"))

            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }
}