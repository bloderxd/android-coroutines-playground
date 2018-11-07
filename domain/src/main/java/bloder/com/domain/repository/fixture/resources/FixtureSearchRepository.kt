package bloder.com.domain.repository.fixture.resources

import bloder.com.domain.repository.resources.SearchRepository
import bloder.com.domain.result.HttpResult
import bloder.com.domain.values.Search

class FixtureSearchRepository : SearchRepository {

    override suspend fun search(query: String): HttpResult<Search> {
        return HttpResult.success(Search("Test Bloder"))
    }
}