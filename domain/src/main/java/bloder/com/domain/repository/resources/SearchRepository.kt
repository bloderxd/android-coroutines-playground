package bloder.com.domain.repository.resources

import bloder.com.domain.result.HttpResult
import bloder.com.domain.values.Search

interface SearchRepository {

    suspend fun search(query: String) : HttpResult<Search>
    suspend fun request1(query: String) : HttpResult<String>
    suspend fun request2(oldSearch: String) : HttpResult<String>
}