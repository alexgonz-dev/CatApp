package com.catapp.api.data.datasource

import com.catapp.api.data.local.dao.ItemDao
import com.catapp.api.data.network.ItemService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Singleton
    @Provides
    fun provideItemLocalDataSource(itemDao: ItemDao): ItemLocalDataSource {
        return ItemLocalDataSourceImpl(itemDao)
    }

    @Singleton
    @Provides
    fun provideItemRemoteDataSource(
        itemApiService: ItemService
    ): ItemRemoteDataSource {
        return ItemRemoteDataSourceImpl(itemApiService)
    }
}