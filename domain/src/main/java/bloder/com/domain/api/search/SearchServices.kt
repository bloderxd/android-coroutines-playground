package bloder.com.domain.api.search

import bloder.com.domain.values.Search
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface SearchServices {

    @GET("") fun search(query: String) : Deferred<Search>
}