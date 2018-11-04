package bloder.com.presentation

import bloder.com.domain.search

class SearchViewModel : AppViewModel<SearchState>() {

    fun search(query: String) = run {
        val searchResponse = interactor.search(query)

    } exception  {
        dispatch(SearchState.OnError)
    }
}