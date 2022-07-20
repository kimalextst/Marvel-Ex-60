package br.com.zup.marvel.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.zup.marvel.domain.model.Marvel

@Dao
interface MarvelDAO {
    @Query("SELECT * from heroes ORDER BY nome ASC")
    fun getHeroesList(): List<Marvel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHero(vararg movies: Marvel)
}