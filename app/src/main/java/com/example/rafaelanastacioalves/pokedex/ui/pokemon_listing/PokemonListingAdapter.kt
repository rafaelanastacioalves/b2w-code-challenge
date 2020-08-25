package com.example.rafaelanastacioalves.pokedex.ui.pokemon_listing;

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rafaelanastacioalves.pokedex.R
import com.example.rafaelanastacioalves.pokedex.domain.entities.Result
import com.example.rafaelanastacioalves.pokedex.listeners.RecyclerViewClickListener

class PokemonListingAdapter(context: Context) : RecyclerView.Adapter<PokemonLIstingViewHolder>() {
    lateinit private var recyclerViewClickListener: RecyclerViewClickListener
    private var mainList: List<Result>? = null
    private var intermediateList: List<Result>? = null

    private val mContext: Context = context

    fun setRecyclerViewClickListener(aRVC: RecyclerViewClickListener ) {
        this.recyclerViewClickListener = aRVC;
    }

    fun getItems(): List<Result>? {
        return if (intermediateList!=null) intermediateList else {mainList}
    }

    fun setItems(items: List<Result>?) {
        this.mainList = items
        updateList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonLIstingViewHolder  {
        return PokemonLIstingViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pokemon_listing_viewholder, parent, false), recyclerViewClickListener);
    }

    override fun onBindViewHolder(holder: PokemonLIstingViewHolder, position: Int ) {
        val pokemonResult = getItems()?.get(position) as Result;
        holder.bind(pokemonResult, mContext)
    }

    override fun getItemCount(): Int {
       if (intermediateList !=null){
           return intermediateList!!.size
       }else if (mainList !=null ) {
           return mainList!!.size
       }else {
           return 0
       }
    }

    fun updateList() {
        notifyDataSetChanged()
    }

    fun filter(newText: String?) {
        if (newText.isNullOrEmpty()){
            intermediateList = null
        }else if(newText.isNotBlank()){
            intermediateList =  mainList?.filter { it.name.contains(newText) }
        }
        notifyDataSetChanged()
    }
}

