package com.carlosdiestro.needit.core.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun <T> ViewModel.launchCollect(
    flow: Flow<T>,
    block: (T) -> Unit
) {
    viewModelScope.launch {
        flow.collect(block)
    }
}