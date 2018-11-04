package bloder.com.domain.api.search

import bloder.com.domain.payloads.SearchPayload
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface SearchServices {

    @GET("todos/1") fun search() : Deferred<SearchPayload>
}