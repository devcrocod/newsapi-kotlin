package io.github.devcrocod.news.api.models

import io.github.devcrocod.news.api.exception.*
import io.github.devcrocod.news.api.response.Response
import kotlinx.serialization.Serializable

@Suppress("NOTHING_TO_INLINE")
internal inline fun throwNewsApiException(response: Response.ErrorResponse): Nothing = when (response.code) {
    ErrorCode.ApiKeyDisabled, ErrorCode.ApiKeyExhausted,
    ErrorCode.ApiKeyInvalid, ErrorCode.ApiKeyMissing -> throw ApiKeyException(response.message)
    ErrorCode.ParameterInvalid, ErrorCode.ParametersMissing -> throw ParameterException(response.message)
    ErrorCode.RateLimited -> throw RateLimitedException(response.message)
    ErrorCode.SourcesTooMany, ErrorCode.SourceDoesNotExist -> throw SourceException(response.message)
    ErrorCode.UnexpectedError -> throw NewsApiException(response.message)
}

@Serializable
public enum class ErrorCode(public val desc: String) {
    ApiKeyDisabled("Your API key has been disabled."),
    ApiKeyExhausted("Your API key has no more requests available."),
    ApiKeyInvalid("Your API key hasn't been entered correctly. Double check it and try again."),
    ApiKeyMissing("Your API key is missing from the request."),
    ParameterInvalid("You've included a parameter in your request which is currently not supported. Check the message property for more details."),
    ParametersMissing("Required parameters are missing from the request and it cannot be completed. Check the message property for more details."),
    RateLimited("You have been rate limited. Back off for a while before trying the request again."),
    SourcesTooMany("You have requested too many sources in a single request. Try splitting the request into 2 smaller requests."),
    SourceDoesNotExist("You have requested a source which does not exist."),
    UnexpectedError("This shouldn't happen, and if it does then it's our fault, not yours. Try the request again shortly.")
}