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

/**
 * Enumeration class representing error codes for the News API.
 *
 * @property desc The description associated with the error code.
 *
 * @constructor Creates and initializes an instance of the ErrorCode class with the given description.
 *
 * @param desc The description associated with the error code.
 *
 * @see ErrorCode.ApiKeyDisabled
 * @see ErrorCode.ApiKeyExhausted
 * @see ErrorCode.ApiKeyInvalid
 * @see ErrorCode.ApiKeyMissing
 * @see ErrorCode.ParameterInvalid
 * @see ErrorCode.ParametersMissing
 * @see ErrorCode.RateLimited
 * @see ErrorCode.SourcesTooMany
 * @see ErrorCode.SourceDoesNotExist
 * @see ErrorCode.UnexpectedError
 */
@Serializable
public enum class ErrorCode(public val desc: String) {
    /**
     * Class representing the error code for a disabled API key in the News API.
     */
    ApiKeyDisabled("Your API key has been disabled."),
    /**
     * Class representing the error code for an exhausted API key in the News API.
     */
    ApiKeyExhausted("Your API key has no more requests available."),
    /**
     * Exception class representing an invalid API key error.
     */
    ApiKeyInvalid("Your API key hasn't been entered correctly. Double check it and try again."),
    /**
     * Represents an error code for when the API key is missing from the request.
     */
    ApiKeyMissing("Your API key is missing from the request."),
    /**
     * Class representing an exception for invalid parameters.
     * This exception is thrown when a parameter in a request is not supported.
     */
    ParameterInvalid("You've included a parameter in your request which is currently not supported. Check the message property for more details."),
    /**
     * Represents an error code for when required parameters are missing from the request.
     */
    ParametersMissing("Required parameters are missing from the request and it cannot be completed. Check the message property for more details."),
    /**
     * Class representing an error code for rate-limited requests in the News API.
     */
    RateLimited("You have been rate limited. Back off for a while before trying the request again."),
    /**
     * Represents the error code for requesting too many sources in a single request.
     * This class is used
     * to indicate an error when the number of requested sources exceeds the maximum allowed in a single request.
     */
    SourcesTooMany("You have requested too many sources in a single request. Try splitting the request into 2 smaller requests."),
    /**
     * Class representing the error code for when the requested source does not exist.
     * This class is used to indicate an error when a user requests a source that does not exist.
     */
    SourceDoesNotExist("You have requested a source which does not exist."),
    /**
     * Class representing an unexpected error in the News API.
     */
    UnexpectedError("This shouldn't happen, and if it does then it's our fault, not yours. Try the request again shortly.")
}