package com.carlosdiestro.needit.core.database.datasources

import com.carlosdiestro.needit.core.database.dao.WishDao
import com.carlosdiestro.needit.core.mappers.toDomain
import com.carlosdiestro.needit.data.wishes.WishLocalDatasource
import com.carlosdiestro.needit.domain.wishes.Wish
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WishLocalDatasourceImpl @Inject constructor(
    private val dao: WishDao
): WishLocalDatasource {
    override val wishes: Flow<List<Wish>>
        get() = dao.getAll().toDomain()
}