package com.app.imdb.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.imdb.ApiService
import com.app.imdb.model.Result
import com.app.imdb.repo.MovieRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieRepo: MovieRepo
) : ViewModel() {

    fun fetchMovieList(): Flow<PagingData<Result>> {
        return movieRepo.getMovieListFlow("en-US")
            .cachedIn(viewModelScope)
            .distinctUntilChanged()
    }
}