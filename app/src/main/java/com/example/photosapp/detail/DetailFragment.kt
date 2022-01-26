package com.example.photosapp.detail

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.*
import androidx.recyclerview.widget.RecyclerView.SmoothScroller
import com.example.photosapp.databinding.FragmentDetailBinding
import com.example.photosapp.overview.OverviewFragmentDirections
import com.example.photosapp.overview.OverviewViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailFragment : Fragment() {

    lateinit var binding: FragmentDetailBinding

//    private val viewModel: DetailViewModel by lazy {
//        ViewModelProvider(this).get(DetailViewModel::class.java)
//    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater)

        val application = requireNotNull(activity).application

        binding.lifecycleOwner = viewLifecycleOwner
//
      val postProperty = DetailFragmentArgs.fromBundle(requireArguments()).selectedPhoto
        val viewModel: DetailViewModel by viewModel {
            parametersOf(postProperty)
        }

//
//        val viewModelFactory = DetailViewModelFactory(postProperty, application )
//        val detailViewModel = ViewModelProvider(this, viewModelFactory)
//            .get(DetailViewModel::class.java)
        binding.viewModel = viewModel

        val layoutManager = binding.photosSnap.layoutManager
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.photosSnap)
        //layoutManager?.rev



        //.smoothScrollToPosition(layoutManager.findLastVisibleItemPosition());
//        snapHelper.findSnapView(layoutManager) {
//            if (layoutManager.canScrollVertically()) {
//                return findFirstView(layoutManager, getVerticalHelper(layoutManager))
//            } else if (layoutManager.canScrollHorizontally()) {
//                return findFirstView(layoutManager, getHorizontalHelper(layoutManager))
//            }
//            return null
//        }
//        PagerSnapHelper().findSnapView(layoutManager)
//        println("${}")

//        val snapView = snapHelper.findSnapView(layoutManager)
//        val snapPosition = snapView?.let { layoutManager?.getPosition(it)  }
//        println("ROSALÍA ${snapView}")
//        println("ROSALÍA ff${snapPosition}")
//
//        fun PagerSnapHelper.getSnapPosition(recyclerView: RecyclerView): Int {
//            val layoutManager = recyclerView.layoutManager ?: return RecyclerView.NO_POSITION
//            val snapView = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
//            return layoutManager.getPosition(snapView)
//        }
//
//        var snapPosition = RecyclerView.NO_POSITION
//
//
//
//
//        fun maybeNotifySnapPositionChange(recyclerView: RecyclerView) {
//            val snapPosition = snapHelper.getSnapPosition(recyclerView)
//            val snapPositionChanged = this.snapPosition != snapPosition
//            if (snapPositionChanged) {
//                onSnapPositionChangeListener
//                    .onSnapPositionChange(snapPosition)
//                this.snapPosition = snapPosition
//            }
//        }
//        fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//            maybeNotifySnapPositionChange(recyclerView)
//        }
//
//
//        println("CARDI ${getSna}")
//        fun RecyclerView.attachSnapHelperWithListener(
//            snapHelper: SnapHelper,
//            behavior: SnapOnScrollListener.Behavior = SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL,
//            onSnapPositionChangeListener: OnSnapPositionChangeListener) {
//            snapHelper.attachToRecyclerView(this)
//            val snapOnScrollListener = SnapOnScrollListener(snapHelper, onSnapPositionChangeListener, behavior)
//            addOnScrollListener(snapOnScrollListener)
//        }

        //private var snapPosition = RecyclerView.NO_POSITION

//        fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//            maybeNotifySnapPositionChange(recyclerView)
//        }
//
//        private fun maybeNotifySnapPositionChange(recyclerView: RecyclerView) {
//            val snapPosition = snapHelper.getSnapPosition(recyclerView)
//            val snapPositionChanged = this.snapPosition != snapPosition
//            if (snapPositionChanged) {
//                onSnapPositionChangeListener
//                    .onSnapPositionChange(snapPosition)
//                this.snapPosition = snapPosition
//            }
//        }

//        val smoothScroller = LinearSmoothScroller(context) {
//
//            getVerticalSnapPreference() {
//                return LinearSmoothScroller.SNAP_TO_START;
//            }
//        }
//
//
//
//        smoothScroller.setTargetPosition(postProperty.idy);
//        binding.photosSnap.layoutManager?.startSmoothScroll(smoothScroller)

        binding.photosSnap.adapter = PhotosSnapAdapter()


      //  viewM

        viewModel.photos.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                val currentView = view
                val offset = currentView?.let {  (binding.photosSnap.width - currentView.width )/ 4 } ?: 0
                (layoutManager as LinearLayoutManager).scrollToPositionWithOffset(100 - postProperty.id, offset )

//                val smoothScroller: SmoothScroller = object : LinearSmoothScroller(context) {
//                    override fun getHorizontalSnapPreference(): Int {
//                        return SNAP_TO_START
//                    }
//                }
//
//                smoothScroller.targetPosition = 100 - postProperty.id
//                layoutManager?.startSmoothScroll(smoothScroller)



//    layoutManager?.smoothScrollToPosition(
//        binding.photosSnap, state:
//    )
//    val smoothScroller = CenterSmoothScroller(context)
//                smoothScroller.targetPosition = 100 - postProperty.id
//
//                layoutManager?.startSmoothScroll(smoothScroller)
//                Navigation.findNavController(requireView())
//                    .navigate(OverviewFragmentDirections.actionShowDetail(it))
//                viewModel.displayPropertyDetailsComplete()
            }
        })
//        binding.photosSnap.layoutManager?.
//        scrollToPosition(25)
        return binding.root

    }

//    class CenterSmoothScroller(context: Context?): LinearSmoothScroller(context) {
////        CenterSmoothScroller(context: Context) {
////            super(context)
////        }
//     //   private val context = context
//        private val MILLISECONDS_PER_INCH = 100F;
//        override fun calculateDtToFit(
//            viewStart: Int,
//            viewEnd: Int,
//            boxStart: Int,
//            boxEnd: Int,
//            snapPreference: Int
//        ): Int {
//            return (boxStart + (boxEnd - boxStart) / 2) - (viewStart + (viewEnd - viewStart) / 2);
//        }
//
//        override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics?): Float {
//            return displayMetrics?.let { MILLISECONDS_PER_INCH / displayMetrics.densityDpi } ?: 0F
//
//        }
 //   }
//    class CenterSmoothScroller extends LinearSmoothController {
//        CenterSmoothScroller(context: Context) {
//            super(context)
//        }
//    }
}