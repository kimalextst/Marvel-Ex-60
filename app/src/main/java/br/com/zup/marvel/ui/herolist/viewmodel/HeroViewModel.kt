package br.com.zup.marvel.ui.herolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zup.marvel.domain.model.Marvel
import br.com.zup.marvel.domain.usecase.MarvelUseCase
import br.com.zup.marvel.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HeroViewModel (application: Application) : AndroidViewModel(application) {
    private val marvelUseCase = MarvelUseCase(application)
    val marvelListState = MutableLiveData<ViewState<List<Marvel>>>()

    fun getHeroList() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    marvelUseCase.getAllHeroes()
                }
                marvelListState.value = response
            } catch (ex: Exception) {
                marvelListState.value =
                    ViewState.Error(Throwable("Tivemos um problema! Tente mais tarde."))
            }
        }
    }
}