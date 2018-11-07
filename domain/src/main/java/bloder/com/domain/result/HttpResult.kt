package bloder.com.domain.result

inline class HttpResult<T>(private val result: Result<T>) {

    companion object {
        inline fun <T> success(value : T) : HttpResult<T> = HttpResult(Result.ok(value))
        inline fun <T> requestError(code: Int, errorBody: T) : HttpResult<Result.Failure> =
                HttpResult(HttpFailure(code, errorBody).getFailure())
    }

    class HttpFailure<out E> (private val code: Int, private val errorBody: E?) {

        fun getFailure() : Result<Result.Failure> = when(code) {
            400 -> Result.error(HttpCodeException(HttpCode.On400(errorBody)))
            404 -> Result.error(HttpCodeException(HttpCode.On404(errorBody)))
            500 -> Result.error(HttpCodeException(HttpCode.On500(errorBody)))
            403 -> Result.error(HttpCodeException(HttpCode.On403(errorBody)))
            else -> Result.error(HttpCodeException(HttpCode.Unknown))
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun getResponse() : T = when(result.get()) {
        is Result.Failure -> throw result.get() as HttpCodeException
        else -> result.get() as T
    }
}

sealed class HttpCode {
    open class On400<T>(errorBody: T? = null) : HttpCode()
    open class On404<T>(errorBody: T? = null) : HttpCode()
    open class On500<T>(errorBody: T? = null) : HttpCode()
    open class On403<T>(errorBody: T? = null) : HttpCode()
    object Unknown : HttpCode()
}

class HttpCodeException(val code: HttpCode) : Exception()

