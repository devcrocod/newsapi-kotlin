package io.github.devcrocod.news.api.response

import io.github.devcrocod.news.api.models.ErrorCode
import kotlinx.serialization.Serializable

@Serializable(with = ResponseSerializer::class)
public sealed class Response<out T> {
    @Serializable
    public data class SuccessResponse<out T>(val status: String, val totalResults: Int? = null, val data: List<T>) :
        Response<T>()

    @Serializable
    public data class ErrorResponse(val status: String, val code: ErrorCode, val message: String) : Response<Nothing>()
}
