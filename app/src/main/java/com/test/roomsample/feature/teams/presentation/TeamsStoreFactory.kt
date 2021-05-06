package com.test.roomsample.feature.teams.presentation

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
import com.test.roomsample.feature.teams.domain.model.TeamModel
import com.test.roomsample.feature.teams.domain.usecase.LoadTeamsUseCase
import com.test.roomsample.feature.teams.presentation.TeamsStore.*
import com.test.roomsample.library.coreui.error.UiErrorHandler
import javax.inject.Inject

class TeamsStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val loadTeamsUseCase: LoadTeamsUseCase,
    private val errorHandler: UiErrorHandler
) {

    fun create(): TeamsStore =
        object : TeamsStore,
            Store<Intent, State, Label> by storeFactory.create(
                name = "TeamsStore",
                initialState = State(),
                bootstrapper = null,
                executorFactory = ::ExecutorImpl,
                reducer = ReducerImpl()
            ) {}

    private inner class ExecutorImpl :
        SuspendExecutor<Intent, Action, State, Result, Label>() {

        override suspend fun executeIntent(
            intent: Intent,
            getState: () -> State
        ) {
            when (intent) {
                Intent.Idle -> Unit
                is Intent.LoadTeamList -> loadLiveScores(countryId = intent.countryId)
            }
        }

        private suspend fun loadLiveScores(countryId: String) {

            dispatch(result = Result.Loading)

            try {
                val teams = loadTeamsUseCase.invoke(countryId = countryId)
                dispatch(result = Result.TeamsLoaded(teams = teams))
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
            is Result.TeamsLoaded -> copy(teamList = result.teams)
        }
    }

    class Action

    sealed class Result {
        object Loading : Result()
        object StopLoading : Result()
        data class TeamsLoaded(val teams: List<TeamModel>) : Result()
    }
}