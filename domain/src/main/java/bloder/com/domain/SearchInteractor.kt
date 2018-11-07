package bloder.com.domain

import bloder.com.domain.repository.releaseRepository
import bloder.com.domain.values.Search

suspend fun UseCase.search() : Search = this.runAsync {
    releaseRepository().forSearch().search("").getResponse()
}