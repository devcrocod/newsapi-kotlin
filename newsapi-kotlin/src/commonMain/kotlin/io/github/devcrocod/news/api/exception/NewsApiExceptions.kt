package io.github.devcrocod.news.api.exception

/**
 * Base exception class for News API errors.
 */
public open class NewsApiException : Exception {
    public constructor()
    public constructor(message: String?) : super(message)
    public constructor(message: String?, cause: Throwable?) : super(message, cause)
    public constructor(cause: Throwable?) : super(cause)
}

/**
 * Custom exception class for API key-related errors in the News API.
 *
 * This exception is used to indicate errors related to API keys. It extends the base [NewsApiException]
 * class and inherits its constructors.
 *
 * @param message The detail message explaining the reason for the exception to be thrown (optional).
 * @param cause The cause of the exception (optional).
 *
 * @see NewsApiException
 */
public class ApiKeyException : NewsApiException {
    public constructor(message: String?) : super(message)
    public constructor(message: String?, cause: Throwable?) : super(message, cause)
    public constructor(cause: Throwable?) : super(cause)
}

/**
 * Exception class representing errors related to invalid parameters.
 * Inherits from the NewsApiException class.
 *
 * @constructor Creates a new instance of a ParameterException with the specified message.
 * @param message The error message for the exception.
 *
 * @constructor Creates a new instance of a ParameterException with the specified message and cause.
 * @param message The error message for the exception.
 * @param cause The cause of the exception.
 *
 * @constructor Creates a new instance of a ParameterException with the specified cause.
 * @param cause The cause of the exception.
 */
public class ParameterException : NewsApiException {
    public constructor(message: String?) : super(message)
    public constructor(message: String?, cause: Throwable?) : super(message, cause)
    public constructor(cause: Throwable?) : super(cause)
}

/**
 * Exception thrown when a rate limit is exceeded for the News API.
 *
 * This class extends [NewsApiException], which is the base exception class for News API errors.
 *
 * @param message The detail message for this exception.
 * @param cause The cause of this exception.
 * @constructor Creates a RateLimitedException with the specified detail message and cause.
 */
public class RateLimitedException : NewsApiException {
    public constructor(message: String?) : super(message)
    public constructor(message: String?, cause: Throwable?) : super(message, cause)
    public constructor(cause: Throwable?) : super(cause)
}

/**
 * Represents an exception specific to the Source API in NewsApi.
 *
 * @constructor Constructs a SourceException with the specified error message.
 * @param message The error message for the exception.
 */
public class SourceException : NewsApiException {
    public constructor(message: String?) : super(message)
    public constructor(message: String?, cause: Throwable?) : super(message, cause)
    public constructor(cause: Throwable?) : super(cause)
}