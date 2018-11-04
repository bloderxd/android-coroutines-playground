package bloder.com.domain.payloads

import bloder.com.domain.values.Search
import com.google.gson.annotations.SerializedName

data class SearchPayload(@SerializedName("title") private val name: String) {

    fun toValue() : Search = Search(name)
}