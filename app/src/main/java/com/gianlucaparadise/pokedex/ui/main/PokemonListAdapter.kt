package com.gianlucaparadise.pokedex.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gianlucaparadise.pokedex.databinding.PokemonListItemBinding
import com.gianlucaparadise.pokedex.vo.main.PokemonListItem
import java.util.*

typealias PokemonClickHandler = (PokemonListItem, PokemonListAdapter.ViewHolder) -> Unit

class PokemonListAdapter(private val onPokemonClicked: PokemonClickHandler?) :
    PagingDataAdapter<PokemonListItem, PokemonListAdapter.ViewHolder>(PokemonDiffCallback()) {

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

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isNotEmpty()) {
            Log.d("PokemonListAdapter", "onBindViewHolder: Payload is empty")
        }
        else {
            onBindViewHolder(holder, position)
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
                name = pokemon.name.capitalize(Locale.getDefault())
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