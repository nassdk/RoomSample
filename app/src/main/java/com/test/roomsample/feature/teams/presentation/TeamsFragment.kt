package com.test.roomsample.feature.teams.presentation

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.lifecycle.asMviLifecycle
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.test.roomsample.R
import com.test.roomsample.databinding.ScreenTeamsBinding
import com.test.roomsample.feature.teams.presentation.di.TeamsComponent
import com.test.roomsample.feature.teams.presentation.ui.TeamsMainViewImpl
import com.test.roomsample.feature.teams.presentation.ui.TeamsViewConnections
import com.test.roomsample.library.coreui.base.BaseFragment
import com.test.roomsample.library.coreui.navigation.Screens
import me.aartikov.alligator.ScreenResolver
import javax.inject.Inject

class TeamsFragment : BaseFragment(R.layout.screen_teams) {

    @Inject
    lateinit var store: TeamsStore

    @Inject
    lateinit var screenResolver: ScreenResolver

    private val viewBinding: ScreenTeamsBinding
        get() = _viewBinding!!

    private var _viewBinding: ScreenTeamsBinding? = null

    private val screen by lazy { screenResolver.getScreen<Screens.Teams>(this) }

    override fun invokeDi() {

        TeamsComponent
            .create(requireActivity() as AppCompatActivity)
            .inject(this)
    }

    override fun initBindings() {

        _viewBinding = ScreenTeamsBinding.bind(requireView())

        bind(this@TeamsFragment.lifecycle.asMviLifecycle(), BinderLifecycleMode.START_STOP) {
            store.labels.bindTo { label ->
                when (label) {
                    is TeamsStore.Label.Error -> showError(message = label.message)
                }
            }
        }
    }

    override fun prepareUi() {

        TeamsMainViewImpl(
            root = viewBinding.mainContainer
        ).bind(store, TeamsViewConnections)

        store.accept(intent = TeamsStore.Intent.LoadTeamList(countryId = screen.countryId))
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        store.dispose()
    }
}