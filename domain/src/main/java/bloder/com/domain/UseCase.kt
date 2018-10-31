package bloder.com.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class UseCase {

    suspend fun <T> execute(action: suspend () -> T) : T = GlobalScope.async {
        async(Dispatchers.IO) { action() }.await()
    }.await()
}