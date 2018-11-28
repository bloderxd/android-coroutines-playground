package bloder.com.domain.concurrency

interface ConcurrencyScope {

    fun cancelJobs()
    fun runInBackgroundWithException(run: suspend () -> Unit) : ConcurrencyContinuation =
            ConcurrencyContinuation(run)
    suspend fun <T> runAsync(action: suspend () -> T) : T
    infix fun ConcurrencyContinuation.onError(onError: (Exception) -> Unit)
}

inline class ConcurrencyContinuation(val action: suspend () -> Unit)