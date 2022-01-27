package com.example.photosapp.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.*
import com.example.photosapp.databinding.FragmentDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailFragment : Fragment() {

    lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner

        val postProperty = DetailFragmentArgs.fromBundle(requireArguments()).selectedPhoto
        val viewModel: DetailViewModel by viewModel {
            parametersOf(postProperty)
        }
        binding.viewModel = viewModel

        val layoutManager = binding.photosSnap.layoutManager
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.photosSnap)
        binding.photosSnap.adapter = PhotosSnapAdapter()

        viewModel.photos.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                layoutManager?.scrollToPosition(100 - postProperty.id)
            }
        })

        binding.topAppBar.setNavigationOnClickListener {
            val navController = Navigation.findNavController(requireView())
            navController?.navigateUp()
        }

        return binding.root
    }
}