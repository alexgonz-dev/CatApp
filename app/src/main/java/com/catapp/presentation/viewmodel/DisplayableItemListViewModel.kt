package com.catapp.presentation.viewmodel

import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catapp.domain.model.DisplayableItem
import com.catapp.domain.usecase.GetApplicationConfigurationUseCase
import com.catapp.domain.usecase.GetDisplayableItemImageUseCase
import com.catapp.domain.usecase.GetDisplayableItemListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DisplayableItemListViewModel @Inject constructor(
    private val getApplicationConfigurationUseCase: GetApplicationConfigurationUseCase,
    private val getDisplayableItemListUseCase: GetDisplayableItemListUseCase,
    private val getDisplayableItemImageUseCase: GetDisplayableItemImageUseCase
) : ViewModel() {


    val result: MutableState<List<DisplayableItem>> = mutableStateOf(emptyList())
    val resultImage: MutableState<ByteArray?> = mutableStateOf(null)

    fun getDisplayableItemsAsFlow(): Flow<List<DisplayableItem>> = flow {
        emit(getDisplayableItems() ?: emptyList())
    }

    fun getDisplayableImageItemAsFlow(item: DisplayableItem?): Flow<ByteArray?> = flow {
        emit(updateImage(item))
    }

    private suspend fun getDisplayableItems(): List<DisplayableItem>? {
        val config = viewModelScope.async {
            val configuration = getConfiguration()
            configuration?.let {
                processConfiguration(it)
            }
        }.await()


        return config
    }

    suspend fun updateImage(item: DisplayableItem?): ByteArray? {
        return viewModelScope.async {
            withContext(Dispatchers.IO) {
                getDisplayableItemImageUseCase.loadItemImage(item).getOrNull()
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

    private suspend fun processConfiguration(config: String): List<DisplayableItem> {
        return viewModelScope.async {
            withContext(Dispatchers.IO) {
                val retVal =
                    getDisplayableItemListUseCase.fetchItemsList(config).getOrDefault(emptyList())

                retVal
            }
        }.await()
    }
}