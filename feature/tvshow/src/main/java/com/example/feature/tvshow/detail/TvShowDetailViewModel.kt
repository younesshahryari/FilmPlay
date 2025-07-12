package com.example.feature.tvshow.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.core.data.model.Result
import com.example.core.data.repository.tvShow.TvShowRepository
import com.example.feature.tvshow.navigation.TvShowDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowDetailViewModel @Inject constructor(
    private val repository: TvShowRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _detailState = MutableStateFlow<TvShowDetailState>(TvShowDetailState.Loading)
    val detailState: StateFlow<TvShowDetailState> = _detailState.asStateFlow()

    private val id: Int = savedStateHandle.toRoute<TvShowDestination.DetailScreenRoute>().id

    init {
        fetchDetail()
    }

    fun fetchDetail() {
        viewModelScope.launch {
            repository.getTvShow(id).collect { result ->
                when (result) {
                    is Result.Loading -> _detailState.value = TvShowDetailState.Loading
                    is Result.Success -> _detailState.value = TvShowDetailState.Success(result.data)
                    is Result.Error -> _detailState.value = TvShowDetailState.Error(result.message)
                }
            }
        }
    }

}
