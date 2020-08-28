package com.example.rafaelanastacioalves.pokedex.ui.pokemon_detailing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.rafaelanastacioalves.pokedex.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pokemon_detailing_photos_viewholder.view.*

class PokemonImageAdapter : RecyclerView.Adapter<PokemonImageViewHolder>() {
    var list : List<String?>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonImageViewHolder {
        return PokemonImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.pokemon_detailing_photos_viewholder, parent, false))
    }


    override fun getItemCount(): Int {
        return if (list.isNullOrEmpty()){
            0
        }else{
            list!!.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: PokemonImageViewHolder, position: Int) {
            holder.bind(list?.get(position))
    }

}

class PokemonImageViewHolder(itemView: View) : ViewHolder(itemView) {

    fun bind(url: String?) {
        Picasso.get().load(url)
                .into(itemView.imageViewHolder)
    }

}
