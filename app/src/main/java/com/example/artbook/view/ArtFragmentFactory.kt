package com.example.artbook.view

import android.media.Image
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.FragmentManager
import javax.inject.Inject

import com.bumptech.glide.RequestManager
import com.example.artbook.adapter.ArtRecyclerAdapter
import com.example.artbook.adapter.ImageRecyclerAdapter

/**
 * Created by aarslan on 21/02/2022.
 */
class ArtFragmentFactory @Inject constructor(
    private val artRecyclerAdapter: ArtRecyclerAdapter,
    private val glide: RequestManager,
    private val imageRecyclerAdapter: ImageRecyclerAdapter
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when(className){
            ArtFragment::class.java.name->ArtFragment(artRecyclerAdapter)
            ArtDetailsFragment::class.java.name->ArtDetailsFragment(glide)
            ImageApiFragment::class.java.name->ImageApiFragment(imageRecyclerAdapter)
            else->super.instantiate(classLoader, className)
        }
        //return super.instantiate(classLoader, className)
    }
}