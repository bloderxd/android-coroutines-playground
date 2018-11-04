package bloder.com.domain

import bloder.com.domain.payloads.HttpResult
import bloder.com.domain.repository.releaseRepository
import bloder.com.domain.values.Search

suspend fun UseCase.search(query: String) : HttpResult<Search> = this.execute {
    releaseRepository().forSearch().search(query)
}