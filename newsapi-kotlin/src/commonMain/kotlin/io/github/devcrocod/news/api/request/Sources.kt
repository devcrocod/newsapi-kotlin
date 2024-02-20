package io.github.devcrocod.news.api.request

import io.github.devcrocod.news.api.NewsApiClient
import io.github.devcrocod.news.api.models.*
import io.github.devcrocod.news.api.response.Response
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

/**
 * Fetches a list of news sources.
 *
 * - [category][SourcesRequest.category] - Find sources that display news of this category.
 * Possible options see in [Category].
 * - [language][SourcesRequest.language] - Find sources that display news in a specific language.
 * Possible options see in [Language].
 * - [country][SourcesRequest.country] - Find sources that display news in a specific country.
 * Possible options see in [Country].
 *
 * Sample:
 * ```
 * // fetch all sources
 * client.sources()
 *
 * // fetch business sources from Germany and USA
 * client.sources {
 *     category = Category.BUSINESS
 *     country = Country.DE and Country.US
 * }
 * ```
 *
 * @param body a lambda function that allows configuring the [SourcesRequest].
 * @return a list of [Source] objects representing the news sources.
 * @throws NewsApiException when an unexpected error occurs.
 * @throws ApiKeyException when the API key is missing, invalid, disabled, or exhausted.
 * @throws ParameterException when the request parameters are invalid or missing.
 * @throws RateLimitedException when the API rate limit has been reached.
 * @throws SourceException when there are issues with the specified news source.
 */
public suspend fun NewsApiClient.sources(body: SourcesRequest.() -> Unit = {}): List<Source> {
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

/**
 * Builder of request to fetch sources.
 *
 * - [category][SourcesRequest.category] - Find sources that display news of this category.
 * Possible options see in [Category].
 * - [language][SourcesRequest.language] - Find sources that display news in a specific language.
 * Possible options see in [Language].
 * - [country][SourcesRequest.country] - Find sources that display news in a specific country.
 * Possible options see in [Country].
 *
 * Sample:
 * ```
 * // fetch all sources
 * client.sources()
 *
 * // fetch business sources from Germany and USA
 * client.sources {
 *     category = Category.BUSINESS
 *     country = Country.DE and Country.US
 * }
 * ```
 */
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