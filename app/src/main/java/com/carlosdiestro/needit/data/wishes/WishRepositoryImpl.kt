package com.carlosdiestro.needit.data.wishes

import com.carlosdiestro.needit.core.di.IoDispatcher
import com.carlosdiestro.needit.domain.wishes.Wish
import com.carlosdiestro.needit.domain.wishes.WishRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WishRepositoryImpl @Inject constructor(
    private val localDatasource: WishLocalDatasource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
): WishRepository {
    override val wishes: Flow<List<Wish>>
        get() = localDatasource.wishes.flowOn(dispatcher)
}