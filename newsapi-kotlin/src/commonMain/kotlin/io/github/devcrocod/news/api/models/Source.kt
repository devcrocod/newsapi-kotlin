package io.github.devcrocod.news.api.models

import kotlinx.serialization.Serializable

/**
 * Represents a source ID for retrieving news articles.
 *
 * Possible options: [ABC_News], [ABC_News_AU], [Aftenposten], [Al_Jazeera_English], [ANSA_it], [Argaam],
 * [Ars_Technica], [Ary_News], [Associated_Press], [Australian_Financial_Review], [Axios], [BBC_News], [BBC_Sport],
 * [Bild], [Blasting_News_BR], [Bleacher_Report], [Bloomberg], [Breitbart_News], [Business_Insider],
 * [Business_Insider_UK], [Buzzfeed], [CBC_News], [CBS_News], [CNN], [CNN_Spanish], [Crypto_Coins_News],
 * [Der_Tagesspiegel], [Die_Zeit], [El_Mundo], [Engadget], [Entertainment_Weekly], [ESPN], [ESPN_Cric_Info],
 * [Financial_Post], [Focus], [Football_Italia], [Fortune], [FourFourTwo], [Fox_News], [Fox_Sports], [Globo],
 * [Google_News], [Google_News_Argentina], [Google_News_Australia], [Google_News_Brasil], [Google_News_Canada],
 * [Google_News_France], [Google_News_India], [Google_News_Israel], [Google_News_Italy], [Google_News_Russia],
 * [Google_News_SaudiArabia], [Google_News_UK], [Goteborgs_Posten], [Gruenderszene], [Hacker_News], [Handelsblatt],
 * [IGN], [Il_Sole_24_Ore], [Independent], [Infobae], [InfoMoney], [La_Gaceta], [La_Nacion], [La_Repubblica],
 * [Le_Monde], [Lenta], [Lequipe], [Les_Echos], [Liberation], [Marca], [Mashable], [Medical_News_Today], [MSNBC],
 * [MTV_News], [MTV_News_UK], [National_Geographic], [National_Review], [NBC_News], [News24], [New_Scientist],
 * [News_COM_AU], [Newsweek], [New_York_Magazine], [Next_Big_Future], [NFL_News], [NHL_News], [NRK], [Politico],
 * [Polygon], [RBC], [Recode], [Reddit_ALL], [Reuters], [RT], [RTE], [RTL_Nieuws], [SABQ], [Spiegel_Online],
 * [Svenska_Dagbladet], [T3n], [TalkSport], [TechCrunch], [TechCrunch_CN], [TechRadar], [The_American_Conservative],
 * [The_Globe_And_Mail], [The_Hill], [The_Hindu], [The_Huffington_Post], [The_Irish_Times], [The_Jerusalem_Post],
 * [The_Lad_Bible], [The_Next_Web], [The_Sport_Bible], [The_Times_of_India], [The_Verge], [The_Wall_Street_Journal],
 * [The_Washington_Post], [The_Washington_Times], [Time], [USA_Today], [Vice_News], [Wired], [Wired_DE],
 * [Wirtschafts_Woche], [Xinhua_Net], [Ynet]
 */
public class SourceId internal constructor(id: String) : CriteriaSet<SourceId>(id) {

    public override infix fun and(other: CriteriaSet<SourceId>): SourceId {
        this.set.add(other.option)
        return this
    }

    public companion object {
        public val ABC_News: SourceId = SourceId(id = "abc-news")
        public val ABC_News_AU: SourceId = SourceId(id = "abc-news-au")
        public val Aftenposten: SourceId = SourceId(id = "aftenposten")
        public val Al_Jazeera_English: SourceId = SourceId(id = "al-jazeera-english")
        public val ANSA_it: SourceId = SourceId(id = "ansa")
        public val Argaam: SourceId = SourceId(id = "argaam")
        public val Ars_Technica: SourceId = SourceId(id = "ars-technica")
        public val Ary_News: SourceId = SourceId(id = "ary-news")
        public val Associated_Press: SourceId = SourceId(id = "associated-press")
        public val Australian_Financial_Review: SourceId = SourceId(id = "australian-financial-review")
        public val Axios: SourceId = SourceId(id = "axios")
        public val BBC_News: SourceId = SourceId(id = "bbc-news")
        public val BBC_Sport: SourceId = SourceId(id = "bbc-sport")
        public val Bild: SourceId = SourceId(id = "bild")
        public val Blasting_News_BR: SourceId = SourceId(id = "blasting-news-br")
        public val Bleacher_Report: SourceId = SourceId(id = "bleacher-report")
        public val Bloomberg: SourceId = SourceId(id = "bloomberg")
        public val Breitbart_News: SourceId = SourceId(id = "breitbart-news")
        public val Business_Insider: SourceId = SourceId(id = "business-insider")
        public val Business_Insider_UK: SourceId = SourceId(id = "business-insider-uk")
        public val Buzzfeed: SourceId = SourceId(id = "buzzfeed")
        public val CBC_News: SourceId = SourceId(id = "cbc-news")
        public val CBS_News: SourceId = SourceId(id = "cbs-news")
        public val CNN: SourceId = SourceId(id = "cnn")
        public val CNN_Spanish: SourceId = SourceId(id = "cnn-es")
        public val Crypto_Coins_News: SourceId = SourceId(id = "crypto-coins-news")
        public val Der_Tagesspiegel: SourceId = SourceId(id = "der-tagesspiegel")
        public val Die_Zeit: SourceId = SourceId(id = "die-zeit")
        public val El_Mundo: SourceId = SourceId(id = "el-mundo")
        public val Engadget: SourceId = SourceId(id = "engadget")
        public val Entertainment_Weekly: SourceId = SourceId(id = "entertainment-weekly")
        public val ESPN: SourceId = SourceId(id = "espn")
        public val ESPN_Cric_Info: SourceId = SourceId(id = "espn-cric-info")
        public val Financial_Post: SourceId = SourceId(id = "financial-post")
        public val Focus: SourceId = SourceId(id = "focus")
        public val Football_Italia: SourceId = SourceId(id = "football-italia")
        public val Fortune: SourceId = SourceId(id = "fortune")
        public val FourFourTwo: SourceId = SourceId(id = "four-four-two")
        public val Fox_News: SourceId = SourceId(id = "fox-news")
        public val Fox_Sports: SourceId = SourceId(id = "fox-sports")
        public val Globo: SourceId = SourceId(id = "globo")
        public val Google_News: SourceId = SourceId(id = "google-news")
        public val Google_News_Argentina: SourceId = SourceId(id = "google-news-ar")
        public val Google_News_Australia: SourceId = SourceId(id = "google-news-au")
        public val Google_News_Brasil: SourceId = SourceId(id = "google-news-br")
        public val Google_News_Canada: SourceId = SourceId(id = "google-news-ca")
        public val Google_News_France: SourceId = SourceId(id = "google-news-fr")
        public val Google_News_India: SourceId = SourceId(id = "google-news-in")
        public val Google_News_Israel: SourceId = SourceId(id = "google-news-is")
        public val Google_News_Italy: SourceId = SourceId(id = "google-news-it")
        public val Google_News_Russia: SourceId = SourceId(id = "google-news-ru")
        public val Google_News_SaudiArabia: SourceId = SourceId(id = "google-news-sa")
        public val Google_News_UK: SourceId = SourceId(id = "google-news-uk")
        public val Goteborgs_Posten: SourceId = SourceId(id = "goteborgs-posten")
        public val Gruenderszene: SourceId = SourceId(id = "gruenderszene")
        public val Hacker_News: SourceId = SourceId(id = "hacker-news")
        public val Handelsblatt: SourceId = SourceId(id = "handelsblatt")
        public val IGN: SourceId = SourceId(id = "ign")
        public val Il_Sole_24_Ore: SourceId = SourceId(id = "il-sole-24-ore")
        public val Independent: SourceId = SourceId(id = "independent")
        public val Infobae: SourceId = SourceId(id = "infobae")
        public val InfoMoney: SourceId = SourceId(id = "info-money")
        public val La_Gaceta: SourceId = SourceId(id = "la-gaceta")
        public val La_Nacion: SourceId = SourceId(id = "la-nacion")
        public val La_Repubblica: SourceId = SourceId(id = "la-repubblica")
        public val Le_Monde: SourceId = SourceId(id = "le-monde")
        public val Lenta: SourceId = SourceId(id = "lenta")
        public val Lequipe: SourceId = SourceId(id = "lequipe")
        public val Les_Echos: SourceId = SourceId(id = "les-echos")
        public val Liberation: SourceId = SourceId(id = "liberation")
        public val Marca: SourceId = SourceId(id = "marca")
        public val Mashable: SourceId = SourceId(id = "mashable")
        public val Medical_News_Today: SourceId = SourceId(id = "medical-news-today")
        public val MSNBC: SourceId = SourceId(id = "msnbc")
        public val MTV_News: SourceId = SourceId(id = "mtv-news")
        public val MTV_News_UK: SourceId = SourceId(id = "mtv-news-uk")
        public val National_Geographic: SourceId = SourceId(id = "national-geographic")
        public val National_Review: SourceId = SourceId(id = "national-review")
        public val NBC_News: SourceId = SourceId(id = "nbc-news")
        public val News24: SourceId = SourceId(id = "news24")
        public val New_Scientist: SourceId = SourceId(id = "new-scientist")
        public val News_COM_AU: SourceId = SourceId(id = "news-com-au")
        public val Newsweek: SourceId = SourceId(id = "newsweek")
        public val New_York_Magazine: SourceId = SourceId(id = "new-york-magazine")
        public val Next_Big_Future: SourceId = SourceId(id = "next-big-future")
        public val NFL_News: SourceId = SourceId(id = "nfl-news")
        public val NHL_News: SourceId = SourceId(id = "nhl-news")
        public val NRK: SourceId = SourceId(id = "nrk")
        public val Politico: SourceId = SourceId(id = "politico")
        public val Polygon: SourceId = SourceId(id = "polygon")
        public val RBC: SourceId = SourceId(id = "rbc")
        public val Recode: SourceId = SourceId(id = "recode")
        public val Reddit_ALL: SourceId = SourceId(id = "reddit-r-all")
        public val Reuters: SourceId = SourceId(id = "reuters")
        public val RT: SourceId = SourceId(id = "rt")
        public val RTE: SourceId = SourceId(id = "rte")
        public val RTL_Nieuws: SourceId = SourceId(id = "rtl-nieuws")
        public val SABQ: SourceId = SourceId(id = "sabq")
        public val Spiegel_Online: SourceId = SourceId(id = "spiegel-online")
        public val Svenska_Dagbladet: SourceId = SourceId(id = "svenska-dagbladet")
        public val T3n: SourceId = SourceId(id = "t3n")
        public val TalkSport: SourceId = SourceId(id = "talksport")
        public val TechCrunch: SourceId = SourceId(id = "techcrunch")
        public val TechCrunch_CN: SourceId = SourceId(id = "techcrunch-cn")
        public val TechRadar: SourceId = SourceId(id = "techradar")
        public val The_American_Conservative: SourceId = SourceId(id = "the-american-conservative")
        public val The_Globe_And_Mail: SourceId = SourceId(id = "the-globe-and-mail")
        public val The_Hill: SourceId = SourceId(id = "the-hill")
        public val The_Hindu: SourceId = SourceId(id = "the-hindu")
        public val The_Huffington_Post: SourceId = SourceId(id = "the-huffington-post")
        public val The_Irish_Times: SourceId = SourceId(id = "the-irish-times")
        public val The_Jerusalem_Post: SourceId = SourceId(id = "the-jerusalem-post")
        public val The_Lad_Bible: SourceId = SourceId(id = "the-lad-bible")
        public val The_Next_Web: SourceId = SourceId(id = "the-next-web")
        public val The_Sport_Bible: SourceId = SourceId(id = "the-sport-bible")
        public val The_Times_of_India: SourceId = SourceId(id = "the-times-of-india")
        public val The_Verge: SourceId = SourceId(id = "the-verge")
        public val The_Wall_Street_Journal: SourceId = SourceId(id = "the-wall-street-journal")
        public val The_Washington_Post: SourceId = SourceId(id = "the-washington-post")
        public val The_Washington_Times: SourceId = SourceId(id = "the-washington-times")
        public val Time: SourceId = SourceId(id = "time")
        public val USA_Today: SourceId = SourceId(id = "usa-today")
        public val Vice_News: SourceId = SourceId(id = "vice-news")
        public val Wired: SourceId = SourceId(id = "wired")
        public val Wired_DE: SourceId = SourceId(id = "wired-de")
        public val Wirtschafts_Woche: SourceId = SourceId(id = "wirtschafts-woche")
        public val Xinhua_Net: SourceId = SourceId(id = "xinhua-net")
        public val Ynet: SourceId = SourceId(id = "ynet")
    }
}

/**
 * A data class representing a source.
 *
 * @property id The ID of the source.
 * @property name The name of the source.
 * @property description The description of the source.
 * @property url The URL of the source.
 * @property category The category of the source.
 * @property language The language of the source.
 * @property country The country of the source.
 */
@Serializable
public data class Source(
    val id: String?,
    val name: String,
    val description: String? = null,
    val url: String? = null,
    val category: Category? = null,
    val language: Language? = null,
    val country: Country? = null
)
