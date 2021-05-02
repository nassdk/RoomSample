package com.test.roomsample.feature.countries.presentation

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
import com.test.roomsample.feature.countries.domain.model.CountryModel
import com.test.roomsample.feature.countries.domain.usecase.LoadCountriesUseCase
import com.test.roomsample.feature.countries.presentation.CountriesStore.*
import com.test.roomsample.library.coreui.error.UiErrorHandler
import com.test.roomsample.library.coreui.navigation.Screens
import me.aartikov.alligator.Navigator
import java.io.IOException
import javax.inject.Inject

class CountriesStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val loadCountriesUseCase: LoadCountriesUseCase,
    private val navigator: Navigator,
    private val errorHandler: UiErrorHandler
) {
    fun create(): CountriesStore =
        object : CountriesStore, Store<Intent, State, Label> by storeFactory.create(
            name = "CountriesStore",
            initialState = State(),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl()
        ) {}

    private class BootstrapperImpl : SuspendBootstrapper<Action>() {

        override suspend fun bootstrap() {
            dispatch(action = Action.LoadCountries)
        }
    }

    private inner class ExecutorImpl : SuspendExecutor<Intent, Action, State, Result, Label>() {

        override suspend fun executeAction(action: Action, getState: () -> State) {
            when (action) {
                Action.LoadCountries -> loadCountries()
            }
        }

        override suspend fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.CountrySelected -> navigator.goForward(Screens.Flow(countryId = intent.countryId))
                is Intent.SearchQueryChange -> filterCountriesByQuery(query = intent.query, countries = getState().allCountries)
            }
        }

        private suspend fun loadCountries() {

            dispatch(result = Result.Loading)

            try {
                val countries = loadCountriesUseCase.invoke()
                dispatch(result = Result.CountriesLoaded(countries = countries))
            } catch (throwable: IOException) {
                errorHandler.proceedError(
                    throwable = throwable,
                    errorListener = { errorMessage ->
                        publish(label = Label.Error(message = errorMessage))
                    }
                )
            } finally {
                dispatch(result = Result.StopLoading)
            }
        }

        private fun filterCountriesByQuery(query: String, countries: List<CountryModel>) {

            val filteredCountries = countries.filter {
                it.name.contains(other = query, ignoreCase = true)
            }

            dispatch(result = Result.CountriesFilteredByQuery(countries = filteredCountries))
        }
    }

    private class ReducerImpl : Reducer<State, Result> {

        override fun State.reduce(result: Result) =
            when (result) {
                Result.Loading -> copy(loading = true)
                Result.StopLoading -> copy(loading = false)
                is Result.CountriesLoaded -> copy(allCountries = result.countries)
                is Result.CountriesFilteredByQuery -> copy(filteredCountries = result.countries)
            }
    }

    sealed class Action {
        object LoadCountries : Action()
    }

    sealed class Result {
        object Loading : Result()
        object StopLoading : Result()
        data class CountriesLoaded(val countries: List<CountryModel>) : Result()
        data class CountriesFilteredByQuery(val countries: List<CountryModel>) : Result()
    }
}