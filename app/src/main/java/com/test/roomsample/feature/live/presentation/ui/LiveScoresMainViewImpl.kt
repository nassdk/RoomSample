package com.test.roomsample.feature.live.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arkivanov.mvikotlin.core.utils.diff
import com.arkivanov.mvikotlin.core.view.BaseMviView
import com.arkivanov.mvikotlin.core.view.ViewRenderer
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.test.roomsample.databinding.ItemLiveScoreBinding
import com.test.roomsample.databinding.ViewLiveScoresBinding
import com.test.roomsample.feature.live.domain.model.LiveScoreMatchModel
import com.test.roomsample.feature.live.presentation.ui.LiveScoresMainView.Event
import com.test.roomsample.feature.live.presentation.ui.LiveScoresMainView.Model

class LiveScoresMainViewImpl(
    root: ViewGroup,
    attachToRoot: Boolean = true
) : BaseMviView<Model, Event>() {

    private val viewBinding: ViewLiveScoresBinding = ViewLiveScoresBinding.inflate(
        LayoutInflater.from(root.context), root, attachToRoot
    )

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        ListDelegationAdapter(lifeScoresDelegate())
    }

    init {
        viewBinding.recyclerLiveScores.adapter = adapter
    }

    override val renderer: ViewRenderer<Model> = diff {
        diff(get = Model::loading, set = ::updateLoadingState)
        diff(get = Model::liveScores, set = ::applyLiveScores)
    }

    private fun applyLiveScores(liveScores: List<LiveScoreMatchModel>) {

        with(adapter) {
            items = liveScores
            notifyDataSetChanged()
        }
    }

    private fun updateLoadingState(loading: Boolean) {
        viewBinding.progressContainer.visibility = if (loading) View.VISIBLE else View.GONE
    }

    private fun lifeScoresDelegate() =
        adapterDelegateViewBinding<LiveScoreMatchModel, LiveScoreMatchModel, ItemLiveScoreBinding>(
            { layoutInflater: LayoutInflater, parent: ViewGroup ->
                ItemLiveScoreBinding.inflate(layoutInflater, parent, false)
            }
        ) {

            bind {
                with(binding) {
                    homeTeam.text = item.homeTeam
                    guestTeam.text = item.guestTeam
                    score.text = item.score
                    time.text = "${item.time}'"
                    location.text = item.location
                }
            }
        }
}