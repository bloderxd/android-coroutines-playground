package bloder.com.domain.repository.resources

import bloder.com.domain.payloads.Binder
import bloder.com.domain.values.Search

interface SearchRepository {

    suspend fun search(query: String) : Binder<Search, Void>
}