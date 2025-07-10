package com.example.feature.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core.data.repository.tvShow.TvShowRepository
import com.example.core.model.TvShowModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(
    repository: TvShowRepository
) : ViewModel() {
    val items: Flow<PagingData<TvShowModel>> =
        repository.getPopularTvShows().cachedIn(viewModelScope)
}
