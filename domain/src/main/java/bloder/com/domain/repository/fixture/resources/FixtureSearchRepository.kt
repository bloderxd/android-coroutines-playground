package bloder.com.domain.repository.fixture.resources

import bloder.com.domain.repository.resources.SearchRepository
import bloder.com.domain.result.HttpResult
import bloder.com.domain.values.Search

class FixtureSearchRepository : SearchRepository {

    override suspend fun search(query: String): HttpResult<Search> {
        return HttpResult.success(Search("Test Bloder"))
    }

    override suspend fun request1(query: String): HttpResult<String> {
        Thread.sleep(2000)
        return HttpResult.success("$query - step 1")
    }

    override suspend fun request2(oldSearch: String): HttpResult<String> {
        Thread.sleep(2000)
        return HttpResult.success("$oldSearch - step 2")
    }
}