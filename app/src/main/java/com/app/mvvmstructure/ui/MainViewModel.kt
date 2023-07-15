package com.app.mvvmstructure.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.mvvmstructure.model.ProductResponse
import com.app.mvvmstructure.rest.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    init {
        getProduct()
    }

    private var _response: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)
    val response = _response.asStateFlow()

    private fun getProduct() = viewModelScope.launch {
        mainRepository.getProduct().onStart {
            _response.value = ApiState.Loading
        }.catch {
            _response.value = ApiState.Failure(it)
        }.collect {
            if (it.products.isNullOrEmpty()) {
                _response.value = ApiState.Empty
            } else {
                _response.value = ApiState.Success(it)
            }
        }

    }
}

sealed class ApiState {
    class Success(val data: ProductResponse) : ApiState()
    class Failure(val msg: Throwable) : ApiState()
    object Loading : ApiState()
    object Empty : ApiState()
}