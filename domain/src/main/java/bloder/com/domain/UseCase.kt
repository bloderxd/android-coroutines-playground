package bloder.com.domain

import kotlinx.coroutines.*

open class UseCase {

    suspend fun <T> execute(action: suspend () -> T) : T = currentScope {
        async(Dispatchers.IO) { action() }.await()
    }
}