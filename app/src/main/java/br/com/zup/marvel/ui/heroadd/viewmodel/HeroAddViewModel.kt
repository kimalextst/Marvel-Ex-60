package br.com.zup.marvel.ui.heroadd.viewmodel

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

class HeroAddViewModel (application: Application) : AndroidViewModel(application) {
    private val marvelUseCase = MarvelUseCase(application)
    val marvelAddState = MutableLiveData<ViewState<Marvel>>()

    fun insertHero(marvel: Marvel) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    marvelUseCase.insertHero(marvel)
                }
                marvelAddState.value = response
            } catch (ex: Exception) {
                marvelAddState.value =
                    ViewState.Error(Throwable("Tivemos um problema! Tente mais tarde."))
            }
        }
    }
}