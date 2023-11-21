package com.carlosdiestro.design_system.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Message
import androidx.compose.material.icons.automirrored.rounded.KeyboardBackspace
import androidx.compose.material.icons.automirrored.rounded.Logout
import androidx.compose.material.icons.automirrored.rounded.NavigateNext
import androidx.compose.material.icons.automirrored.rounded.Sort
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Redeem
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.BugReport
import androidx.compose.material.icons.outlined.Celebration
import androidx.compose.material.icons.outlined.CloudUpload
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ImageSearch
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material.icons.outlined.LocalAtm
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PersonAdd
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Sync
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Checklist
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.Deselect
import androidx.compose.material.icons.rounded.EuroSymbol
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Lens
import androidx.compose.material.icons.rounded.LockOpen
import androidx.compose.material.icons.rounded.Redeem
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Immutable
import com.carlosdiestro.design_system.R

@Immutable
object Icons {
    val HomeOutlined = Icons.Outlined.Home
    val HomeFilled = Icons.Rounded.Home
    val GiftsOutlined = Icons.Rounded.Redeem
    val GiftsFilled = Icons.Filled.Redeem
    val FriendsOutlined = Icons.Outlined.Group
    val FriendsFilled = Icons.Filled.Group
    val Profile = Icons.Outlined.AccountCircle
    val Back = Icons.AutoMirrored.Rounded.KeyboardBackspace
    val Sort = Icons.AutoMirrored.Rounded.Sort
    val Search = Icons.Rounded.Search
    val Delete = Icons.Outlined.Delete
    val Edit = Icons.Outlined.Edit
    val Add = Icons.Rounded.Add
    val AddFriend = Icons.Outlined.PersonAdd
    val Share = Icons.Outlined.CloudUpload
    val Lock = Icons.Outlined.Lock
    val Link = Icons.Outlined.Link
    val Group = Icons.Outlined.Groups
    val Person = Icons.Outlined.Person
    val Settings = Icons.Outlined.Settings
    val Logout = Icons.AutoMirrored.Rounded.Logout
    val Deliver = Icons.Outlined.LocalShipping
    val Sync = Icons.Outlined.Sync
    val Check = Icons.Rounded.Check
    val Euro = Icons.Rounded.EuroSymbol
    val Dollar = Icons.Rounded.AttachMoney
    val Continue = Icons.AutoMirrored.Rounded.NavigateNext
    val Camera = Icons.Outlined.PhotoCamera
    val Gallery = Icons.Outlined.ImageSearch
    val Unlock = Icons.Rounded.LockOpen
    val SelectAll = Icons.Rounded.Checklist
    val DeselectAll = Icons.Rounded.Deselect
    val Birthday = Icons.Outlined.Celebration
    val Calendar = Icons.Rounded.CalendarMonth
    val Notification = Icons.Outlined.Notifications
    val Lens = Icons.Rounded.Lens
    val Warning = Icons.Outlined.Warning
    val NeedIt = R.drawable.ic_needit
    val Feedback = Icons.AutoMirrored.Outlined.Message
    val Bug = Icons.Outlined.BugReport
    val ArrowUp = Icons.Filled.ArrowDropUp
    val ArrowDown = Icons.Filled.ArrowDropDown
    val Currency = Icons.Outlined.LocalAtm
    val ArrowRight = Icons.Rounded.ChevronRight
}

val MaterialTheme.icons: com.carlosdiestro.design_system.theme.Icons
    get() = com.carlosdiestro.design_system.theme.Icons