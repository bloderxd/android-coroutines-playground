package bloder.com.domain

import bloder.com.domain.values.Search

suspend fun UseCase.search(query: String) : Search = this.execute {
    Search("Bloder")
}