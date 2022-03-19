package com.example.artbook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.artbook.R
import javax.inject.Inject

/**
 * Created by aarslan on 21/02/2022.
 */
class ImageRecyclerAdapter  @Inject constructor(
    val glide: RequestManager
) : RecyclerView.Adapter<ImageRecyclerAdapter.ImageRecyclerViewHolder>() {

    class ImageRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var onItemClickListener : ((String) -> Unit) ?= null

    private val diffUtil = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var images: List<String>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageRecyclerAdapter.ImageRecyclerViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_row, parent, false)
        return ImageRecyclerViewHolder(view)
    }

    fun setOnClickListener(listener : (String)->Unit){
        onItemClickListener = listener
    }

    override fun onBindViewHolder(holder: ImageRecyclerAdapter.ImageRecyclerViewHolder, position: Int) {

        val singleArtImageView = holder.itemView.findViewById<ImageView>(R.id.singleArtImageView)
        val url = images[position]
        holder.itemView.apply {
            glide.load(url).into(singleArtImageView)
            setOnClickListener {
                onItemClickListener?.let {
                    it(url)
                }
            }
        }


    }

    override fun getItemCount(): Int {
        return images.size
    }
}