package bloder.com.domain.result

inline class HttpResult<T>(private val result: Result<T>) {

    companion object {
        inline fun <T> success(value : T) : HttpResult<T> = HttpResult(Result.ok(value))
        inline fun <T> requestError(exception: Exception) : HttpResult<T> = HttpResult(Result(exception))
    }

    fun onResponse(action: HttpCodeDsl<T>.() -> Unit) : HttpResult<T> = HttpCodeDsl<T>().let { instance ->
        instance.action()
        when(result.get()) {
            is Result.Failure -> instance.handleError((result.get() as Result.Failure).exception)
            else -> instance.handleSuccess(result.get() as T)
        }
        return@let this
    }

    fun getResult() : Result<T> = result
}

inline fun <T> httpResult(response: T) : HttpResult<T> = HttpResult(Result(response))

class HttpCodeDsl<T> {

    private lateinit var on200: (T) -> Unit
    private lateinit var on400: (Exception) -> Unit
    private lateinit var on404: (Exception) -> Unit
    private lateinit var on500: (Exception) -> Unit
    private lateinit var on403: (Exception) -> Unit
    private lateinit var onUnknownResponse: () -> Unit

    fun handleSuccess(response: T) = this.on200(response)

    fun handleError(exception: Exception) = when(exception) {
        is HttpCode.On400 -> on400(exception)
        is HttpCode.On404 -> on400(exception)
        is HttpCode.On500 -> on400(exception)
        is HttpCode.On403 -> on400(exception)
        else -> onUnknownResponse()
    }

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

sealed class HttpCode : Exception() {
    open class On400 : HttpCode()
    open class On404 : HttpCode()
    open class On500 : HttpCode()
    open class On403 : HttpCode()
}