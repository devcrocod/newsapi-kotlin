package io.github.devcrocod.news.api.response

import io.github.devcrocod.news.api.models.Article
import io.github.devcrocod.news.api.models.ErrorCode
import io.github.devcrocod.news.api.models.Source
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

@OptIn(ExperimentalSerializationApi::class, InternalSerializationApi::class)
internal class ResponseSerializer<T>(private val dataSerializer: KSerializer<T>) : KSerializer<Response<T>> {
    override val descriptor: SerialDescriptor = buildSerialDescriptor("Response", PolymorphicKind.SEALED) {

        element("SuccessResponse", buildClassSerialDescriptor("Ok") {
            element<String>("status")
            element<Int>("totalResults")
            element(elementName = "articles", descriptor = listSerialDescriptor(dataSerializer.descriptor))
        })
        element("ErrorResponse", buildClassSerialDescriptor("Error") {
            element<String>("status")
            element<ErrorCode>("code")
            element<String>("message")
        })
    }

    override fun deserialize(decoder: Decoder): Response<T> {
        require(decoder is JsonDecoder)
        val element = decoder.decodeJsonElement()
        require(element is JsonObject)

        val status = element["status"]?.jsonPrimitive?.content ?: throw SerializationException("Status missing")

        return when (status) {
            "error" -> {
                val errorCodeString = element["code"]?.jsonPrimitive?.content
                    ?: throw SerializationException("Error code missing")
                val errorCode =
                    ErrorCode.entries.find { it.name.replaceFirstChar { it.lowercase() } == errorCodeString }
                        ?: throw SerializationException("Unknown error code: $errorCodeString")
                Response.ErrorResponse(
                    status = status,
                    code = errorCode,
                    message = element["message"]?.jsonPrimitive?.content
                        ?: throw SerializationException("Error message missing")
                )
            }

            "ok" -> {
                when {
                    element.containsKey("articles") -> {
                        Response.SuccessResponse(
                            status = status,
                            totalResults = element["totalResults"]?.jsonPrimitive?.int,
                            data = decoder.json.decodeFromJsonElement(
                                ListSerializer(dataSerializer),
                                element["articles"]!!
                            )
                        )
                    }

                    element.containsKey("sources") -> {
                        Response.SuccessResponse(
                            status = status,
                            totalResults = null,
                            data = decoder.json.decodeFromJsonElement(
                                ListSerializer(dataSerializer),
                                element["sources"]!!
                            )
                        )
                    }

                    else -> throw SerializationException("Unknown data structure")
                }
            }

            else -> throw SerializationException("Unknown response status")
        }
    }

    override fun serialize(encoder: Encoder, value: Response<T>) {
        require(encoder is JsonEncoder)
        val jsonObject = when (value) {
            is Response.SuccessResponse -> buildJsonObject {
                put("status", value.status)
                value.totalResults?.let { put("totalResults", it) }
                when {
                    value.data.isNotEmpty() && value.data.first() is Article -> put(
                        "articles",
                        encoder.json.encodeToJsonElement(ListSerializer(dataSerializer), value.data)
                    )

                    value.data.isNotEmpty() && value.data.first() is Source -> put(
                        "sources",
                        encoder.json.encodeToJsonElement(ListSerializer(dataSerializer), value.data)
                    )
                }
            }

            is Response.ErrorResponse -> buildJsonObject {
                put("status", value.status)
                put("code", value.code.name.replaceFirstChar { it.lowercase() })
                put("message", value.message)
            }
        }
        encoder.encodeJsonElement(jsonObject)
    }
}