package com.catapp.api.domain

import android.content.Context
import com.catapp.api.data.datasource.ItemLocalDataSource
import com.catapp.api.data.datasource.ItemRemoteDataSource
import com.catapp.api.data.network.ItemService
import com.catapp.api.domain.usecase.GetDisplayableItemImageUseCaseImpl
import com.catapp.domain.usecase.GetDisplayableItemImageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiUseCaseModule {

    @Singleton
    @Provides
    fun provideGetDisplayableItemImageUseCase(
        @ApplicationContext context: Context,
        itemLocalDataSource: ItemLocalDataSource,
        itemRemoteDataSource: ItemRemoteDataSource,
    ): GetDisplayableItemImageUseCase {
        return GetDisplayableItemImageUseCaseImpl(
            context,
            itemLocalDataSource,
            itemRemoteDataSource
        )
    }
}