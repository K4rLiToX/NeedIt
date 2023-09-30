package com.carlosdiestro.needit.core.design_system.components.buttons

import android.content.res.Configuration
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.carlosdiestro.needit.core.design_system.theme.Icons
import com.carlosdiestro.needit.core.design_system.theme.NeedItTheme

@Composable
fun NIFab(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(imageVector = icon, contentDescription = "")
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun NIFabPreview() {
    NeedItTheme {
        NIFab(icon = Icons.AddFriend, onClick = {})
    }
}