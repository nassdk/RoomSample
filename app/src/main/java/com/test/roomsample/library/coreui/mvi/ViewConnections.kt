package com.test.roomsample.library.coreui.mvi

interface ViewConnections<State : Any, Intent : Any, Model : Any, Event : Any> {
    val stateToModel: (State) -> Model
    val eventToIntent: (Event) -> Intent
}