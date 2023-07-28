package com.example.kotlincorotinhilt.result

sealed class Networkresult<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Networkresult<T>(data)
    class Error<T>(message: String?, data: T? = null) : Networkresult<T>(data, message)
    class Loading<T> : Networkresult<T>()
}
