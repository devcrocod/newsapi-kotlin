package io.github.devcrocod.news.api.models

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = CountrySerializer::class)
public class Country internal constructor(code: String) : CriteriaSet<Country>(code) {
    public companion object {
        public val AE: Country = Country("ae")
        public val AR: Country = Country("ar")
        public val AT: Country = Country("at")
        public val AU: Country = Country("au")
        public val BE: Country = Country("be")
        public val BG: Country = Country("bg")
        public val BR: Country = Country("br")
        public val CA: Country = Country("ca")
        public val CH: Country = Country("ch")
        public val CN: Country = Country("cn")
        public val CO: Country = Country("co")
        public val CU: Country = Country("cu")
        public val CZ: Country = Country("cz")
        public val DE: Country = Country("de")
        public val EG: Country = Country("eg")
        public val FR: Country = Country("fr")
        public val GB: Country = Country("gb")
        public val GR: Country = Country("gr")
        public val HK: Country = Country("hk")
        public val HU: Country = Country("hu")
        public val ID: Country = Country("id")
        public val IE: Country = Country("ie")
        public val IL: Country = Country("il")
        public val IN: Country = Country("in")
        public val IT: Country = Country("it")
        public val JP: Country = Country("jp")
        public val KR: Country = Country("kr")
        public val LT: Country = Country("lt")
        public val LV: Country = Country("lv")
        public val MA: Country = Country("ma")
        public val MX: Country = Country("mx")
        public val MY: Country = Country("my")
        public val NG: Country = Country("ng")
        public val NL: Country = Country("nl")
        public val NO: Country = Country("no")
        public val NZ: Country = Country("nz")
        public val PH: Country = Country("ph")
        public val PL: Country = Country("pl")
        public val PT: Country = Country("pt")
        public val RO: Country = Country("ro")
        public val RS: Country = Country("rs")
        public val RU: Country = Country("ru")
        public val SA: Country = Country("sa")
        public val SE: Country = Country("se")
        public val SG: Country = Country("sg")
        public val SI: Country = Country("si")
        public val SK: Country = Country("sk")
        public val TH: Country = Country("th")
        public val TR: Country = Country("tr")
        public val TW: Country = Country("tw")
        public val UA: Country = Country("ua")
        public val US: Country = Country("us")
        public val VE: Country = Country("ve")
        public val ZA: Country = Country("za")
    }

    override fun and(other: CriteriaSet<Country>): Country {
        this.set.add(other.option)
        return this
    }
}

internal object CountrySerializer : KSerializer<Country> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Country", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Country) {
        encoder.encodeString(value.option)
    }

    override fun deserialize(decoder: Decoder): Country {
        val name = decoder.decodeString()
        return Country(name)
    }
}