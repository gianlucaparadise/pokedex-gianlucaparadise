package com.gianlucaparadise.pokedex.ui.detail

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
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
import io.uniflow.androidx.flow.onEvents
import io.uniflow.androidx.flow.onStates
import io.uniflow.core.flow.data.UIState
import kotlinx.android.synthetic.main.detail_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailFragment : Fragment() {

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
                is DetailEvent.Loading -> {
                    progress_circular.isVisible = true
                }
                is DetailEvent.NotLoading -> {
                    progress_circular.isVisible = false
                    animateShowDetails()
                }
            }
        }

        hideDetails()
        viewModel.getPokemonDetail()
    }

    private fun updateState(state: DetailState) {
        activity?.title = state.name
        binding.state = state
    }

    private fun hideDetails() {
        val createAlphaAnimation = fun(v: View): ObjectAnimator {
            return ObjectAnimator.ofFloat(v, "alpha", 1f, 0f)
        }

        val animatorSet = AnimatorSet().apply {
            playTogether(
                createAlphaAnimation(img_pokemon),
                createAlphaAnimation(list_pokemon_type),
                createAlphaAnimation(list_pokemon_stats),
            )
            duration = 0
        }

        animatorSet.start()
    }

    private fun animateShowDetails() {
        val createAlphaAnimation = fun(v: View): ObjectAnimator {
            return ObjectAnimator.ofFloat(v, "alpha", 0f, 1f)
        }

        val animatorSet = AnimatorSet().apply {
            playTogether(
                createAlphaAnimation(img_pokemon),
                createAlphaAnimation(list_pokemon_type),
                createAlphaAnimation(list_pokemon_stats)
            )
            duration = 500
            startDelay = 500
        }

        animatorSet.start()
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