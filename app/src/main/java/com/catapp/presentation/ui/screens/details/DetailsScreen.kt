package com.catapp.presentation.ui.screens.details

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.catapp.R
import com.catapp.presentation.viewmodel.DetailsScreenViewModel
import com.catapp.ui.screens.shared.ArrowBackIcon
import com.catapp.presentation.ui.screens.shared.Thumb
import com.catapp.presentation.viewmodel.DisplayableItemListViewModel
import kotlinx.coroutines.flow.single

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    id: String,
    onUpClick: () -> Unit
) {

    val viewModel: DetailsScreenViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.result.value = viewModel.getDisplayableItemAsFlow(id)
            .single()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = viewModel.result.value?.first?.owner
                            ?: stringResource(R.string.loading_string)
                    )
                },
                navigationIcon = { ArrowBackIcon(onUpClick) }
            )

        }
    ) {
        Thumb(model = viewModel.result.value?.second)
    }
}