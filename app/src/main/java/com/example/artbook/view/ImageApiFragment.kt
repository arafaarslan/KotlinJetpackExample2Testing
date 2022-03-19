package com.example.artbook.view

import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.RequestManager
import com.example.artbook.R
import com.example.artbook.Util.Resource
import com.example.artbook.adapter.ImageRecyclerAdapter
import com.example.artbook.databinding.FragmentImageApiBinding
import com.example.artbook.viewmodel.ArtViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by aarslan on 19/02/2022.
 */
class ImageApiFragment @Inject constructor(
    val imageRecyclerAdapter: ImageRecyclerAdapter
) : Fragment(R.layout.fragment_image_api) {

    lateinit var viewModel: ArtViewModel

    private var fragmentBinding : FragmentImageApiBinding ?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ArtViewModel::class.java)

        val binding = FragmentImageApiBinding.bind(view)

        fragmentBinding = binding

        var job : Job? = null
        binding.searchET.addTextChangedListener {
            job?.cancel()
            job = lifecycleScope.launch {
                delay(1000)
                it?.let {
                    if(it.toString().isNotEmpty()){
                        viewModel.searchForImage(it.toString())
                    }
                }
            }
        }
        subscriberToObservers()

        binding.imageRV.adapter = imageRecyclerAdapter
        binding.imageRV.layoutManager = GridLayoutManager(requireContext(),3)
        imageRecyclerAdapter.setOnClickListener {
            findNavController().popBackStack()
            viewModel.setSelectedImage(it)
        }
    }

    private fun subscriberToObservers(){
        viewModel.imageList.observe(viewLifecycleOwner,Observer{
            when(it.status){
                Resource.Status.SUCCESS->{
                    val urls = it.data?.hits?.map { imageResult ->
                        imageResult.previewURL
                    }

                    imageRecyclerAdapter.images = urls?: listOf()
                    fragmentBinding?.progressBar?.visibility = View.GONE
                }
                Resource.Status.LOADING->{
                    fragmentBinding?.progressBar?.visibility = View.VISIBLE

                }
                Resource.Status.ERROR->{
                    Toast.makeText(requireContext(),it.message ?: "ERROR",Toast.LENGTH_SHORT).show()
                    fragmentBinding?.progressBar?.visibility = View.GONE
                }
            }
        })
    }
}