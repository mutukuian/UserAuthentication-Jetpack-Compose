package com.example.recruittutorial.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recruittutorial.commoncomponents.LogInState
import com.example.recruittutorial.commoncomponents.Resource
import com.example.recruittutorial.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {

    private  val _registerState = Channel<LogInState>()
    val registerState= _registerState.receiveAsFlow()

    fun registerUser(email:String, password: String) = viewModelScope.launch {
        repository.registerUser(email, password).collect{
                result->
            when(result){
                is Resource.Success ->{
                    _registerState.send(LogInState(isSuccess = "Registration  Successfully "))
                }
                is Resource.Loading ->{
                    _registerState.send(LogInState(isLoading = true))
                }
                is Resource.Error->{
                    _registerState.send(LogInState(isError = result.message.toString()))
                }
            }
        }
    }
}