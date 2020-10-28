package com.gianlucaparadise.pokedex.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.gianlucaparadise.pokedex.R
import com.gianlucaparadise.pokedex.databinding.MainFragmentBinding
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
import kotlinx.coroutines.flow.collectLatest

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: MainFragmentBinding

    private val adapter
        get() = binding.pokemonList.adapter as? PokemonListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)

        binding.pokemonList.adapter = PokemonListAdapter(onPokemonClicked)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipe_refresh.setOnRefreshListener { this.adapter?.refresh() }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.title = getString(R.string.app_name)

        lifecycleScope.launch {
            adapter?.loadStateFlow?.collectLatest { loadStates ->
                swipe_refresh.isRefreshing = loadStates.refresh is LoadState.Loading
            }
        }

        lifecycleScope.launch {
            @OptIn(ExperimentalCoroutinesApi::class)
            viewModel.pokemonList.collectLatest {
                adapter?.submitData(it)
            }
        }
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