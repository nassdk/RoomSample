package com.test.roomsample.feature.live.domain

import com.test.roomsample.feature.live.domain.usecase.LoadLiveScoresUseCase

interface LiveScoresUseCaseProvider {
    fun getLiveScoresUseCase(): LoadLiveScoresUseCase
}