package bloder.com.domain

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class UseCase {

    suspend fun <T> runAsync(action: suspend () -> T) : T = GlobalScope.async {
        action()
    }.await()
}