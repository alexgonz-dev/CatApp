package com.catapp.domain.usecase

interface GetApplicationConfigurationUseCase {

    suspend fun fetchConfiguration(): Result<String>
}