package io.github.devcrocod.news.api

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

internal expect fun getHttpClient(block: HttpClientConfig<*>.() -> Unit = {}): HttpClient


/**
 * A client for accessing news data from the News API.
 *
 * @property token The API token used for authorization.
 */
public class NewsApiClient(token: String) {
    internal val client: HttpClient = getHttpClient {
        install(Auth) {
            bearer {
                loadTokens { BearerTokens(token, "") }
            }
        }

        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }

        defaultRequest {
            url("https://newsapi.org/v2/") // base url
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.HEADERS
            sanitizeHeader { header -> header == HttpHeaders.Authorization }
        }

        expectSuccess = true
    }
}
