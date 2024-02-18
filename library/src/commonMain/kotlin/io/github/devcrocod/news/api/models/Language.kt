package io.github.devcrocod.news.api.models

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = LanguageSerializer::class)
public class Language(code: String) : CriteriaSet<Language>(code) {
    public companion object {
        public val AR: Language = Language("ar")
        public val DE: Language = Language("de")
        public val EN: Language = Language("en")
        public val ES: Language = Language("es")
        public val FR: Language = Language("fr")
        public val HE: Language = Language("he")
        public val IT: Language = Language("it")
        public val NL: Language = Language("nl")
        public val NO: Language = Language("no")
        public val PT: Language = Language("pt")
        public val RU: Language = Language("ru")
        public val SV: Language = Language("sv")
        public val UD: Language = Language("ud")
        public val ZH: Language = Language("zh")
    }

    override fun and(other: CriteriaSet<Language>): Language {
        this.set.add(other.option)
        return this
    }
}

internal object LanguageSerializer : KSerializer<Language> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Language", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Language) {
        encoder.encodeString(value.option)
    }

    override fun deserialize(decoder: Decoder): Language {
        val name = decoder.decodeString()
        return Language(name)
    }
}