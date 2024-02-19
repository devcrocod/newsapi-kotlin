package io.github.devcrocod.news.api.models

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = CategorySerializer::class)
public class Category internal constructor(code: String) : CriteriaSet<Category>(code) {
    public companion object {
        public val BUSINESS: Category = Category("business")
        public val ENTERTAINMENT: Category = Category("entertainment")
        public val GENERAL: Category = Category("general")
        public val HEALTH: Category = Category("health")
        public val SCIENCE: Category = Category("science")
        public val SPORTS: Category = Category("sports")
        public val TECHNOLOGY: Category = Category("technology")
    }

    override fun and(other: CriteriaSet<Category>): Category {
        this.set.add(other.option)
        return this
    }
}

internal object CategorySerializer : KSerializer<Category> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Category", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Category) {
        encoder.encodeString(value.option)
    }

    override fun deserialize(decoder: Decoder): Category {
        val name = decoder.decodeString()
        return Category(name)
    }
}