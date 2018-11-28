package bloder.com.domain

import bloder.com.domain.concurrency.defaultCoroutineScope
import kotlinx.coroutines.CoroutineScope

class UseCase : CoroutineScope by defaultCoroutineScope()