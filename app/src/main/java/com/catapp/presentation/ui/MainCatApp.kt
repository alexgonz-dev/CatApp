package com.catapp.presentation.ui

import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.catapp.presentation.ui.theme.CatAppTheme

@Composable
fun MainCatApp(content: @Composable () -> Unit) {
    CatAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colorScheme.background) {
            content()
        }
    }
}