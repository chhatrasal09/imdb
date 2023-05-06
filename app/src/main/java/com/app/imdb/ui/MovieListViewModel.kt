package com.app.imdb.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.imdb.ApiService
import com.app.imdb.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    fun letMovieListFlow(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<Result>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { MovieListPagingSource(apiService, "en-US") }
        ).flow
    }

    /**
     * let's define page size, page size is the only required param, rest is optional
     */
    fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = 10, enablePlaceholders = false)
    }

    fun fetchMovieList(): Flow<PagingData<Result>> {
        return letMovieListFlow()
            .cachedIn(viewModelScope)
            .distinctUntilChanged()
    }
}