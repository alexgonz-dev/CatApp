package com.catapp.domainapp.domain.di

import android.content.Context
import com.catapp.domain.usecase.GetApplicationConfigurationUseCase
import com.catapp.domain.usecase.GetDisplayableItemListUseCase
import com.catapp.domain.usecase.GetDisplayableItemUseCase
import com.catapp.domainapp.domain.usecase.GetApplicationConfigurationUseCaseImpl
import com.catapp.domainapp.domain.usecase.GetDisplayableItemListUseCaseImpl
import com.catapp.domainapp.domain.usecase.GetDisplayableItemUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideDisplayableItemListUseCase(): GetDisplayableItemListUseCase {
        return GetDisplayableItemListUseCaseImpl()
    }

    @Singleton
    @Provides
    fun provideDisplayableItemUseCase(): GetDisplayableItemUseCase {
        return GetDisplayableItemUseCaseImpl()
    }
    
    @Singleton
    @Provides
    fun provideGetApplicationConfigurationUseCase(
        @ApplicationContext context: Context
    ): GetApplicationConfigurationUseCase {
        return GetApplicationConfigurationUseCaseImpl(context)
    }
}