package com.example.rafaelanastacioalves.pokedex.ui.pokemon_listing;

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.rafaelanastacioalves.pokedex.R
import com.example.rafaelanastacioalves.pokedex.domain.entities.PokemonReference
import com.example.rafaelanastacioalves.pokedex.listeners.RecyclerViewClickListener

class PokemonListingAdapter(diffCallback : DiffUtil.ItemCallback<PokemonReference>) : PagedListAdapter<PokemonReference, PokemonLIstingViewHolder>(diffCallback) {
    constructor(context: Context) : this(DIFF_CALLBACK as DiffUtil.ItemCallback<PokemonReference>){
        mContext = context
    }

    private lateinit var mContext: Context

    companion object {
        protected val DIFF_CALLBACK: DiffUtil.ItemCallback<PokemonReference?> = object : DiffUtil.ItemCallback<PokemonReference?>() {
            override fun areItemsTheSame(@NonNull pokemonReference: PokemonReference, @NonNull t1: PokemonReference): Boolean {
                return pokemonReference.name.equals(t1.name) && pokemonReference.position.equals(t1.position)
            }

            override fun areContentsTheSame(@NonNull pokemonReference: PokemonReference, @NonNull t1: PokemonReference): Boolean {
                return pokemonReference.equals(t1)
            }
        }
    }
    lateinit private var recyclerViewClickListener: RecyclerViewClickListener
    private var intermediateList: List<PokemonReference>? = null
    fun setRecyclerViewClickListener(aRVC: RecyclerViewClickListener ) {
        this.recyclerViewClickListener = aRVC;
    }

    fun getItems(): List<PokemonReference>? {
        return if (intermediateList!=null) intermediateList else {currentList}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonLIstingViewHolder  {
        return PokemonLIstingViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pokemon_listing_viewholder, parent, false), recyclerViewClickListener);
    }

    override fun onBindViewHolder(holder: PokemonLIstingViewHolder, position: Int ) {
        val pokemonResult: PokemonReference
        if (intermediateList!=null){
            pokemonResult = intermediateList!![position]
        }else{
            pokemonResult = getItem(position) as PokemonReference;
        }
        holder.bind(pokemonResult, mContext)
    }

    override fun getItemCount(): Int {
       if (intermediateList !=null){
           return intermediateList!!.size
       }else {
           return super.getItemCount()
       }
    }

    fun updateList() {
        notifyDataSetChanged()
    }

    fun filter(newText: String?) {
        if (newText.isNullOrEmpty()){
            intermediateList = null
        }else if(newText.isNotBlank()){
            intermediateList =  currentList?.filter { it.name.contains(newText) }
        }
        notifyDataSetChanged()
    }
}
