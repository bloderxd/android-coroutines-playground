package bloder.com.domain.repository.release.resources

import bloder.com.domain.api.SearchApi
import bloder.com.domain.payloads.Binder
import bloder.com.domain.repository.resources.SearchRepository
import bloder.com.domain.values.Search

class ReleaseSearchRepository : SearchRepository {

    override suspend fun search(query: String) : Binder<Search, Void> {
        val searchPayload = SearchApi().service().search("").await()
        return Binder(searchPayload.toValue(), null)
    }
}