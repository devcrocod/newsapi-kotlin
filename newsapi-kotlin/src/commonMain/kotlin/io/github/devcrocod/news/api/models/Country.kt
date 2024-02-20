package io.github.devcrocod.news.api.models

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Represents a country.
 * The 2-letter ISO 3166-1 code of the country you want to get headlines for.
 * Possible options: [AE], [AR], [AT], [AU], [BE], [BG], [BR], [CA], [CH], [CN], [CO],
 * [CU], [CZ], [DE], [EG], [FR], [GB], [GR], [HK], [HU], [ID], [IE], [IL], [IN], [IT],
 * [JP], [KR], [LT] [LV], [MA], [MX], [MY], [NG], [NL] [NO], [NZ], [PH], [PL], [PT], [RO],
 * [RS], [RU], [SA], [SE], [SG], [SI], [SK], [TH], [TR], [TW], [UA], [US], [VE], [ZA]
 *
 * > Note: you can't mix this param with the sources param.
 */
@Serializable(with = CountrySerializer::class)
public class Country internal constructor(code: String) : CriteriaSet<Country>(code) {
    public companion object {
        /**
         * AE: United Arab Emirates
         */
        public val AE: Country = Country("ae")

        /**
         * AR: Argentina
         */
        public val AR: Country = Country("ar")

        /**
         * AT: Austria
         */
        public val AT: Country = Country("at")

        /**
         * AU: Australia
         */
        public val AU: Country = Country("au")

        /**
         * BE: Belgium
         */
        public val BE: Country = Country("be")

        /**
         * BG: Bulgaria
         */
        public val BG: Country = Country("bg")

        /**
         * BR: Brazil
         */
        public val BR: Country = Country("br")

        /**
         * CA: Canada
         */
        public val CA: Country = Country("ca")

        /**
         * CH: Switzerland
         */
        public val CH: Country = Country("ch")

        /**
         * CN: China
         */
        public val CN: Country = Country("cn")

        /**
         * CO: Colombia
         */
        public val CO: Country = Country("co")

        /**
         * CU: Cuba
         */
        public val CU: Country = Country("cu")

        /**
         * CZ: Czech Republic
         */
        public val CZ: Country = Country("cz")

        /**
         * DE: Germany
         */
        public val DE: Country = Country("de")

        /**
         * EG: Egypt
         */
        public val EG: Country = Country("eg")

        /**
         * FR: France
         */
        public val FR: Country = Country("fr")

        /**
         * GB: United Kingdom
         */
        public val GB: Country = Country("gb")

        /**
         * GR: Greece
         */
        public val GR: Country = Country("gr")

        /**
         * HK: Hong Kong
         */
        public val HK: Country = Country("hk")

        /**
         * HU: Hungary
         */
        public val HU: Country = Country("hu")

        /**
         * ID: Indonesia
         */
        public val ID: Country = Country("id")

        /**
         * IE: Ireland
         */
        public val IE: Country = Country("ie")

        /**
         * IL: Israel
         */
        public val IL: Country = Country("il")

        /**
         * IN: India
         */
        public val IN: Country = Country("in")

        /**
         * IT: Italy
         */
        public val IT: Country = Country("it")

        /**
         * JP: Japan
         */
        public val JP: Country = Country("jp")

        /**
         * KR: Korea, Republic of
         */
        public val KR: Country = Country("kr")

        /**
         * LT: Lithuania
         */
        public val LT: Country = Country("lt")

        /**
         * LV: Latvia
         */
        public val LV: Country = Country("lv")

        /**
         * MA: Morocco
         */
        public val MA: Country = Country("ma")

        /**
         * MX: Mexico
         */
        public val MX: Country = Country("mx")

        /**
         * MY: Malaysia
         */
        public val MY: Country = Country("my")

        /**
         * NG: Nigeria
         */
        public val NG: Country = Country("ng")

        /**
         * NL: Netherlands
         */
        public val NL: Country = Country("nl")

        /**
         * NO: Norway
         */
        public val NO: Country = Country("no")

        /**
         * NZ: New Zealand
         */
        public val NZ: Country = Country("nz")

        /**
         * PH: Philippines
         */
        public val PH: Country = Country("ph")

        /**
         * PL: Poland
         */
        public val PL: Country = Country("pl")

        /**
         * PT: Portugal
         */
        public val PT: Country = Country("pt")

        /**
         * RO: Romania
         */
        public val RO: Country = Country("ro")

        /**
         * RS: Serbia
         */
        public val RS: Country = Country("rs")

        /**
         * RU: Russia
         */
        public val RU: Country = Country("ru")

        /**
         * SA: Saudi Arabia
         */
        public val SA: Country = Country("sa")

        /**
         * SE: Sweden
         */
        public val SE: Country = Country("se")

        /**
         * SG: Singapore
         */
        public val SG: Country = Country("sg")

        /**
         * SI: Slovenia
         */
        public val SI: Country = Country("si")

        /**
         * SK: Slovakia
         */
        public val SK: Country = Country("sk")

        /**
         * TH: Thailand
         */
        public val TH: Country = Country("th")

        /**
         * TR: Turkey
         */
        public val TR: Country = Country("tr")

        /**
         * TW: Taiwan
         */
        public val TW: Country = Country("tw")

        /**
         * UA: Ukraine
         */
        public val UA: Country = Country("ua")

        /**
         * US: United States
         */
        public val US: Country = Country("us")

        /**
         * VE: Venezuela
         */
        public val VE: Country = Country("ve")

        /**
         * ZA: South Africa
         */
        public val ZA: Country = Country("za")
    }

    /**
     * Adds the option of the given criteria set to this criteria set.
     *
     * @param other the criteria set to be added
     * @return the updated criteria set
     */
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