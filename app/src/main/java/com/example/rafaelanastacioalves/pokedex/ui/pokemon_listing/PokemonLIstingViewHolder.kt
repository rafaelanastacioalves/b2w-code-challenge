package com.example.rafaelanastacioalves.pokedex.ui.pokemon_listing;

import android.content.Context
import android.graphics.drawable.StateListDrawable
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

import com.example.rafaelanastacioalves.pokedex.R
import com.example.rafaelanastacioalves.pokedex.domain.entities.Result
import com.example.rafaelanastacioalves.pokedex.listeners.RecyclerViewClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.pokemon_listing_viewholder.view.*

class PokemonLIstingViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), View.OnClickListener, LayoutContainer{

    lateinit private var aRecyclerViewListener: RecyclerViewClickListener


    constructor(itemView: View , clickListener: RecyclerViewClickListener) : this(itemView) {
        this.aRecyclerViewListener = clickListener


    }
    init {
        itemView.detail_container.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        aRecyclerViewListener.onClick(v, getAdapterPosition());
    }

    fun bind(pokemonResult: Result, context: Context) {
        itemView.pokemonName.text = context.getString(R.string.pokemon_name, pokemonResult.name)
    }
}
