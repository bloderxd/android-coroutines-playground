package bloder.com.domain

import bloder.com.domain.repository.releaseRepository
import bloder.com.domain.values.Search

suspend fun UseCase.search() : Search = this.runAsync {
    releaseRepository().forSearch().search("").getResponse()
}

suspend fun UseCase.getStep1(query: String) : String = this.runAsync {
    releaseRepository().forSearch().request1(query).getResponse()
}

suspend fun UseCase.getStep2(oldQuery: String) : String = this.runAsync {
    releaseRepository().forSearch().request2(oldQuery).getResponse()
}

fun UseCase.test1() : String = "Daniel"