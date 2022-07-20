package br.com.zup.marvel.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.zup.marvel.data.datasource.local.dao.MarvelDAO
import br.com.zup.marvel.domain.model.Marvel

@Database(entities = [Marvel::class], version = 1, exportSchema = false)
abstract class MarvelRoomDatabase : RoomDatabase() {
    abstract fun movieDAO(): MarvelDAO

    companion object {
        @Volatile
        private var INSTANCE: MarvelRoomDatabase? = null

        fun getDatabase(context: Context): MarvelRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MarvelRoomDatabase::class.java,
                    "movie_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}