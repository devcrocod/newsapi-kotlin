package io.github.devcrocod.news.api

import io.github.devcrocod.news.api.models.SourceId
import io.github.devcrocod.news.api.request.everything
import kotlinx.coroutines.runBlocking
import kotlin.collections.first
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.text.contains

class EverythingTests {

    private lateinit var client: NewsApiClient

    @BeforeTest
    fun initClient() {
        client = NewsApiClient("a4c33ad851904b00a3e641ef6edf7386")
    }

    @Test
    fun `get all articles about bitcoin`() {
        runBlocking {
            val articlesAboutBitcoin = client.everything {
                phrase = "bitcoin"
            }

            val bit = articlesAboutBitcoin.first().title.contains("Bitcoin") ||
                    articlesAboutBitcoin.first().title.contains("bitcoin") ||
                    articlesAboutBitcoin.first().description?.contains("Bitcoin") == true ||
                    articlesAboutBitcoin.first().description?.contains("bitcoin") == true ||
                    articlesAboutBitcoin.first().content?.contains("Bitcoin") == true ||
                    articlesAboutBitcoin.first().content?.contains("bitcoin") == true
            assertTrue(bit)
        }
    }

    @Test
    fun `all articles published by TechCrunch and The Next Web`() {
        runBlocking {
            val articlesFromTechCrunchAndNextWeb = client.everything {
                domains = "techcrunch.com,thenextweb.com"
            }

            val id = articlesFromTechCrunchAndNextWeb.first().source.id
            assertTrue(id == SourceId.TechCrunch.option || id == SourceId.The_Next_Web.option)
        }
    }
}