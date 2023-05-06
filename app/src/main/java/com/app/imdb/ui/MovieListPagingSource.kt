package com.app.imdb.ui

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.imdb.ApiService
import com.app.imdb.model.Result
import retrofit2.HttpException
import java.io.IOException

class MovieListPagingSource(private val apiService: ApiService, private val language: String) :
    PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return -1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val page = params.key ?: 1
        return try {
            val response = apiService.fetchMovieList(language, page)
            LoadResult.Page(
                response.body()?.results.orEmpty(), prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.body()?.results.isNullOrEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}