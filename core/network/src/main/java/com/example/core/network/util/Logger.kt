package com.example.core.network.util

import android.util.Log

object Logger {

    fun i(tag: String, message: String) {
        Log.i(message, tag)
    }

    fun d(tag: String, message: String) {
        Log.d(message, tag)
    }

    fun w(tag: String, message: String) {
        Log.w(message, tag)
    }

    fun e(tag: String, message: String, throwable: Throwable? = null) {
        Log.e(message, tag, throwable)
    }

}