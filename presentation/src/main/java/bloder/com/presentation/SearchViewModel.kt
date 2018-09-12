package bloder.com.presentation

import bloder.com.domain.search

class SearchViewModel : AppViewModel<SearchState>() {

    fun search(query: String) = run { dispatch(SearchState.onSearched(interactor.search(query))) }
}