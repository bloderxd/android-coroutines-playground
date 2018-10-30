package bloder.com.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bloder.com.domain.UseCase
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

open class AppViewModel<State> : ViewModel(), CoroutineScope {

    protected val interactor = UseCase()

    private val scopeJob by lazy { Job() }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + scopeJob

    private val state: MutableLiveData<State> = MutableLiveData()

    fun state() : LiveData<State> = state

    protected fun dispatch(state: State) {
        this.state.postValue(state)
    }

    protected fun run(run: suspend () -> Any) = launch { withContext(Dispatchers.Main) { run() }}
}