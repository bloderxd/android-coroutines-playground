package bloder.com.domain

import bloder.com.domain.result.HttpResult
import bloder.com.domain.repository.releaseRepository
import bloder.com.domain.values.Search

suspend fun UseCase.search() : HttpResult<Search> = this.execute {
    releaseRepository().forSearch().search("")
}