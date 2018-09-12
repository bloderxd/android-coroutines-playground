package bloder.com.presentation

import bloder.com.domain.values.Search

sealed class SearchState {

    class onSearched(val search: Search) : SearchState()
}