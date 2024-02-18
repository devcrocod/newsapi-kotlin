package io.github.devcrocod.news.api.request

import io.github.devcrocod.news.api.NewsApiClient
import io.github.devcrocod.news.api.models.*
import io.github.devcrocod.news.api.response.Response
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

public suspend fun NewsApiClient.sources(body: SourcesRequest.() -> Unit): List<Source> {
    val res: Response<Source> = this.client.get("top-headlines/sources") {
        url { parameters.appendAll(SourcesRequest().apply(body).build().getParameters()) }
    }.body()

    return when (res) {
        is Response.SuccessResponse -> res.data
        is Response.ErrorResponse -> throwNewsApiException(res)
    }
}

internal data class SourcesParams(
    val category: String?,
    val language: String?,
    val country: String?,
) {
    fun getParameters(): Parameters =
        parameters {
            category?.let { append("category", it) }
            language?.let { append("sources", it) }
            country?.let { append("country", it) }
        }
}

public class SourcesRequest internal constructor() {
    public var category: Category? = null
    public var language: Language? = null
    public var country: Country? = null

    internal fun build(): SourcesParams {
        return SourcesParams(category?.toString(), language?.toString(), country?.toString())
    }

    override fun toString(): String {
        return "SourcesRequest(category=$category, language=$language, country=$country)"
    }
}