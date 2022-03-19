package com.example.artbook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.artbook.R
import com.example.artbook.model.Art
import javax.inject.Inject

/**
 * Created by aarslan on 21/02/2022.
 */
class ArtRecyclerAdapter @Inject constructor(
    val glide: RequestManager
) : RecyclerView.Adapter<ArtRecyclerAdapter.ArtViewHolder>() {

    class ArtViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffUtil = object : DiffUtil.ItemCallback<Art>() {
        override fun areItemsTheSame(oldItem: Art, newItem: Art): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Art, newItem: Art): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var arts: List<Art>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArtRecyclerAdapter.ArtViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.art_row, parent, false)
        return ArtViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtRecyclerAdapter.ArtViewHolder, position: Int) {

        val artRowIV = holder.itemView.findViewById<ImageView>(R.id.artRowIV)
        val artRowNameTV = holder.itemView.findViewById<TextView>(R.id.artRowNameTV)
        val artRowArtistNameTV = holder.itemView.findViewById<TextView>(R.id.artRowArtistNameTV)
        val artRowYearTV = holder.itemView.findViewById<TextView>(R.id.artRowYearTV)

        val art = arts[position]
        holder.itemView.apply {
            artRowNameTV.text = "Name: ${art.name}"
            artRowArtistNameTV.text = "Name: ${art.artistName}"
            artRowYearTV.text = "Name: ${art.year}"
            glide.load(art.imageUrl).into(artRowIV)
        }
    }

    override fun getItemCount(): Int {
        return arts.size
    }
}