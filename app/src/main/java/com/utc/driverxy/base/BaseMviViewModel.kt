package com.utc.driverxy.base

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseMviViewModel<I : MviIntent, S : MviViewState, E : MviSingleEvent> : ViewModel() {
    private val _viewState: MutableStateFlow<S> = MutableStateFlow(initState())
    val viewState: StateFlow<S> = _viewState.asStateFlow()
    private val eventChannel = Channel<E>(Channel.UNLIMITED)
    val singleEvent: Flow<E> = eventChannel.receiveAsFlow()

    abstract fun initState(): S

    /**
     * Process user intentions
     * All business logic should be handled here
     */
    abstract fun processIntent(intent: I)

    /**
     * Update the current state
     * Should be called from processIntent implementations
     */
    protected fun updateState(newState: S) {
        _viewState.value = newState
    }

    /**
     * Update state using a reducer function
     */
    protected fun updateState(reducer: S.() -> S) {
        _viewState.value = _viewState.value.reducer()
    }

    /**
     * Send a one-time event
     */
    protected fun sendEvent(event: E) {
        viewModelScope.launch {
            eventChannel.trySend(event)
        }
    }

    /**
     * Get current state value
     */
    protected val currentState: S
        get() = _viewState.value

    /**
     * Restore state (useful for process death recovery)
     */
    open fun restoreState(state: S) {
        _viewState.value = state
    }

    /**
     * Reset state to initial state
     */
    open fun resetState() {
        _viewState.value = initState()
    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        eventChannel.close()
    }
}