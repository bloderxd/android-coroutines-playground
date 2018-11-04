package bloder.com.presentation

import bloder.com.domain.search

class SearchViewModel : AppViewModel<SearchState>() {

    fun search() = run {
        searchUser()
    } exception {
        dispatch(SearchState.OnError)
    }

    private suspend fun searchUser() = interactor.search().onResponse {
        on200 {
            dispatch(SearchState.OnSearched(it))
        }
        onUnknownResponse {

        }
    }
}