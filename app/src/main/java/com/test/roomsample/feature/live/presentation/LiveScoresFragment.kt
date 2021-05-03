package com.test.roomsample.feature.live.presentation

import android.widget.Toast
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.lifecycle.asMviLifecycle
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.test.roomsample.R
import com.test.roomsample.databinding.ScreenLiveScoreBinding
import com.test.roomsample.feature.live.presentation.di.LiveScoresComponent
import com.test.roomsample.feature.live.presentation.ui.LiveScoresMainViewImpl
import com.test.roomsample.feature.live.presentation.ui.LiveScoresViewConnections
import com.test.roomsample.library.coreui.base.BaseFragment
import com.test.roomsample.library.coreui.navigation.Screens
import me.aartikov.alligator.ScreenResolver
import javax.inject.Inject

class LiveScoresFragment : BaseFragment(R.layout.screen_live_score) {

    @Inject lateinit var store: LiveScoresStore
    @Inject lateinit var screenResolver: ScreenResolver

    private val viewBinding: ScreenLiveScoreBinding
        get() = _viewBinding!!

    private var _viewBinding: ScreenLiveScoreBinding? = null

    private val screen by lazy { screenResolver.getScreen<Screens.LiveScore>(this) }

    override fun invokeDi() {

        LiveScoresComponent
            .create(requireActivity())
            .inject(this)
    }

    override fun initBindings() {

        _viewBinding = ScreenLiveScoreBinding.bind(requireView())

        bind(this@LiveScoresFragment.lifecycle.asMviLifecycle(), BinderLifecycleMode.START_STOP) {
            store.labels.bindTo { label ->
                when (label) {
                    is LiveScoresStore.Label.Error -> showError(message = label.message)
                }
            }
        }
    }

    override fun prepareUi() {

        LiveScoresMainViewImpl(
            root = viewBinding.mainContainer
        ).bind(store, LiveScoresViewConnections)

        store.accept(intent = LiveScoresStore.Intent.LoadLiveScores(countryId = screen.countryId))
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