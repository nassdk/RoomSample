package com.test.roomsample.feature.teams.domain

import com.test.roomsample.feature.teams.domain.usecase.LoadTeamsUseCase

interface TeamsUseCaseProvider {
    fun getTeamsUseCase(): LoadTeamsUseCase
}