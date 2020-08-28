package com.example.rafaelanastacioalves.pokedex.ui.pokemon_listing;

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

import com.example.rafaelanastacioalves.pokedex.R
import com.example.rafaelanastacioalves.pokedex.domain.entities.PokemonReference
import com.example.rafaelanastacioalves.pokedex.listeners.RecyclerViewClickListener
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

    fun bind(pokemonPokemonReference: PokemonReference, context: Context) {
        itemView.pokemonName.text = context.getString(R.string.pokemon_name, pokemonPokemonReference.name)
    }
}
