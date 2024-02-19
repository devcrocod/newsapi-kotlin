package io.github.devcrocod.news.api.request

public class KeywordBuilder internal constructor(private val builder: StringBuilder = StringBuilder()) :
    CharSequence by builder {
    public fun append(value: String): KeywordBuilder = this.apply { builder.append(value) }

    public fun matchPhrase(value: String): KeywordBuilder = this.apply { builder.append("\"$value\"") }

    public operator fun String.unaryPlus(): KeywordBuilder = this@KeywordBuilder.apply { builder.append("+${this@unaryPlus}") }

    public operator fun String.unaryMinus(): KeywordBuilder =
        this@KeywordBuilder.apply { builder.append("-${this@unaryMinus}") }

    public infix fun and(value: String): KeywordBuilder = this.apply { builder.append("AND $value") }

    public infix fun or(value: String): KeywordBuilder = this.apply { builder.append("OR $value") }

    public infix fun not(value: String): KeywordBuilder = this.apply { builder.append("NOT $value") }

    public override fun toString(): String = builder.toString()
}