package io.github.devcrocod.news.api.request

import io.github.devcrocod.news.api.NewsApiClient
import io.github.devcrocod.news.api.exception.ParameterException
import io.github.devcrocod.news.api.models.*
import io.github.devcrocod.news.api.response.Response
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.datetime.LocalDateTime
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Retrieves a list of articles based on the specified search criteria.
 *
 * - [phrase][EverythingRequest.phrase] - Keywords or phrases to search for in the article title and body. Also see [EverythingRequest.buildPhrase] for advanced search.
 * - [searchIn][EverythingRequest.searchIn] - The fields to restrict your phrase [search][Search] to. Default: all fields are searched.
 * - [sources][EverythingRequest.sources] - [Identifiers][SourceId] for the news sources or blogs you want headlines from.
 * - [domains][EverythingRequest.domains] - A comma-seperated string of domains (e.g. bbc.co.uk, techcrunch.com, engadget.com) to restrict the search to.
 * - [from][EverythingRequest.from] - A date and optional time for the _oldest_ article allowed.
 * Default: the oldest, according to your plan.
 * - [to][EverythingRequest.to] - A date and optional time for the _newest_ article allowed.
 * Default: the newest, according to your plan.
 * - [language][EverythingRequest.language] -
 * The 2-letter ISO-639-1 code of the language you want to get headlines for.
 * See [Language].
 * Default: all languages.
 * - [sortBy][EverythingRequest.sortBy] - The order to sort the articles in.
 * Possible options: [SortBy.RELEVANCY], [SortBy.POPULARITY], [SortBy.PUBLISHED_AT].
 * Default: [SortBy.PUBLISHED_AT].
 * - [pageSize][EverythingRequest.pageSize] - The number of results to return per page.
 * Default: `100`.
 * Maximum: `100`.
 * - [page][EverythingRequest.page] - Use this to page through the results.
 * Default: `100`.
 *
 * Sample:
 * ```
 * client.everything {
 *     phrase = buildPhrase {
 *         append("bitcoin") and "crypto" and "ethereum"
 *     }
 *     searchIn = Search.Title
 *     language = Language.EN and Language.DE
 * }
 * ```
 *
 * @param body A lambda function that allows customization of the search parameters using an instance of [EverythingRequest].
 * @return A list of [Article] objects that match the search criteria.
 * @throws NewsApiException when an unexpected error occurs.
 * @throws ApiKeyException when the API key is missing, invalid, disabled, or exhausted.
 * @throws ParameterException when the request parameters are invalid or missing.
 * @throws RateLimitedException when the API rate limit has been reached.
 * @throws SourceException when there are issues with the specified news source.
 */
public suspend fun NewsApiClient.everything(body: EverythingRequest.() -> Unit): List<Article> {
    val res: Response<Article> = this.client.get("everything") {
        url { parameters.appendAll(EverythingRequest().apply(body).build().getParameters()) }
    }.body()

    return when (res) {
        is Response.SuccessResponse -> res.data
        is Response.ErrorResponse -> throwNewsApiException(res)
    }
}

internal data class EverythingParams(
    val q: String?,
    val searchIn: String?,
    val sources: String?,
    val domains: String?,
    val from: String?,
    val to: String?,
    val language: String?,
    val sortBy: String?,
    val pageSize: Int?,
    val page: Int?
) {
    fun getParameters(): Parameters =
        parameters {
            q?.let { append("q", it) }
            searchIn?.let { append("searchIn", it) }
            sources?.let { append("sources", it) }
            domains?.let { append("domains", it) }
            from?.let { append("from", it) }
            to?.let { append("to", it) }
            language?.let { append("language", it) }
            sortBy?.let { append("sortBy", it) }
            pageSize?.let { append("pageSize", it.toString()) }
            page?.let { append("page", it.toString()) }
        }
}

/**
 * Builder of request to search for everything.
 *
 * This class provides various parameters that can be set to customize the search query.
 *
 * @property phrase Keywords or phrases to search for in the article title and body.
 * Also see [EverythingRequest.buildPhrase] for advanced search.
 * @property searchIn The fields to restrict your phrase [search][Search] to.
 * Default: all fields are searched.
 * @property sources _[Identifiers][SourceId] for the news sources or blogs you want headlines from.
 * @property domains A comma-seperated string of domains (e.g. bbc.co.uk, techcrunch.com, engadget.com) to restrict the search to.
 * @property from A date and optional time for the _oldest_ article allowed.
 * Default: the oldest, according to your plan.
 * @property to A date and optional time for the _newest_ article allowed.
 * Default: the newest, according to your plan.
 * @property language The 2-letter ISO-639-1 code of the language you want to get headlines for.
 * See [Language].
 * Default: all languages.
 * @property sortBy The order to sort the articles in.
 * Possible options: [SortBy.RELEVANCY], [SortBy.POPULARITY], [SortBy.PUBLISHED_AT].
 * Default: [SortBy.PUBLISHED_AT].
 * @property pageSize The number of results to return per page.
 * Default: `100`.
 * Maximum: `100`.
 * @property page Use this to page through the results.
 * Default: `100`.
 *
 * Sample:
 * ```
 * client.everything {
 *     phrase = buildPhrase {
 *         append("bitcoin") and "crypto" and "ethereum"
 *     }
 *     searchIn = Search.Title
 *     language = Language.EN and Language.DE
 * }
 * ```
 */
public class EverythingRequest internal constructor() {
    public var phrase: String? = null
    public var searchIn: Search? = null
    public var sources: SourceId? = null
    public var domains: String? = null
    public var from: LocalDateTime? = null
    public var to: LocalDateTime? = null
    public var language: Language? = null
    public var sortBy: SortBy? = null
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

    internal fun build(): EverythingParams {
        require(!(phrase.isNullOrEmpty() && searchIn == null && sources == null && domains.isNullOrEmpty())) {
            """
                Required parameters are missing, the scope of your search is too broad.
                Please set any of the following required parameters and try again: 
                `phrase`, `searchIn`, `sources`, `domains`.
            """.trimIndent()
        }

        validateQ(phrase)
        validateSource(sources)
        return EverythingParams(
            phrase,
            searchIn?.toString(),
            sources?.toString(),
            domains,
            from?.toString(),
            to?.toString(),
            language?.toString(),
            sortBy?.option,
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
        return "EverythingRequest(question=$phrase, searchIn=\"$searchIn\", sources=$sources, domains=$domains, from=$from, to=$to, language=$language, sortBy=$sortBy, pageSize=$pageSize, page=$page)"
    }
}