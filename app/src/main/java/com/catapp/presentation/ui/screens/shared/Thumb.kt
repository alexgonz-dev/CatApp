package com.catapp.presentation.ui.screens.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.catapp.R
import com.catapp.domain.model.DisplayableItem
import com.catapp.presentation.viewmodel.ThumbViewModel
import kotlinx.coroutines.flow.singleOrNull

@Composable
fun Thumb(
    item: DisplayableItem?
) {

    val viewModel: ThumbViewModel = hiltViewModel()

    LaunchedEffect(item) {
        viewModel.result.value = viewModel.getDisplayableItemImageAsFlow(item)
            .singleOrNull()
    }

    Box(
        modifier = Modifier
            .height(dimensionResource(R.dimen.cell_thumb_height))
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = viewModel.result.value,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}