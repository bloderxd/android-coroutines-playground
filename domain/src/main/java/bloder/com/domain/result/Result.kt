package bloder.com.domain.result

inline class Result<out T>(private val value: Any?) {

    companion object {
        inline fun <T> ok(value: T) : Result<T> = Result(value)
        inline fun <T> error(exception: Exception) : Result<T> = Result(Failure(exception))
    }

    fun get() : Any? = value

    open class Failure(val exception: Exception)
}

inline fun <T, R> Result<T>.map(transform: (T) -> R) : Result<R> = when(get()) {
    is Result.Failure -> Result(get())
    else -> Result.ok(transform(get() as T))
}

inline fun <T, R> Result<T>.flatMap(transform: (T) -> Result<R>) : Result<R> = when(get()) {
    is Result.Failure -> Result(get())
    else -> transform(get() as T)
}