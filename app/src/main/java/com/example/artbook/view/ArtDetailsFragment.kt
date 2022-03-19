package com.example.artbook.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.artbook.R
import com.example.artbook.Util.Resource
import com.example.artbook.databinding.FragmentArtDetailsBinding
import com.example.artbook.viewmodel.ArtViewModel
import javax.inject.Inject

/**
 * Created by aarslan on 19/02/2022.
 */
class ArtDetailsFragment @Inject constructor(
    private val glide : RequestManager
) : Fragment(R.layout.fragment_art_details) {

    lateinit var viewModel : ArtViewModel

    private var fragmentArtDetailsBinding : FragmentArtDetailsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ArtViewModel::class.java)

        val binding = FragmentArtDetailsBinding.bind(view)
        fragmentArtDetailsBinding = binding

        subscribeToObserver()

        binding.artImageView.setOnClickListener {
            findNavController().navigate(ArtDetailsFragmentDirections.actionArtDetailsFragmentToImageApiFragment())
        }

        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                viewModel.setSelectedImage("")
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)

        binding.saveButton.setOnClickListener {
            viewModel.makeArt(binding.nameText.text.toString(),binding.artistText.text.toString(),binding.yearText.text.toString())
        }
    }

    private fun subscribeToObserver() {
        viewModel.selectedImageUrl.observe(viewLifecycleOwner, Observer {url->
            fragmentArtDetailsBinding?.let {
                glide.load(url).into(it.artImageView)
            }
        })

        viewModel.insertArtMessage.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Resource.Status.SUCCESS ->{
                    Toast.makeText(requireContext(),"Success",Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                    viewModel.resetInsertArtMessage()
                }

                Resource.Status.LOADING ->{
                }

                Resource.Status.ERROR ->{
                    Toast.makeText(requireContext(),it.message ?: "ERROR",Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onDestroyView() {
        fragmentArtDetailsBinding = null
        super.onDestroyView()
    }
}