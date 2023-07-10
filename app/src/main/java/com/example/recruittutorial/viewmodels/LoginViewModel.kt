package com.example.recruittutorial.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recruittutorial.commoncomponents.LogInState
import com.example.recruittutorial.commoncomponents.Resource
import com.example.recruittutorial.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

   private  val _logInState = Channel<LogInState>()
            val logInState = _logInState.receiveAsFlow()

    fun loginUser(email:String, password: String) = viewModelScope.launch {
        repository.loginUser(email, password).collect{
            result->
            when(result){
                is Resource.Success ->{
                    _logInState.send(LogInState(isSuccess = "Log In Successfully "))
                }
                is Resource.Loading ->{
                    _logInState.send(LogInState(isLoading = true))
                }
                is Resource.Error->{
                    _logInState.send(LogInState(isError = result.message.toString()))
                }
            }
        }
    }

}