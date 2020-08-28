package com.example.rafaelanastacioalves.pokedex.repository.database;

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rafaelanastacioalves.pokedex.domain.entities.PokemonReference
import com.example.rafaelanastacioalves.pokedex.domain.entities.pokemon.Pokemon


@Database(entities = [PokemonReference::class,
    Pokemon::class],
        version = 1)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun appDAO(): DAO
    companion object {

        val databaseName : String = "pokemonDB"
        private lateinit var context: Context
        private val INSTANCE: AppDataBase by lazy {
            synchronized(this) {
                buildDatabase(context)
            }
        }

        fun setupAtApplicationStartup(context: Context) {
            this.context =context
        }

        fun getInstance(): AppDataBase {
            return INSTANCE
        }


        private fun buildDatabase(context: Context): AppDataBase {
            return Room.databaseBuilder(context.applicationContext,
                    AppDataBase::class.java,
                    databaseName).build()
        }
    }
}
