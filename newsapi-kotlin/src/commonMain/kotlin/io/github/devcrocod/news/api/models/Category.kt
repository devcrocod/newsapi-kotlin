package io.github.devcrocod.news.api.models

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Represents a category for grouping criteria sets.
 *
 * @property BUSINESS - Represents the 'business' category for grouping criteria sets.
 * @property ENTERTAINMENT - Represents the 'entertainment' category for grouping criteria sets.
 * @property GENERAL - Represents the 'general' category for grouping criteria sets.
 * @property HEALTH - Represents the 'health' category for grouping criteria sets.
 * @property SCIENCE - Represents the 'science' category for grouping criteria sets.
 * @property SPORTS - Represents the 'sports' category for grouping criteria sets.
 * @property TECHNOLOGY - Represents the 'technology' category for grouping criteria sets.
 */
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

    /**
     * Adds another Category to the current Category.
     *
     * @param other The Category to be added to the current Category.
     * @return The Category with the added CriteriaSet.
     */
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