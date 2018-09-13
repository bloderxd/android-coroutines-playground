package bloder.com.domain.api

import bloder.com.domain.api.search.SearchServices

class SearchApi : Api<SearchServices>() {
    override fun service(): SearchServices = retrofit("").create(SearchServices::class.java)
}