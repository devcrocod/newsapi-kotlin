package io.github.devcrocod.news.api.request

import io.github.devcrocod.news.api.NewsApiClient
import io.github.devcrocod.news.api.models.*
import io.github.devcrocod.news.api.response.Response
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

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

public class TopHeadlinesRequest internal constructor() {
    public var country: Country? = null
    public var category: Category? = null
    public var sources: SourceId? = null
    public var phrase: String? = null
    public var pageSize: Int? = null
    public var page: Int? = null

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

        val phrase = validateQ(phrase)
        val sources = validateSource(sources)
        return TopHeadlinesParams(
            country?.toString(),
            category?.toString(),
            sources?.toString(),
            phrase,
            pageSize,
            page
        )
    }

    private fun validateQ(value: String?): String? {
        return value?.let {
            if (it.length > 500)
                it.substring(0..500)// TODO("add logger")
            else
                it
        }
    }

    private fun validateSource(value: SourceId?): SourceId? {
        return value?.let {
            if (it.set.size > 20)
                it // TODO("add logger, take 20")
            else
                it
        }
    }

    override fun toString(): String {
        return "TopHeadlinesRequest(country=$country, category=$category, sources=$sources, phrase=$phrase, pageSize=$pageSize, page=$page)"
    }
}