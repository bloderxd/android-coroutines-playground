package bloder.com.presentation

import bloder.com.domain.values.Search

sealed class SearchState {

    class OnSearched(val search: Search) : SearchState()
    class OnSearched2(val search: Search) : SearchState()
    class HttpError(val reason: String) : SearchState()
    class HttpError2(val reason: String) : SearchState()
    object OnError : SearchState()
    object OnError2 : SearchState()
}