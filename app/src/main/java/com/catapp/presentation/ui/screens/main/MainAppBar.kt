package com.catapp.presentation.ui.screens.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.catapp.R
import com.catapp.presentation.ui.MainCatApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppBar() {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
        actions = {
            AppBarAction(
                imageVector = Icons.Default.Search,
                onClick = { /* TODO */ }
            )
            AppBarAction(
                imageVector = Icons.Default.Share,
                onClick = { /* TODO */ }
            )
        }
    )
}

@Composable
private fun AppBarAction(
    imageVector: ImageVector,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = imageVector,
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun AppBarActionPreview() {
    MainCatApp {
        MainAppBar()
    }
}