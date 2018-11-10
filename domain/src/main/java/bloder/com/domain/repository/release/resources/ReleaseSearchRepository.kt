package bloder.com.domain.repository.release.resources

import bloder.com.domain.api.SearchApi
import bloder.com.domain.repository.resources.SearchRepository
import bloder.com.domain.result.HttpResult
import bloder.com.domain.values.Search

class ReleaseSearchRepository : SearchRepository {

    override suspend fun search(query: String) : HttpResult<Search> {
        val searchPayload = SearchApi().service().search().await()
        return HttpResult.requestError(400, SearchException())
    }

    override suspend fun request1(query: String): HttpResult<String> = HttpResult.requestError(400, Request1Exception())

    override suspend fun request2(oldSearch: String): HttpResult<String> = HttpResult.success("")
}

class Request1Exception : Exception("Request 1 Exception")
class SearchException : Exception("")