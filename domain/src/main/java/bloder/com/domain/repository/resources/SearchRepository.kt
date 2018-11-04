package bloder.com.domain.repository.resources

import bloder.com.domain.result.HttpResult
import bloder.com.domain.values.Search

interface SearchRepository {

    suspend fun search(query: String) : HttpResult<Search>
}