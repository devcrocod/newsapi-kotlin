package io.github.devcrocod.news.api.request

/**
 * `KeywordBuilder` is primarily used for supporting advanced search functionalities.
 * The instances of this class are responsible for dynamically creating a search keyword,
 * following specific criteria and modifiers as mentioned in the advanced search rules.
 */
public class KeywordBuilder internal constructor(private val builder: StringBuilder = StringBuilder()) :
    CharSequence by builder {
    /**
     * Appends given `value` to the `builder`.
     *
     * @param value The String to be added to the `builder`.
     */
    public fun append(value: String): KeywordBuilder = this.apply { builder.append(value) }

    /**
     * Appends the given phrase `value` to the `builder` and surrounds it with quotation marks for an exact match.
     *
     * @param value The String to be added to the `builder` for exact match.
     */
    public fun matchPhrase(value: String): KeywordBuilder = this.apply { builder.append("\"$value\"") }

    /**
     * Unary plus operator function to prepend a plus symbol (+) to the given `String`.
     *
     * @returns KeywordBuilder instance after adding the plus symbol to the `builder`.
     */
    public operator fun String.unaryPlus(): KeywordBuilder =
        this@KeywordBuilder.apply { builder.append("+${this@unaryPlus}") }

    /**
     * Unary minus operator function to prepend a minus symbol (-) to the given `String`.
     *
     * @returns KeywordBuilder instance after adding the minus symbol to the `builder`.
     */
    public operator fun String.unaryMinus(): KeywordBuilder =
        this@KeywordBuilder.apply { builder.append("-${this@unaryMinus}") }

    /**
     * The "and" function can be called using the infix notation.
     * It appends the string "AND" followed by the given `value` to the `builder`.
     *
     * @param value The String to follow the "AND".
     * @returns KeywordBuilder instance after adding "AND" with `value` to the `builder`.
     */
    public infix fun and(value: String): KeywordBuilder = this.apply { builder.append("AND $value") }

    /**
     * The "or" function can be called using the infix notation.
     * It appends the string "OR" followed by the given `value` to the `builder`.
     *
     * @param value The String to follow the "OR".
     * @returns KeywordBuilder instance after adding "OR" with `value` to the `builder`.
     */
    public infix fun or(value: String): KeywordBuilder = this.apply { builder.append("OR $value") }

    /**
     * The "not" function can be called using the infix notation.
     * It appends the string "NOT" followed by the given `value` to the `builder`.
     *
     * @param value The String to follow the "NOT".
     * @returns KeywordBuilder instance after adding "NOT" with `value` to the `builder`.
     */
    public infix fun not(value: String): KeywordBuilder = this.apply { builder.append("NOT $value") }

    /**
     * Returns String representation of KeywordBuilder.
     *
     * @returns String that represents the constructed search keyword.
     */
    public override fun toString(): String = builder.toString()
}