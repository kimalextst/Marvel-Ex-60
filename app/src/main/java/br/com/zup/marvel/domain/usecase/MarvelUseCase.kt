package br.com.zup.marvel.domain.usecase

import android.app.Application
import br.com.zup.marvel.data.datasource.local.MarvelRoomDatabase
import br.com.zup.marvel.domain.model.Marvel
import br.com.zup.marvel.domain.repository.MarvelRepository
import br.com.zup.marvel.viewstate.ViewState

class MarvelUseCase (application: Application) {
    private val marvelDAO = MarvelRoomDatabase.getDatabase(application).movieDAO()
    private val marvelRepository = MarvelRepository(marvelDAO)

    suspend fun getAllHeroes(): ViewState<List<Marvel>> {
        return try {
            val listMovie = marvelRepository.getAllHeroes()
            ViewState.Success(listMovie)
        } catch (ex: Exception) {
            ViewState.Error(Exception("Não foi possível carregar a lista de heróis!"))
        }
    }

    suspend fun insertHero(marvel: Marvel): ViewState<Marvel> {
        return try {
            marvelRepository.insertHero(marvel)
            ViewState.Success(marvel)
        } catch (ex: Exception) {
            ViewState.Error(Exception("Não foi possível inserir o herói!"))
        }
    }
}