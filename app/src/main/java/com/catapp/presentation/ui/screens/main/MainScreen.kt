package com.catapp.presentation.ui.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.catapp.presentation.ui.MainCatApp

@Composable
fun MainScreen(onNavigate: (String) -> Unit) {
    MainCatApp {
        Scaffold(
            topBar = { MainAppBar() }
        ) { padding ->
            DisplayableItemList(
                onClick = { onNavigate(it.id) },
                modifier = Modifier.padding(padding)
            )
        }

    }
}