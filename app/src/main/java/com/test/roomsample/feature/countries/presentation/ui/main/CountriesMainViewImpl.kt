package com.test.roomsample.feature.countries.presentation.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arkivanov.mvikotlin.core.utils.diff
import com.arkivanov.mvikotlin.core.view.BaseMviView
import com.arkivanov.mvikotlin.core.view.ViewRenderer
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.test.roomsample.databinding.ItemCountryBinding
import com.test.roomsample.databinding.ViewCountriesBinding
import com.test.roomsample.feature.countries.domain.model.CountryModel
import com.test.roomsample.feature.countries.presentation.ui.main.CountriesMainView.Event
import com.test.roomsample.feature.countries.presentation.ui.main.CountriesMainView.Model

class CountriesMainViewImpl(
    root: ViewGroup,
    attachToRoot: Boolean = true
) : BaseMviView<Model, Event>() {

    private val viewBinding: ViewCountriesBinding = ViewCountriesBinding.inflate(
        LayoutInflater.from(root.context), root, attachToRoot
    )

    private val adapter by lazy {
        ListDelegationAdapter(
            countriesDelegate { id ->
                dispatch(event = Event.CountrySelected(id = id))
            }
        )
    }

    init {
        viewBinding.recyclerCountries.adapter = adapter
    }

    override val renderer: ViewRenderer<Model> = diff {
        diff(get = Model::countries, set = ::applyCountries)
        diff(get = Model::loading, set = ::updateLoadingState)
    }

    private fun applyCountries(countries: List<CountryModel>) {

        with(adapter) {
            items = countries
            notifyDataSetChanged()
        }
    }

    private fun updateLoadingState(loading: Boolean) {
        viewBinding.progressContainer.visibility = if (loading) View.VISIBLE else View.GONE
    }

    private fun countriesDelegate(
        onCountriesSelected: (id: String) -> Unit
    ) = adapterDelegateViewBinding<CountryModel, CountryModel, ItemCountryBinding>(
        { layoutInflater, parent -> ItemCountryBinding.inflate(layoutInflater, parent, false) }
    ) {

        binding.root.setOnClickListener {
            onCountriesSelected.invoke(item.id)
        }

        bind {
            binding.root.text = item.name
        }
    }
}