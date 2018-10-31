package bloder.com.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bloder.com.domain.UseCase
import kotlinx.coroutines.*
import kotlin.Exception
import kotlin.coroutines.CoroutineContext

open class AppViewModel<State> : ViewModel(), CoroutineScope {

    protected val interactor by lazy { UseCase() }

    private val scopeJob by lazy { Job() }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + scopeJob

    private val state: MutableLiveData<State> = MutableLiveData()

    fun state() : LiveData<State> = state

    protected fun dispatch(state: State) {
        this.state.postValue(state)
    }

    protected fun run(run: suspend () -> Any) : CoroutineAction = CoroutineAction(run)

    protected infix fun CoroutineAction.whenError(onError: (Exception) -> Unit) = launch { withContext(Dispatchers.Main) {
        try {
            this@whenError.action()
        } catch (e: Exception) {
            onError(e)
        }
    }}

    override fun onCleared() {
        coroutineContext.cancel()
    }
}

inline class CoroutineAction(val action: suspend () -> Any)