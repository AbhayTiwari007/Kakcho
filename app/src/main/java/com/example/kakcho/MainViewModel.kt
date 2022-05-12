package com.example.kakcho

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.example.kakcho.models.IconsIF
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "MainViewModel"

class MainViewModel : ViewModel() {

    var runningJob: Job? = null

    private val _uiList = MutableLiveData<List<IconsIF>>()
    val uiList: LiveData<List<IconsIF>> get() = _uiList

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun makeApiCall(query: String = "arrow") {
        runningJob?.cancel()
        runningJob = viewModelScope.launch {
            _isLoading.value = true
            _uiList.value = emptyList()
            val result = RetrofitService.getInstance().searchIcons(query)
            try {
                if (result.isSuccessful) {
                    _isLoading.value = false
                    Log.d(TAG, "api success: $result")
                    _uiList.value = result.body()!!.icons
                } else {
                    _isLoading.value = false
                    _error.value = "Api Error";
                    Log.d(TAG, "failed ${result.errorBody()}")
                }
            } catch (e: Exception) {
                _isLoading.value = false
                _error.value = "Error $e";
            }
        }
    }

}