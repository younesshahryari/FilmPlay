package com.example.feature.tvshow.detail

import com.example.core.model.TvShowDetailModel

sealed class TvShowDetailState {
    object Loading : TvShowDetailState()
    data class Success(val movie: TvShowDetailModel) : TvShowDetailState()
    data class Error(val message: String) : TvShowDetailState()
}