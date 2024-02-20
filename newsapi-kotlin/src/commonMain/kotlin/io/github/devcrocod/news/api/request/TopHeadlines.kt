package io.github.devcrocod.news.api.request

import io.github.devcrocod.news.api.NewsApiClient
import io.github.devcrocod.news.api.exception.ParameterException
import io.github.devcrocod.news.api.models.*
import io.github.devcrocod.news.api.response.Response
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Retrieves the top headlines based on the specified parameters.
 *
 * - [country][SourcesRequest.country] - Find sources that display news in a specific country.
 * Possible options see in [Country].
 * - [category][SourcesRequest.category] - Find sources that display news of this category.
 * Possible options see in [Category].
 * - [sources][EverythingRequest.sources] - [Identifiers][SourceId] for the news sources or blogs you want headlines from.
 * - [phrase][EverythingRequest.phrase] - Keywords or phrases to search for in the article title and body. Also see [EverythingRequest.buildPhrase] for advanced search.
 * - [pageSize][EverythingRequest.pageSize] - The number of results to return per page.
 * Default: `100`.
 * Maximum: `100`.
 * - [page][EverythingRequest.page] - Use this to page through the results.
 * Default: `100`.
 *
 * Sample:
 * ```
 * // Get top business headlines from Germany
 * client.topHeadlines {
 *     country = Country.DE
 *     category = Category.BUSINESS
 * }
 * ```
 *
 * @param body The block of code that configures the [TopHeadlinesRequest] object.
 * @return A list of [Article] objects representing the top headlines.
 * @throws NewsApiException when an unexpected error occurs.
 * @throws ApiKeyException when the API key is missing, invalid, disabled, or exhausted.
 * @throws ParameterException when the request parameters are invalid or missing.
 * @throws RateLimitedException when the API rate limit has been reached.
 * @throws SourceException when there are issues with the specified news source.
 */
public suspend fun NewsApiClient.topHeadlines(body: TopHeadlinesRequest.() -> Unit): List<Article> {
    val res: Response<Article> = this.client.get("top-headlines") {
        url { parameters.appendAll(TopHeadlinesRequest().apply(body).build().getParameters()) }
    }.body()

    return when (res) {
        is Response.SuccessResponse -> res.data
        is Response.ErrorResponse -> throwNewsApiException(res)
    }
}

internal data class TopHeadlinesParams(
    val country: String?,
    val category: String?,
    val sources: String?,
    val q: String?,
    val pageSize: Int?,
    val page: Int?
) {
    fun getParameters(): Parameters =
        parameters {
            country?.let { append("country", it) }
            category?.let { append("category", it) }
            sources?.let { append("sources", it) }
            q?.let { append("q", it) }
            pageSize?.let { append("pageSize", it.toString()) }
            page?.let { append("page", it.toString()) }
        }
}

/**
 * Builder of request to search top-headlines.
 *
 * - [country][SourcesRequest.country] - Find sources that display news in a specific country.
 * Possible options see in [Country].
 * - [category][SourcesRequest.category] - Find sources that display news of this category.
 * Possible options see in [Category].
 * - [sources][EverythingRequest.sources] - [Identifiers][SourceId] for the news sources or blogs you want headlines from.
 * - [phrase][EverythingRequest.phrase] - Keywords or phrases to search for in the article title and body. Also see [EverythingRequest.buildPhrase] for advanced search.
 * - [pageSize][EverythingRequest.pageSize] - The number of results to return per page.
 * Default: `100`.
 * Maximum: `100`.
 * - [page][EverythingRequest.page] - Use this to page through the results.
 * Default: `100`.
 *
 * Sample:
 * ```
 * // Get top business headlines from Germany
 * client.topHeadlines {
 *     country = Country.DE
 *     category = Category.BUSINESS
 * }
 * ```
 *
 */
public class TopHeadlinesRequest internal constructor() {
    public var country: Country? = null
    public var category: Category? = null
    public var sources: SourceId? = null
    public var phrase: String? = null
    public var pageSize: Int? = null
    public var page: Int? = null

    /**
     * Builds a phrase for advanced search using the provided lambda function [builderAction].
     * - [append()][KeywordBuilder.append] - Add new keywords or phrases in search
     * - [matchPhrase()][KeywordBuilder.matchPhrase] - Add string for exact phrase match.
     * - [+][KeywordBuilder.unaryPlus] - Prepend words or phrases that must appear
     * - [-][KeywordBuilder.unaryMinus] - Prepend words that must not appear
     * - [and][KeywordBuilder.and]
     * - [or][KeywordBuilder.or]
     * - [not][KeywordBuilder.not]
     *
     * @param builderAction The lambda function that takes an instance of [KeywordBuilder] and allows customization of the phrase.
     * @return The built phrase as a [String].
     */
    @OptIn(ExperimentalContracts::class)
    public fun buildPhrase(builderAction: KeywordBuilder.() -> Unit): String {
        contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }
        return KeywordBuilder().apply(builderAction).toString()
    }

    internal fun build(): TopHeadlinesParams {
        require(!(phrase.isNullOrEmpty() && country == null && sources == null && category == null)) {
            """
                Required parameters are missing, the scope of your search is too broad.
                Please set any of the following required parameters and try again: 
                `phrase`, `country`, `sources`, `category`.
            """.trimIndent()
        }

        validateQ(phrase)
        validateSource(sources)
        return TopHeadlinesParams(
            country?.toString(),
            category?.toString(),
            sources?.toString(),
            phrase,
            pageSize,
            page
        )
    }

    private fun validateQ(value: String?) {
        value?.let { if (it.length > 500) throw ParameterException("The `phrase` parameter length exceeds the limit of 500 characters.") }
    }

    private fun validateSource(value: SourceId?) {
        value?.let { if (it.set.size > 20) throw ParameterException("The number of `sources` exceeds the limit of 20.") }
    }

    override fun toString(): String {
        return "TopHeadlinesRequest(country=$country, category=$category, sources=$sources, phrase=$phrase, pageSize=$pageSize, page=$page)"
    }
}