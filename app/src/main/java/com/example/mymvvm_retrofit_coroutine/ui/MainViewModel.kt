package com.example.mymvvm_retrofit_coroutine.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymvvm_retrofit_coroutine.repository.MainRepository
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.Coin
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.NetworkState
import kotlinx.coroutines.*

class MainViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
    get() = _errorMessage





    val coinList = MutableLiveData<List<Coin>?>()

    var job: Job? = null




    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun getAllMovies() {
        Log.d("Thread Outside", Thread.currentThread().name)


        viewModelScope.launch {
            Log.d("Thread Inside", Thread.currentThread().name)
            when (val response = mainRepository.getAllCoin()) {
                is NetworkState.Success -> {
                    coinList.postValue(response.data)
                }
                is NetworkState.Error -> {
                    if (response.response.code() == 401) {
                        //movieList.postValue(NetworkState.Error())
                    } else {
                        //movieList.postValue(NetworkState.Error)
                    }
                }
            }
        }
    }

    private fun onError(message: String) {
        _errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}