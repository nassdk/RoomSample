package com.test.roomsample.feature.teams.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arkivanov.mvikotlin.core.utils.diff
import com.arkivanov.mvikotlin.core.view.BaseMviView
import com.arkivanov.mvikotlin.core.view.ViewRenderer
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.test.roomsample.databinding.ItemTeamBinding
import com.test.roomsample.databinding.ViewLiveScoresBinding
import com.test.roomsample.feature.teams.domain.model.TeamModel
import com.test.roomsample.feature.teams.presentation.ui.TeamsMainView.Event
import com.test.roomsample.feature.teams.presentation.ui.TeamsMainView.Model

class TeamsMainViewImpl(
    root: ViewGroup,
    attachToRoot: Boolean = true
) : BaseMviView<Model, Event>() {

    private val viewBinding: ViewLiveScoresBinding = ViewLiveScoresBinding.inflate(
        LayoutInflater.from(root.context), root, attachToRoot
    )

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        ListDelegationAdapter(teamsDelegate())
    }

    init {
        viewBinding.recyclerLiveScores.adapter = adapter
    }

    override val renderer: ViewRenderer<Model> = diff {
        diff(get = Model::loading, set = ::updateLoadingState)
        diff(get = Model::teamsList, set = ::applyTeamList)
    }

    private fun applyTeamList(teams: List<TeamModel>) {

        with(adapter) {
            items = teams
            notifyDataSetChanged()
        }
    }

    private fun updateLoadingState(loading: Boolean) {
        viewBinding.progressContainer.visibility = if (loading) View.VISIBLE else View.GONE
    }

    private fun teamsDelegate() =
        adapterDelegateViewBinding<TeamModel, TeamModel, ItemTeamBinding>(
            { layoutInflater: LayoutInflater, parent: ViewGroup ->
                ItemTeamBinding.inflate(layoutInflater, parent, false)
            }
        ) {

            bind {
                with(binding) {
                    name.text = item.name
                }
            }
        }
}