package io.github.devcrocod.news.api.models

/**
 * The fields to restrict your q search to.
 *
 * The possible options are: [Title], [Description], [Content].
 *
 * Multiple options can be specified by separating them with a `and`, for example: `Search.Title and Search.Content`.
 */
public class Search internal constructor(option: String) : CriteriaSet<Search>(option) {

    public companion object {
        /**
         * Represents the search criteria for title.
         *
         * @property option the option associated with the search criteria
         */
        public val Title: Search = Search("title")
        /**
         * Represents the search criteria for description.
         */
        public val Description: Search = Search("description")
        /**
         * Represents the search criteria for content.
         */
        public val Content: Search = Search("content")
    }

    /**
     * Adds the specified [CriteriaSet] to the current [Search] criteria set using the 'and' operator.
     *
     * @param other the [CriteriaSet] to add to the current [Search] criteria set
     * @return a new [Search] criteria set with the additional criteria
     */
    public override infix fun and(other: CriteriaSet<Search>): Search {
        this.set.add(other.option)
        return this
    }
}
