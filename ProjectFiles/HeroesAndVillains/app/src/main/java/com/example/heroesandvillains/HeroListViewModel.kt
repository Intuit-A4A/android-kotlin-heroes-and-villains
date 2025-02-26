package com.example.heroesandvillains

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.heroesandvillains.data.DataAccessState
import com.example.heroesandvillains.data.HeroDataModel
import com.example.heroesandvillains.network.utils.interfaces.RepositoryInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel class for Heroes and Villains
 */
class HeroListViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: RepositoryInterface
): ViewModel() {

    companion object {
        private const val CURRENT_HEROES_LIST_KEY = "currentHeroesList"
    }

    // List of heroes
    private val _heroesListContentState = MutableStateFlow<DataAccessState<List<HeroDataModel>>>(
        savedStateHandle[CURRENT_HEROES_LIST_KEY] ?: DataAccessState.Loading
    )
    val heroesListContentState: StateFlow<DataAccessState<List<HeroDataModel>>> = _heroesListContentState.asStateFlow()

    private var _searchedHeroList = MutableStateFlow<List<HeroDataModel>>(emptyList())
    val searchedHeroList: StateFlow<List<HeroDataModel>> = _searchedHeroList.asStateFlow()
    private var heroList: List<HeroDataModel> = emptyList()

    init {
        fetchHeroList()
    }

    private fun fetchHeroList() {
        viewModelScope.launch {
            // pass filter and search query in
            repository.getFullList()
                .collect { list ->
                    heroList = list
                    _searchedHeroList.value = heroList
                    _heroesListContentState.value = DataAccessState.Success(list)
                }
        }
    }
}

internal class HeroesAndVillainsViewModelFactory(
    private val repository: RepositoryInterface
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T =
        HeroListViewModel(
            extras.createSavedStateHandle(),
            repository
        ) as T
}
