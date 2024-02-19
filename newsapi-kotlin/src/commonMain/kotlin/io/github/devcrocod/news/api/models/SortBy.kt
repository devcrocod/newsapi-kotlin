package io.github.devcrocod.news.api.models

public enum class SortBy(public val option: String) {
    RELEVANCY("relevancy"), POPULARITY("popularity"), PUBLISHED_AT("publishedAt")
}