package io.github.devcrocod.news.api.request

import io.github.devcrocod.news.api.NewsApiClient
import io.github.devcrocod.news.api.models.*
import io.github.devcrocod.news.api.response.Response
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.datetime.LocalDateTime
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

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

        val phrase = validateQ(phrase)
        val sources = validateSource(sources)
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
        return "EverythingRequest(question=$phrase, searchIn=\"$searchIn\", sources=$sources, domains=$domains, from=$from, to=$to, language=$language, sortBy=$sortBy, pageSize=$pageSize, page=$page)"
    }
}