package io.github.devcrocod.news.api.response

import io.github.devcrocod.news.api.models.ErrorCode
import kotlinx.serialization.Serializable

/**
 * Represents a generic response object.
 *
 * @param T The type of data contained in the response.
 */
@Serializable(with = ResponseSerializer::class)
public sealed class Response<out T> {
    /**
     * Represents a successful response object.
     *
     * @param T The type of data contained in the response.
     * @property status The status of the response.
     * @property totalResults The total number of results in the response.
     * @property data The list of data objects in the response.
     */
    @Serializable
    public data class SuccessResponse<out T>(val status: String, val totalResults: Int? = null, val data: List<T>) :
        Response<T>()

    /**
     * Represents an error response object.
     *
     * @property status The status of the response.
     * @property code The error code associated with the response.
     * @property message The error message associated with the response.
     *
     * @param T The type of data contained in the response.
     * @constructor Creates an instance of the [ErrorResponse] class with the specified status, error code, and error message.
     */
    @Serializable
    public data class ErrorResponse(val status: String, val code: ErrorCode, val message: String) : Response<Nothing>()
}
