package io.github.devcrocod.news.api

import io.ktor.client.*
import io.ktor.client.engine.curl.*

internal actual fun getHttpClient(block: HttpClientConfig<*>.() -> Unit): HttpClient =
    HttpClient(Curl, block)