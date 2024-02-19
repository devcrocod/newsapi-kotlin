package io.github.devcrocod.news.api

import io.ktor.client.*
import io.ktor.client.engine.darwin.*

internal actual fun getHttpClient(block: HttpClientConfig<*>.() -> Unit): HttpClient =
    HttpClient(Darwin, block)