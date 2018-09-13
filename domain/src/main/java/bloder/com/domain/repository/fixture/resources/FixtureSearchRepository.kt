package bloder.com.domain.repository.fixture.resources

import bloder.com.domain.payloads.Binder
import bloder.com.domain.repository.resources.SearchRepository
import bloder.com.domain.values.Search

class FixtureSearchRepository : SearchRepository {

    override suspend fun search(query: String): Binder<Search, Void> {
        return Binder(null, null)
    }
}