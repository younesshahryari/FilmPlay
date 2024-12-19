package com.aparat.androidinterview.persentation.extensions

import arrow.retrofit.adapter.either.networkhandling.CallError
import arrow.retrofit.adapter.either.networkhandling.HttpError
import arrow.retrofit.adapter.either.networkhandling.IOError
import arrow.retrofit.adapter.either.networkhandling.UnexpectedCallError

fun CallError?.toHumanReadableText(): String {
    return when (this) {
        is HttpError -> "Http Error message! Tap to retry!"
        is IOError -> "Io Error message! Tap to retry!"
        is UnexpectedCallError -> "Unexpected call error message! Tap to retry!"
        else -> "Unknown Error! Tap to retry!"
    }
}
