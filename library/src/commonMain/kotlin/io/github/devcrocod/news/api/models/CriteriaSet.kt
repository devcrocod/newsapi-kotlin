package io.github.devcrocod.news.api.models


public sealed class CriteriaSet<T>(public val option: String) {
    internal val set: MutableSet<String> = mutableSetOf(option)

    public abstract infix fun and(other: CriteriaSet<T>): T

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as SourceId

        if (option != other.option) return false
        if (set != other.set) return false

        return true
    }

    override fun hashCode(): Int {
        var result = option.hashCode()
        result = 31 * result + set.hashCode()
        return result
    }

    override fun toString(): String {
        return set.joinToString(separator = ",")
    }
}