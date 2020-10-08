package com.gianlucaparadise.pokedex.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.gianlucaparadise.pokedex.R
import com.gianlucaparadise.pokedex.adapters.PokemonClickHandler
import com.gianlucaparadise.pokedex.adapters.PokemonListAdapter
import com.gianlucaparadise.pokedex.databinding.MainFragmentBinding
import com.gianlucaparadise.pokedex.repository.Status
import com.gianlucaparadise.pokedex.vo.PokemonListItem
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)

        val adapter = PokemonListAdapter(onPokemonClicked)
        binding.pokemonList.adapter = adapter

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.pokemonList.observe(viewLifecycleOwner, Observer { result ->
            val adapter = binding.pokemonList.adapter
            if (adapter is PokemonListAdapter) {
                adapter.submitList(result)
            }
        })

        viewModel.networkState.observe(viewLifecycleOwner, Observer { state ->
            if (state.status == Status.FAILED) {
                Snackbar
                    .make(requireView(), R.string.network_error, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.ok) {
                        // no-op
                    }
                    .show()
            }
        })
    }

    private val onPokemonClicked: PokemonClickHandler = { pokemonListItem, holder ->
        val name = holder.itemView.findViewById<View>(R.id.pokemon_name).apply {
            transitionName = "pokemonName"
        }

        val extras = FragmentNavigatorExtras(
            name to name.transitionName
        )

        val action = MainFragmentDirections.actionMainFragmentToDetailFragment(pokemonListItem)
        findNavController().navigate(action, extras)
    }

}