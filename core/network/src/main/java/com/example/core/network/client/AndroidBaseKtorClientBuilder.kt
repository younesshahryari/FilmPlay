package com.example.core.network.client

import com.example.core.network.BuildConfig
import com.example.core.network.util.Logger
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger as KtorLogger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.util.concurrent.TimeUnit

open class AndroidBaseKtorClientBuilder : KtorClientBuilder {

    private companion object {
        const val CONNECT_TIMEOUT_SECONDS = 5L
        const val READ_TIMEOUT_SECONDS = 10L
        const val TAG = "BaseKtorClient"
    }

    override fun buildKtorClient(): HttpClient = HttpClient(OkHttp) {
        engine {
            config {
                connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            }

        }
        installLogging()
        installDefaultRequestHeaders()
        installContentNegotiation()
        installAuth()
    }

    private fun HttpClientConfig<*>.installDefaultRequestHeaders() {
        install(DefaultRequest) {
            contentType(ContentType.Application.Json)
        }
    }

    private fun HttpClientConfig<*>.installAuth() {
        install(Auth) {
            bearer {
                val token = BuildConfig.API_TOKEN
                val bearerTokens = BearerTokens(token, token)
                loadTokens { bearerTokens }
                refreshTokens { bearerTokens }
            }
        }
    }

    private fun HttpClientConfig<*>.installContentNegotiation() {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }
    }

    private fun HttpClientConfig<*>.installLogging() {
        install(Logging) {
            level = LogLevel.ALL
            logger = object : KtorLogger {
                override fun log(message: String) {
                    println("$TAG: $message")
                   // Logger.i(TAG, message)
                }
            }
        }
    }
}