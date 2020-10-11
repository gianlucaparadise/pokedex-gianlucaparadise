package com.gianlucaparadise.pokedex.repository

enum class Status {
    IDLE,
    RUNNING,
    SUCCESS,
    FAILED
}

data class NetworkState(
    val status: Status,
    val msg: String? = null) {

    companion object {
        val IDLE = NetworkState(Status.IDLE)
        val LOADED = NetworkState(Status.SUCCESS)
        val LOADING = NetworkState(Status.RUNNING)
        fun error(msg: String?) = NetworkState(Status.FAILED, msg)
    }

}