package com.gianlucaparadise.pokedex.ui.detail

import io.uniflow.core.flow.data.UIEvent

sealed class DetailEvent : UIEvent() {
    object Loading : DetailEvent()
    object NotLoading : DetailEvent()
}