package bloder.com.domain

import kotlinx.coroutines.*

open class UseCase {

    suspend fun <T> execute(action: suspend () -> T) : T = coroutineScope {
        async(Dispatchers.IO) { action() }.await()
    }
}