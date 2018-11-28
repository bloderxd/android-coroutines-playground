package bloder.com.domain.concurrency

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class DefaultCoroutineScope : ConcurrencyScope, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    override fun cancelJobs() {
        coroutineContext.cancel()
    }

    override infix fun ConcurrencyContinuation.onError(onError: (Exception) -> Unit) {
        initCoroutine(this.action, onError)
    }

    override suspend fun <T> runAsync(action: suspend () -> T) : T = GlobalScope.async {
        action()
    }.await()

    private fun initCoroutine(run: suspend () -> Unit, onError: (Exception) -> Unit = {}) = launch { withContext(coroutineContext) {
        try {
            run()
        } catch (e: Exception) {
            onError(e)
        }
    }}
}

fun defaultCoroutineScope() : DefaultCoroutineScope = DefaultCoroutineScope()