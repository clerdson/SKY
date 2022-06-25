package com.clerdsonjuca.sky.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.viewModelScope

import com.clerdsonjuca.sky.model.All
import com.clerdsonjuca.sky.repositori.Repository
import com.clerdsonjuca.sky.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application):
    AndroidViewModel(application) {

    var myResponse: MutableLiveData<Response<List<All>>> = MutableLiveData()

    private val _response: MutableLiveData<NetworkResult<List<All>>> = MutableLiveData()
    val response: LiveData<NetworkResult<List<All>>> = _response
    fun getAll() =
        viewModelScope.launch {
            repository.getDog().collect{
            _response.value = it
            }

        }
    }





