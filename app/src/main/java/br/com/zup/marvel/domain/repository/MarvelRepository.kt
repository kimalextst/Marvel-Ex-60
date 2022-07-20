package br.com.zup.marvel.domain.repository

import br.com.zup.marvel.data.datasource.local.dao.MarvelDAO
import br.com.zup.marvel.domain.model.Marvel

class MarvelRepository(private val marvelDAO: MarvelDAO) {
    suspend fun getAllHeroes(): List<Marvel> = marvelDAO.getHeroesList()

    suspend fun insertHero(marvel: Marvel) {
        marvelDAO.insertHero(marvel)
    }
}