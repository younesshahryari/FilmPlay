package com.example.core.network.client

import io.ktor.client.HttpClient

interface KtorClientBuilder {
    fun buildKtorClient(): HttpClient
}