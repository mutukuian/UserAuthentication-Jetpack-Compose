package com.example.recruittutorial.commoncomponents

data class LogInState(
    val isLoading: Boolean = false,
    val isSuccess: String = "",
    val isError: String = ""
)
