package bloder.com.domain.repository

import bloder.com.domain.repository.fixture.FixtureRepository
import bloder.com.domain.repository.release.ReleaseRepository

internal class Release(val factory: RepositoryFactory = ReleaseRepository())
internal class Fixture(val factory: RepositoryFactory = FixtureRepository())

fun releaseRepository() : RepositoryFactory = Release().factory
fun fixtureRepository() : RepositoryFactory = Fixture().factory