package bloder.com.domain.repository.resources

import bloder.com.domain.payloads.Result
import bloder.com.domain.values.Search

interface SearchRepository {

    suspend fun search(query: String) : Result<Search, Void>
}