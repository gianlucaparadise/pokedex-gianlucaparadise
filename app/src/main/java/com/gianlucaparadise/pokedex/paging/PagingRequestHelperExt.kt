package com.gianlucaparadise.pokedex.paging

import com.gianlucaparadise.pokedex.repository.NetworkState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

private fun getErrorMessage(report: PagingRequestHelper.StatusReport): String {
    return PagingRequestHelper.RequestType.values().mapNotNull {
        report.getErrorFor(it)?.message
    }.first()
}

@ExperimentalCoroutinesApi
fun PagingRequestHelper.createStatusAsFlow(): Flow<NetworkState> {
    val flow = MutableStateFlow<NetworkState>(NetworkState.IDLE)
    addListener { report ->
        when {
            report.hasRunning() -> flow.value = NetworkState.LOADING
            report.hasError() -> flow.value = NetworkState.error(getErrorMessage(report)
            )
            else -> flow.value = NetworkState.LOADED
        }
    }
    return flow
}