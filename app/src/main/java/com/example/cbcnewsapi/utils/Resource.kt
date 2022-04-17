package com.codinginflow.simplecachingexample.util

// GENERIC
// <T>: we can pass any data here..
sealed class Resource<T>(
    val data: T? = null,
    val error: Throwable? = null
) {

    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)                        // '?' --> 'progress-bar' only shows up at 'opening the app for the first time!'..
    class Error<T> (throwable: Throwable, data: T? = null) : Resource<T>(data, throwable)   // null --> show an empty-list (we can show error msg with the cached-data)..
}