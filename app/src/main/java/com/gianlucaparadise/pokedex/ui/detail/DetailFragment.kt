package com.gianlucaparadise.pokedex.ui.detail

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.gianlucaparadise.pokedex.databinding.DetailFragmentBinding
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.title = viewModel.name
        binding.viewmodel = viewModel
    }

}