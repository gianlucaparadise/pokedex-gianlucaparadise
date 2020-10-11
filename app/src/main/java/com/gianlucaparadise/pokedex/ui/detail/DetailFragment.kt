package com.gianlucaparadise.pokedex.ui.detail

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.gianlucaparadise.pokedex.R
import com.gianlucaparadise.pokedex.databinding.DetailFragmentBinding
import com.google.android.material.snackbar.Snackbar
import io.uniflow.android.flow.onEvents
import io.uniflow.android.flow.onStates
import io.uniflow.core.flow.data.UIState
import kotlinx.android.synthetic.main.detail_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModel { parametersOf(args.pokemonListItem) }
    private lateinit var binding: DetailFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementReturnTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onStates(viewModel) { state ->
            when (state) {
                is DetailState -> updateState(state)
                is UIState.Failed -> showError()
            }
        }

        onEvents(viewModel) { event ->
            when (val data = event.take()) {
                is DetailEvent.Loading -> progress_circular.isVisible = true
                is DetailEvent.NotLoading -> progress_circular.isVisible = false
            }
        }

        viewModel.getPokemonDetail()
    }

    private fun updateState(state: DetailState) {
        activity?.title = state.name
        binding.state = state
    }

    private fun showError() {
        Snackbar
            .make(requireView(), R.string.pokemon_detail_network_error, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.ok) {
                // no-op
            }
            .show()
    }
}