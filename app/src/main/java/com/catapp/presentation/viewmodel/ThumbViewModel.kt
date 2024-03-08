package com.catapp.presentation.viewmodel

import android.graphics.Bitmap
import android.media.Image
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catapp.domain.model.DisplayableItem
import com.catapp.domain.usecase.GetDisplayableItemImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ThumbViewModel @Inject constructor(
    private val getDisplayableItemImageUseCase: GetDisplayableItemImageUseCase
) : ViewModel() {

    val result: MutableState<Bitmap?> = mutableStateOf(null)

    fun getDisplayableItemImageAsFlow(id: DisplayableItem?): Flow<Bitmap?> = flow {
        emit(getDisplayableItemImage(id))
    }

    suspend fun getDisplayableItemImage(id: DisplayableItem?): Bitmap? {
        return withContext(Dispatchers.IO) {
            getDisplayableItemImageUseCase.loadItemImage(id).getOrNull()
        }
    }
}