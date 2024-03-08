package com.catapp.presentation.ui.screens.details

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.catapp.R
import com.catapp.presentation.viewmodel.DetailsScreenViewModel
import com.catapp.ui.screens.shared.ArrowBackIcon
import com.catapp.presentation.ui.screens.shared.Thumb

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    viewModel: DetailsScreenViewModel = hiltViewModel(),
    id: String,
    onUpClick: () -> Unit
) {
    val displayableItem by viewModel.getDisplayableItemAsFlow(id).collectAsState(initial = null)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = displayableItem?.owner ?: stringResource(R.string.loading_string))
                },
                navigationIcon = { ArrowBackIcon(onUpClick) }
            )

        }
    ) {
        Thumb(item = displayableItem)
    }
}