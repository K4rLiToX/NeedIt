package com.carlosdiestro.design_system.cards

import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.carlosdiestro.design_system.avatars.NiAvatar
import com.carlosdiestro.design_system.icon_buttons.NiIconButton
import com.carlosdiestro.design_system.icon_buttons.NiIconButtonSpecs
import com.carlosdiestro.design_system.lists.FollowableUser
import com.carlosdiestro.design_system.theme.icons

@Composable
fun NiFollowableUserCard(
    modifier: Modifier = Modifier,
    user: FollowableUser,
    onClick: () -> Unit
) {
    ListItem(
        leadingContent = {
            NiAvatar(
                imageUrl = user.profilePictureUrl
            )
        },
        headlineContent = {
            Text(
                text = user.username,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        supportingContent = {
            Text(
                text = user.email,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        trailingContent = {
            if (user.isFriendRequestSent) {
                Icon(
                    imageVector = MaterialTheme.icons.Check,
                    contentDescription = null
                )
            } else {
                NiIconButton(
                    icon = MaterialTheme.icons.AddFriend,
                    onClick = onClick
                )
            }
        },
        modifier = modifier,
        shadowElevation = 0.dp,
        tonalElevation = 0.dp,
        colors = ListItemDefaults.colors(containerColor = Color.Transparent)
    )
}