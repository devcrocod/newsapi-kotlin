package io.github.devcrocod.news.api.models

public class Search internal constructor(option: String) : CriteriaSet<Search>(option) {

    public companion object {
        public val Title: Search = Search("title")
        public val Description: Search = Search("description")
        public val Content: Search = Search("content")
    }

    public override infix fun and(other: CriteriaSet<Search>): Search {
        this.set.add(other.option)
        return this
    }
}
