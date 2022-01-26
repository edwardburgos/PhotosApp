package com.example.photosapp.overview

import com.example.photosapp.databinding.FragmentOverviewBinding
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import org.koin.androidx.viewmodel.ext.android.viewModel


class OverviewFragment : Fragment() {

    lateinit var binding: FragmentOverviewBinding

//    private val viewModel: OverviewViewModel by lazy {
//        ViewModelProvider(this).get(OverviewViewModel::class.java)
//    }

    val viewModel: OverviewViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOverviewBinding.inflate(inflater)

        val application = requireNotNull(activity).application

        binding.lifecycleOwner = viewLifecycleOwner

//        val viewModelFactory = OverviewViewModelFactory(application)
//        val overviewViewModel = ViewModelProvider(this, viewModelFactory)
//            .get(OverviewViewModel::class.java)

       binding.viewModel = viewModel


        binding.postsGrid.adapter = PhotosAdapter(PhotosAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })

        viewModel.navigateToSelectedPhoto.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                Navigation.findNavController(requireView())
                    .navigate(OverviewFragmentDirections.actionShowDetail(it))
                viewModel.displayPropertyDetailsComplete()
            }
        })

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getPhotos(false)
            binding.swipeRefreshLayout.setRefreshing(false);
        }

        setHasOptionsMenu(true)

        return binding.root
    }
}
