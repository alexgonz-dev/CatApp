package com.catapp.presentation.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.catapp.R
import com.catapp.domain.model.DisplayableItem
import com.catapp.presentation.ui.MainCatApp
import com.catapp.presentation.viewmodel.DisplayableItemListViewModel
import com.catapp.presentation.ui.screens.shared.Thumb
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.singleOrNull

@Composable
fun DisplayableItemList(
    onClick: (DisplayableItem) -> Unit,
    modifier: Modifier = Modifier,
) {

    val viewModel: DisplayableItemListViewModel = hiltViewModel()

    LaunchedEffect(viewModel) {
        viewModel.result.value = viewModel.getDisplayableItemsAsFlow()
            .single()
    }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(dimensionResource(R.dimen.cell_min_width)),
        contentPadding = PaddingValues(dimensionResource(R.dimen.padding_xsmall)),
        modifier = modifier
    ) {

        items(viewModel.result.value) {
            MediaListItem(
                displayableItem = it,
                onClick = { onClick(it) },
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_xsmall))
            )
        }
    }
}

@Composable
fun MediaListItem(
    displayableItem: DisplayableItem?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.clickable { onClick() }
    ) {
        Column {
            Thumb(item = displayableItem)
            Title(displayableItem = displayableItem)
        }
    }
}

@Composable
private fun Title(displayableItem: DisplayableItem?) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondary)
            .padding(dimensionResource(R.dimen.padding_medium))
    ) {
        Text(
            text = displayableItem?.owner ?: stringResource(R.string.loading_string),
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview
@Composable
fun MediaListItemPreview() {
    MainCatApp {
        val displayableItem = DisplayableItem(
            "1",
            "Created at",
            "Jhon Doe",
            emptyList(),
            "Updated at"
        )
        MediaListItem(displayableItem = displayableItem, onClick = {})
    }
}