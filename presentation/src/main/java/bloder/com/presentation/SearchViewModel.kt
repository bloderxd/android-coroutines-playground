package bloder.com.presentation

import bloder.com.domain.search

class SearchViewModel : AppViewModel<SearchState>() {

    fun search() = runInBackground {
        dispatch(SearchState.OnSearched(interactor.search()))
    } onError { when(it) {}}
}