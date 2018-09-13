package bloder.com.domain.payloads

typealias Binder<R, E> = Response<R, E>

class Response<R, E>(
        val response: R?,
        val error: E?
) {

    fun resolve(init: ResponseBinder<R, E>.() -> Any) {
        val instance = ResponseBinder(response, error)
        instance.init()
        response?.let { instance.success(it) }
        error?.let { instance.failed(it) }
    }
}

class ResponseBinder<R, E>(private val response: R?, private val error: E?) {

    var success: (R) -> Any? = {}
    var failed: (E) -> Any? = {}

    fun onResponse(action: (R) -> Any?) {
        success = action
    }

    fun onError(action: (E) -> Any?) {
        failed = action
    }
}