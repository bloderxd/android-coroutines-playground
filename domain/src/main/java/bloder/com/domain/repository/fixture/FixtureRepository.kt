package bloder.com.domain.repository.fixture

import bloder.com.domain.repository.RepositoryFactory
import bloder.com.domain.repository.fixture.resources.FixtureSearchRepository
import bloder.com.domain.repository.resources.SearchRepository

class FixtureRepository : RepositoryFactory {

    override fun forSearch(): SearchRepository = FixtureSearchRepository()
}