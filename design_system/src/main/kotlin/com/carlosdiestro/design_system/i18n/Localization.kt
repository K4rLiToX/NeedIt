package com.carlosdiestro.design_system.i18n

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.stringResource
import com.carlosdiestro.design_system.R

@Immutable
object Localization {

    @Immutable
    object Button {
        val Update: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.button_update)

        val Remove: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.button_remove)

        val KeepPrivate: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.button_keep_private)

        val Share: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.button_share)

        val Cancel: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.button_cancel)
    }

    @Immutable
    object DeleteDialog {
        val Title: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.delete_dialog_title)

        val Body: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.delete_dialog_body)
    }
}