package com.test.roomsample.feature.live.presentation

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
import com.test.roomsample.feature.live.domain.model.LiveScoreMatchModel
import com.test.roomsample.feature.live.domain.usecase.LoadLiveScoresUseCase
import com.test.roomsample.feature.live.presentation.LiveScoresStore.*
import com.test.roomsample.library.coreui.error.UiErrorHandler
import javax.inject.Inject

class LiveScoresStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val loadLiveScoresUseCase: LoadLiveScoresUseCase,
    private val errorHandler: UiErrorHandler
) {

    fun create(): LiveScoresStore =
        object : LiveScoresStore, Store<Intent, State, Label> by storeFactory.create(
            name = "LiveScoresStore",
            initialState = State(),
            bootstrapper = null,
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl()
        ) {}

    private inner class ExecutorImpl : SuspendExecutor<Intent, Action, State, Result, Label>() {

        override suspend fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                Intent.Idle -> Unit
                is Intent.LoadLiveScores -> loadLiveScores(countryId = intent.countryId)
            }
        }

        private suspend fun loadLiveScores(countryId: String) {

            dispatch(result = Result.Loading)

            try {
                val liveScores = loadLiveScoresUseCase.invoke(countryId = countryId)
                dispatch(result = Result.LiveScoresLoaded(liveScores = liveScores))
            } catch (throwable: Exception) {
                errorHandler.proceedError(
                    throwable = throwable,
                    errorListener = { message ->
                        publish(label = Label.Error(message = message))
                    }
                )
            } finally {
                dispatch(result = Result.StopLoading)
            }
        }
    }

    private class ReducerImpl : Reducer<State, Result> {

        override fun State.reduce(result: Result) = when (result) {
            Result.Loading -> copy(loading = true)
            Result.StopLoading -> copy(loading = false)
            is Result.LiveScoresLoaded -> copy(liveScores = result.liveScores)
        }
    }

    class Action

    sealed class Result {
        object Loading : Result()
        object StopLoading : Result()
        data class LiveScoresLoaded(val liveScores: List<LiveScoreMatchModel>) : Result()
    }
}