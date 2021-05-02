package com.test.roomsample.feature.countries.presentation

import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.lifecycle.asMviLifecycle
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.test.roomsample.R
import com.test.roomsample.databinding.ScreenCountriesBinding
import com.test.roomsample.feature.countries.presentation.di.CountriesComponent
import com.test.roomsample.feature.countries.presentation.ui.main.CountriesConnections
import com.test.roomsample.feature.countries.presentation.ui.main.CountriesMainViewImpl
import com.test.roomsample.feature.countries.presentation.ui.toolbar.CountriesToolbarConnections
import com.test.roomsample.feature.countries.presentation.ui.toolbar.CountriesToolbarViewImpl
import com.test.roomsample.library.coreui.base.BaseFragment
import javax.inject.Inject

class CountriesFragment : BaseFragment(R.layout.screen_countries) {

    @Inject
    lateinit var store: CountriesStore

    private var _viewBinding: ScreenCountriesBinding? = null
    private val viewBinding: ScreenCountriesBinding get() = _viewBinding!!

    override fun invokeDi() {

        CountriesComponent
            .create(requireActivity())
            .inject(this)
    }

    override fun initBindings() {

        _viewBinding = ScreenCountriesBinding.bind(requireView())

        bind(this@CountriesFragment.lifecycle.asMviLifecycle(), BinderLifecycleMode.START_STOP) {
            store.labels.bindTo { label ->
                when (label) {
                    is CountriesStore.Label.Error -> showError(message = label.message)
                }
            }
        }
    }

    override fun prepareUi() {

        CountriesToolbarViewImpl(
            root = viewBinding.toolbarContainer,
            coroutineScope = lifecycleScope
        ).bind(store = store, viewConnections = CountriesToolbarConnections)

        CountriesMainViewImpl(
            root = viewBinding.mainContainer
        ).bind(store = store, viewConnections = CountriesConnections)
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }
}