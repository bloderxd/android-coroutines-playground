package bloder.com.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bloder.com.domain.UseCase
import bloder.com.domain.concurrency.defaultCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

open class AppViewModel<State> : ViewModel(), CoroutineScope by defaultCoroutineScope() {

    protected val interactor by lazy { UseCase() }

    private val state: MutableLiveData<State> = MutableLiveData()

    fun state(): LiveData<State> = state

    protected fun dispatch(state: State) {
        this.state.postValue(state)
    }

    override fun onCleared() {
        coroutineContext.cancel()
    }
}