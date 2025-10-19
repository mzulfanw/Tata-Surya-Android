package com.example.tatasurya

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.tatasurya.databinding.ItemRowPlanetBinding

class ListPlanetAdapter(private val listPlanet: ArrayList<Planet>, private val onClick: (Planet) -> Unit) : RecyclerView.Adapter<ListPlanetAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val binding = ItemRowPlanetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ListViewHolder,
        position: Int
    ) {
        val hero = listPlanet[position]
        holder.bind(hero)
    }

    override fun getItemCount(): Int = listPlanet.size

    inner class ListViewHolder(private val binding: ItemRowPlanetBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(planet: Planet) {
            Glide.with(itemView.context)
                .load(planet.photo)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imgItemPhoto)
            binding.tvItemName.text = planet.name
            binding.tvItemDescription.text = planet.description
            itemView.setOnClickListener { onClick(planet) }
        }
    }
}