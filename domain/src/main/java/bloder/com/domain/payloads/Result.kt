package bloder.com.domain.payloads

inline class Result<out T>(private val value: Any?) {

    companion object {

        inline fun <T> ok(value: T) : Result<T> = Result(value)
        inline fun <T> error(exception: Exception) : Result<T> = Result(Result.Failure(exception))
    }

    @Suppress("UNCHECKED_CAST")
    fun getOrNull() : T? = when(value) {
        is Failure -> null
        else -> value as T
    }

    fun get() : Any? = value

    open class Failure(private val exception: Exception) {

        fun get() : Exception = exception
    }
}

inline fun <R, T> Result<T>.map(transform: (T) -> R) : Result<R> = when(get()) {
    is Result.Failure -> Result(get())
    else -> Result.ok(transform(get() as T))
}

inline fun <T, R> Result<T>.flatMap(transform: (T) -> Result<R>) : Result<R> = when(get()) {
    is Result.Failure -> Result.error(get() as Exception)
    else -> transform(get() as T)
}