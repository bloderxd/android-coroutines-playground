package bloder.com.presentation

import bloder.com.domain.getStep1
import bloder.com.domain.getStep2
import bloder.com.domain.repository.release.resources.Request1Exception
import bloder.com.domain.repository.release.resources.SearchException
import bloder.com.domain.result.HttpCodeException
import bloder.com.domain.result.is400
import bloder.com.domain.search
import bloder.com.domain.test1
import bloder.com.domain.values.Search

class SearchViewModel : AppViewModel<SearchState>() {

    fun search() = runInBackgroundWithException {
        arrayOf(1).isArrayOf<Int>()
        val search = interactor.search()
        val step1 = interactor.getStep1(search.name)
        val step2 = interactor.getStep2(step1)
        dispatch(SearchState.OnSearched(Search("$step2 + ${interactor.test1()}")))
    } onError { when(it) {
        is HttpCodeException -> {
            it.is400<SearchException> {
                dispatch(SearchState.HttpError("Search Exception"))
            }
            it.is400<Request1Exception> {
                dispatch(SearchState.HttpError("Request 1 Exception"))
            }
        }
        else -> dispatch(SearchState.OnError)
    }}

    fun search2() = runInBackgroundWithException {
        val step1 = interactor.getStep1("")
        val step2 = interactor.getStep2(step1)
        dispatch(SearchState.OnSearched2(Search("$step2 + ${interactor.test1()}")))
    } onError {when(it) {
        is HttpCodeException -> {
            it.is400<SearchException> {
                dispatch(SearchState.HttpError2("Search Exception"))
            }
            it.is400<Request1Exception> {
                dispatch(SearchState.HttpError2("Request 1 Exception"))
            }
        }
        else -> dispatch(SearchState.OnError2)
    }}
}