package com.app.imdb.repo

import androidx.paging.PagingData
import com.app.imdb.model.Result
import kotlinx.coroutines.flow.Flow

interface MovieRepo {

    fun getMovieListFlow(language: String): Flow<PagingData<Result>>
}