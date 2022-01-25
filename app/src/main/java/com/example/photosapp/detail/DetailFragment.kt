package com.example.photosapp.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.photosapp.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater)

        val application = requireNotNull(activity).application

        binding.lifecycleOwner = viewLifecycleOwner

        val postProperty = DetailFragmentArgs.fromBundle(requireArguments()).selectedPhoto

        val viewModelFactory = DetailViewModelFactory(postProperty, application )
        val detailViewModel = ViewModelProvider(this, viewModelFactory, )
            .get(DetailViewModel::class.java)
        binding.viewModel = detailViewModel

        return binding.root
    }
}