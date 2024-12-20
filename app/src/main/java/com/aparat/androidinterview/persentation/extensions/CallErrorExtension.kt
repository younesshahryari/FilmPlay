package com.aparat.androidinterview.persentation.extensions

import android.util.Log
import arrow.retrofit.adapter.either.networkhandling.CallError
import arrow.retrofit.adapter.either.networkhandling.HttpError
import arrow.retrofit.adapter.either.networkhandling.IOError
import arrow.retrofit.adapter.either.networkhandling.UnexpectedCallError

fun CallError?.toHumanReadableText(): String {
    print()
    return when (this) {
        is HttpError -> "Http Error message! Tap to retry!"
        is IOError -> "Io Error message! Tap to retry!"
        is UnexpectedCallError -> "Unexpected call error message! Tap to retry!"
        else -> "Unknown Error! Tap to retry!"
    }
}

fun CallError?.print() {
    when (this) {
        is HttpError -> Log.i("CallError", this.message)
        is IOError -> Log.i("CallError", this.cause.message ?: "IOError")
        is UnexpectedCallError -> Log.i("CallError", this.cause.message ?: "UnexpectedCallError")
        else -> "Unknown Error! Tap to retry!"
    }
}

