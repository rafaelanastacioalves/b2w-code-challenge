package com.example.rafaelanastacioalves.pokedex.ui.pokemon_detailing

import android.os.Bundle

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

import com.example.rafaelanastacioalves.pokedex.R


class PokemonDetailingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_detailing_activity)
        setupActionBar()


        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val arguments = Bundle()
            arguments.putString(PokemonDetailingFragment.POKEMON_NAME,
                    intent.getStringExtra(PokemonDetailingFragment.POKEMON_NAME))
            val fragment = PokemonDetailingFragment()
            fragment.arguments = arguments
            supportFragmentManager.beginTransaction()
                    .add(R.id.entity_detail_fragment_container, fragment)
                    .commit()


            supportPostponeEnterTransition()
        }
    }

    private fun setupActionBar() {
        val toolbar = findViewById<View>(R.id.detail_toolbar) as Toolbar
        setSupportActionBar(toolbar)

    }

}
