package com.gianlucaparadise.pokedex.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gianlucaparadise.pokedex.databinding.NetworkStateItemBinding

typealias RetryCallback = () -> Unit

class PokemonListLoadStateAdapter(
    private val onRetryCallback: RetryCallback
) : LoadStateAdapter<PokemonListLoadStateAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        return ViewHolder(
            NetworkStateItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bind(loadState, onRetryCallback)
    }

    /**
     * A View Holder that can display a loading or have click action.
     * It is used to show the network state of paging.
     */
    class ViewHolder(
        private val binding: NetworkStateItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState, retryCallback: () -> Unit) {
            with(binding) {
                progressBar.isVisible = loadState is LoadState.Loading

                with(errorMsg) {
                    val loadStateError = loadState as? LoadState.Error
                    val errorMessage = loadStateError?.error?.message ?: ""
                    isVisible = errorMessage.isNotBlank()
                    text = errorMessage
                }

                with(retryButton) {
                    isVisible = loadState is LoadState.Error
                    setOnClickListener { retryCallback() }
                }
            }
        }
    }
}