package bloder.com.domain.repository.release

import bloder.com.domain.repository.RepositoryFactory
import bloder.com.domain.repository.release.resources.ReleaseSearchRepository
import bloder.com.domain.repository.resources.SearchRepository

class ReleaseRepository : RepositoryFactory {

    override fun forSearch(): SearchRepository = ReleaseSearchRepository()
}