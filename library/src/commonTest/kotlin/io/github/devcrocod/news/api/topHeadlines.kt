package io.github.devcrocod.news.api

import io.github.devcrocod.news.api.models.Category
import io.github.devcrocod.news.api.models.Country
import io.github.devcrocod.news.api.models.SourceId
import io.github.devcrocod.news.api.request.topHeadlines
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class TopHeadlinesTests {

    private lateinit var client: NewsApiClient

    @BeforeTest
    fun initClient() {
        client = NewsApiClient("a4c33ad851904b00a3e641ef6edf7386")
    }

    @Test
    fun `top headlines in the US`() {
        runBlocking {
            client.topHeadlines {
                country = Country.US
            }
        }
    }

    @Test
    fun `top headlines from BBC News`() {
        runBlocking {
            val headlinesFromBBC = client.topHeadlines {
                sources = SourceId.BBC_News
            }

            assertTrue(headlinesFromBBC.first().source.id == SourceId.BBC_News.option)
        }
    }

    @Test
    fun `top business headlines from Germany`() {
        runBlocking {
            client.topHeadlines {
                country = Country.DE
                category = Category.BUSINESS
            }
        }
    }
}