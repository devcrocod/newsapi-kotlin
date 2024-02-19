package io.github.devcrocod.news.api.models

import kotlinx.datetime.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
public data class Article(
    val source: Source,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    @Serializable(with = LocalDateTimeSerializer::class)
    val publishedAt: LocalDateTime,
    val content: String?
)

public object LocalDateTimeSerializer : KSerializer<LocalDateTime> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("LocalDateTime", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        val instant = value.toInstant(FixedOffsetTimeZone(UtcOffset.ZERO)).toString()
        encoder.encodeString(instant)
    }

    override fun deserialize(decoder: Decoder): LocalDateTime {
        val instant = Instant.parse(decoder.decodeString())
        return instant.toLocalDateTime(FixedOffsetTimeZone(UtcOffset.ZERO))
    }
}
