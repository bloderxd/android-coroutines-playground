package bloder.com.domain.result

inline class HttpResult<T>(private val result: Result<T>) {

    companion object {
        inline fun <T> success(value : T) : HttpResult<T> = HttpResult(Result.ok(value))
        inline fun <T> requestError(code: Int, errorBody: Exception) : HttpResult<T> =
                HttpResult(HttpFailure<Nothing>(code, errorBody).getFailure())
    }

    class HttpFailure<T> (private val code: Int, private val errorBody: Exception) {

        fun getFailure() : Result<T> = when(code) {
            400 -> Result.error(HttpCodeException(HttpCode.On400(errorBody)))
            404 -> Result.error(HttpCodeException(HttpCode.On404(errorBody)))
            500 -> Result.error(HttpCodeException(HttpCode.On500(errorBody)))
            403 -> Result.error(HttpCodeException(HttpCode.On403(errorBody)))
            else -> Result.error(HttpCodeException(HttpCode.Unknown))
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun getResponse() : T = when(result.get()) {
        is Result.Failure -> throw (result.get() as Result.Failure).exception as HttpCodeException
        else -> result.get() as T
    }
}

sealed class HttpCode {
    open class On400<out T : Exception>(val errorBody: T) : HttpCode()
    open class On404<out T : Exception>(val errorBody: T) : HttpCode()
    open class On500<out T : Exception>(val errorBody: T) : HttpCode()
    open class On403<out T : Exception>(val errorBody: T) : HttpCode()
    object Unknown : HttpCode()
}

class HttpCodeException(val code: HttpCode) : Exception()

inline fun <reified T : Exception> HttpCodeException.is400(action: (T?) -> Unit) =
    if (this.code is HttpCode.On400<*> && (this.code.errorBody::class.java) == T::class.java) {
        action((this.code as HttpCode.On400<T>).errorBody)
    } else {}

