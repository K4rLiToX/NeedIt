package com.carlosdiestro.needit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.feature.settings.ThemeConfigPlo
import com.carlosdiestro.feature.settings.asPlo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainService: MainService
) : ViewModel() {

    val isSignedIn = mainService.isSignedIn

    val profilePictureUrl: StateFlow<String> = mainService.profilePictureUrl
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ""
        )

    val state: StateFlow<MainState> = mainService.themeConfig
        .map { themeConfig ->
            MainState.Success(
                value = themeConfig.asPlo()
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MainState.Loading
        )

    fun syncFriends() {
        viewModelScope.launch {
            mainService.syncFriends()
        }
    }
}

sealed interface MainState {
    data object Loading : MainState
    data class Success(val value: ThemeConfigPlo) : MainState
}