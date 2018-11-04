package bloder.com.domain.payloads

inline class HttpResult<T>(private val result: Result<T>) {

    companion object {
        inline fun <T> success(value : T) : HttpResult<T> = HttpResult(Result.ok(value))
        inline fun <T> requestError(exception: Exception) : HttpResult<T> = HttpResult(Result.error(exception))
    }

    fun onResponse(action: HttpCodeDsl<T>.() -> Unit) = HttpCodeDsl<T>().let { instance ->
        instance.action()
        when(result.get()) {
            is Result.Failure -> instance.handleError(result.get() as Exception)
            else -> instance.handleSuccess(result.get() as T)
        }
    }
}

inline fun <T> httpResult(response: T) : HttpResult<T> = HttpResult(Result(response))

class HttpCodeDsl<T> {

    lateinit var on200: (T) -> Unit
    lateinit var on400: (Exception) -> Unit
    lateinit var on404: (Exception) -> Unit
    lateinit var on500: (Exception) -> Unit
    lateinit var on403: (Exception) -> Unit
    lateinit var onUnknownResponse: () -> Unit

    fun on200(action: (T) -> Unit) : HttpCodeDsl<T> {
        on200 = action
        return this
    }

    fun on400(action: (Exception) -> Unit) : HttpCodeDsl<T> {
        on400 = action
        return this
    }

    fun on404(action: (Exception) -> Unit) : HttpCodeDsl<T> {
        on404 = action
        return this
    }

    fun on500(action: (Exception) -> Unit) : HttpCodeDsl<T> {
        on500 = action
        return this
    }

    fun on403(action: (Exception) -> Unit) : HttpCodeDsl<T> {
        on403 = action
        return this
    }

    fun onUnknownResponse(action: () -> Unit) : HttpCodeDsl<T> {
        onUnknownResponse = action
        return this
    }
}

inline fun <T> HttpCodeDsl<T>.handleSuccess(response: T) = this.on200(response)

inline fun <T> HttpCodeDsl<T>.handleError(exception: Exception) = when(exception) {
    is HttpCode.On400 -> on400(exception)
    is HttpCode.On404 -> on400(exception)
    is HttpCode.On500 -> on400(exception)
    is HttpCode.On403 -> on400(exception)
    else -> onUnknownResponse()
}

sealed class HttpCode : Exception() {
    open class On400 : HttpCode()
    open class On404 : HttpCode()
    open class On500 : HttpCode()
    open class On403 : HttpCode()
}