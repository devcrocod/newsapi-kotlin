package io.github.devcrocod.news.api

import io.github.devcrocod.news.api.models.Category
import io.github.devcrocod.news.api.models.Country
import io.github.devcrocod.news.api.request.sources
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SourcesTests {

    private lateinit var client: NewsApiClient

    @BeforeTest
    fun initClient() {
        client = NewsApiClient("a4c33ad851904b00a3e641ef6edf7386")
    }

    @Test
    fun `all sources`() {
        runBlocking {
            val sourcesList = client.sources()
            val totalSourceId = 128
            assertEquals(totalSourceId, sourcesList.size)
        }
    }

    @Test
    fun `get business sources`() {
        runBlocking {
            val businessSources = client.sources {
                category = Category.BUSINESS
            }

            assertEquals(Category.BUSINESS, businessSources.first().category)
        }
    }

    @Test
    fun `sources in DE`() {
        runBlocking {
            val deSources = client.sources {
                country = Country.DE
            }

            assertEquals(Country.DE, deSources.first().country)
        }
    }
}