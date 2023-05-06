package com.app.imdb.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.imdb.ApiService
import com.app.imdb.model.Result
import com.app.imdb.ui.MovieListPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepoImpl @Inject constructor(
    private val apiService: ApiService
) : MovieRepo {
    private fun letMovieListFlow(
        language: String,
        pagingConfig: PagingConfig = getDefaultPageConfig()
    ): Flow<PagingData<Result>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { MovieListPagingSource(apiService, language) }
        ).flow
    }

    /**
     * let's define page size, page size is the only required param, rest is optional
     */
    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = 10, enablePlaceholders = false)
    }

    override fun getMovieListFlow(language: String): Flow<PagingData<Result>> {
        return letMovieListFlow(language)
    }
}