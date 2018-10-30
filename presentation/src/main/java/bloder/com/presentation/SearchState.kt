package bloder.com.presentation

import bloder.com.domain.values.Search

sealed class SearchState {

    class OnSearched(val search: Search) : SearchState()
}