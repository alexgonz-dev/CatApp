package com.catapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catapp.domain.model.DisplayableItem
import com.catapp.domain.usecase.GetApplicationConfigurationUseCase
import com.catapp.domain.usecase.GetDisplayableItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val getApplicationConfigurationUseCase: GetApplicationConfigurationUseCase,
    private val getDisplayableItemUseCase: GetDisplayableItemUseCase
) : ViewModel() {
    fun getDisplayableItemAsFlow(id: String): Flow<DisplayableItem?> = flow {
        emit(getDisplayableItem(id))
    }

    private suspend fun getDisplayableItem(id: String): DisplayableItem? {
        return viewModelScope.async {
            val configuration = getConfiguration()
            configuration?.let {
                processConfiguration(it, id)
            }
        }.await()
    }

    private suspend fun getConfiguration(): String? {
        return viewModelScope.async {
            withContext(Dispatchers.IO) {
                getApplicationConfigurationUseCase.fetchConfiguration().getOrNull()
            }
        }.await()
    }

    private suspend fun processConfiguration(config: String, id: String): DisplayableItem? {
        return viewModelScope.async {
            withContext(Dispatchers.IO) {
                getDisplayableItemUseCase.fetchItem(config, id).getOrNull()
            }
        }.await()
    }

}