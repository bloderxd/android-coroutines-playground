package bloder.com.domain.repository.release.resources

import bloder.com.domain.api.SearchApi
import bloder.com.domain.repository.resources.SearchRepository
import bloder.com.domain.values.Search

class ReleaseSearchRepository : SearchRepository {

    override suspend fun search(query: String) : Search = SearchApi().service().search("").await()
}