package com.gianlucaparadise.pokedex.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gianlucaparadise.pokedex.databinding.PokemonListItemBinding
import com.gianlucaparadise.pokedex.vo.PokemonListItem

typealias PokemonClickHandler = (PokemonListItem, PokemonListAdapter.ViewHolder) -> Unit

class PokemonListAdapter(private val onPokemonClicked: PokemonClickHandler?) :
    PagedListAdapter<PokemonListItem, PokemonListAdapter.ViewHolder>(PokemonDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PokemonListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item, createOnClickListener(item, holder))
        }
    }

    private fun createOnClickListener(
        pokemon: PokemonListItem,
        holder: ViewHolder
    ): View.OnClickListener {
        return View.OnClickListener {
            onPokemonClicked?.invoke(pokemon, holder)
        }
    }

    class ViewHolder(
        private val binding: PokemonListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: PokemonListItem, listener: View.OnClickListener) {
            with(binding) {
                name = pokemon.name
                clickListener = listener
            }
        }
    }

    private class PokemonDiffCallback : DiffUtil.ItemCallback<PokemonListItem>() {

        override fun areItemsTheSame(
            oldItem: PokemonListItem,
            newItem: PokemonListItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: PokemonListItem,
            newItem: PokemonListItem
        ): Boolean {
            return oldItem.name == newItem.name
        }
    }
}