package io.github.devcrocod.news.api.models

import kotlinx.datetime.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Represents an article from a news source.
 *
 * @property source The [source][Source] of the article.
 * @property author The author of the article.
 * @property title The headline or title of the article.
 * @property description The description or snippet from the article.
 * @property url The direct URL to the article.
 * @property urlToImage The URL to the image associated with the article.
 * @property publishedAt The date and time that the article was published, in UTC (+000).
 * @property content The unformatted content of the article, where available. This is truncated to 200 chars.
 */
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

/**
 * LocalDateTimeSerializer is a custom serializer that is used to serialize and deserialize LocalDateTime objects.
 */
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
