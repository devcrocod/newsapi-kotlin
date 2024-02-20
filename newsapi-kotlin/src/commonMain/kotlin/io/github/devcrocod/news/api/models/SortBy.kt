package io.github.devcrocod.news.api.models

/**
 * The `SortBy` enum represents the available sorting options for sorting data.
 *
 * @property option The sorting option as a string.
 */
public enum class SortBy(public val option: String) {
    RELEVANCY("relevancy"), POPULARITY("popularity"), PUBLISHED_AT("publishedAt")
}