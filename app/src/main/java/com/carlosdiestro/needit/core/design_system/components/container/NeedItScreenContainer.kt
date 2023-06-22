package com.carlosdiestro.needit.core.design_system.components.container

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NeedItScreenContainer(
    topAppBar: @Composable () -> Unit,
    screenContent: @Composable () -> Unit
) {
    Scaffold(
        topBar = topAppBar
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            screenContent()
        }
    }
}