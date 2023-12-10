package com.carlosdiestro.design_system.i18n

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.stringResource
import com.carlosdiestro.design_system.R

@Immutable
object Localization {

    val AppName: String
        @ReadOnlyComposable
        @Composable
        get() = stringResource(id = R.string.app_name)

    @Immutable
    object Home {
        val Title: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.home_title)

        val Empty: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.home_empty)
    }

    @Immutable
    object Gifts {
        val Title: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.gifts_title)

        val TabReservations: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.gifts_tab_reservations)

        val TabGroups: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.gifts_tab_groups)

        val ReservationsEmpty: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.gifts_reservations_empty)

        val GroupsEmpty: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.gifts_groups_empty)

        val Guest: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.gifts_guest)
    }

    @Immutable
    object Participants {
        val Title: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.participants_title)

        val SearchFriends: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.participants_search_friends)

        @Composable
        fun Selected(participantsSelected: Int): String = stringResource(
            id = R.string.participants_selected,
            formatArgs = arrayOf(participantsSelected)
        )
    }

    @Immutable
    object Friends {
        val Title: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.friends_title)

        val Empty: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.friends_empty)

        val Guest: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.friends_guest)
    }

    @Immutable
    object People {
        val Search: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.people_search_people)

        val FriendRequestSent: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.people_friend_request_sent)
    }

    @Immutable
    object Notifications {
        val Title: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.notifications_title)

        val TabFriendRequests: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.notifications_tab_friend_requests)

        val TabGroupAdditions: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.notifications_tab_group_additions)

        @Composable
        fun FriendRequestMessage(name: String): String = stringResource(
            id = R.string.notifications_friend_request_message,
            formatArgs = arrayOf(name)
        )

        @Composable
        fun FriendRequestAccepted(name: String): String = stringResource(
            id = R.string.notifications_friend_requests_accepted,
            formatArgs = arrayOf(name)
        )

        val FriendRequestRejected: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.notifications_friend_request_rejected)

        @Composable
        fun GroupAdditionMessage(adminName: String, giftForName: String): String = stringResource(
            id = R.string.notifications_group_addition_message,
            formatArgs = arrayOf(adminName, giftForName)
        )

        val FriendRequestEmpty: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.notifications_friend_requests_empty)
    }

    @Immutable
    object Upsert {
        val NameHint: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.upsert_name_hint)

        val TitleHint: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.upsert_title_hint)

        val BrandHint: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.upsert_brand_hint)

        val AuthorHint: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.upsert_author_hint)

        val PriceHint: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.upsert_price_hint)

        val SizeHint: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.upsert_size_hint)

        val ColorHint: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.upsert_color_hint)

        val IsbnHint: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.upsert_isbn_hint)

        val WebHint: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.upsert_website_link_hint)

        val DescriptionHint: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.upsert_name_hint)

        val MandatoryFieldMessage: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.upsert_mandatory_field_message)
    }

    @Immutable
    object WishDetails {
        val Size: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.item_details_size)

        val Color: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.item_details_color)

        val Isbn: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.item_details_isbn)

        @Composable
        fun GroupGiftTitle(giftForName: String): String = stringResource(
            id = R.string.item_details_group_gift_title,
            formatArgs = arrayOf(giftForName)
        )

        val PersonalGiftMessage: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.item_details_personal_gift_message)

        val NoLinkMessage: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.item_details_no_link_message)
    }

    @Immutable
    object GroupSettings {
        @Composable
        fun Title(giftForName: String): String = stringResource(
            id = R.string.group_settings_title,
            formatArgs = arrayOf(giftForName)
        )

        val Total: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.group_settings_total)

        val PayStatePending: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.group_settings_pay_state_pending)

        val PayStatePaid: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.group_settings_pay_state_paid)

        val LeaveGroupMessage: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.group_settings_leave_group_message)

        val LeaveGroupMessageAdmin: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.group_settings_leave_group_message_admin)

        val MakeAdminMessage: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.group_settings_make_admin_message)

        val DissolveGroupMessage: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.group_settings_dissolve_group_message)
    }

    @Immutable
    object ItemCategories {
        val All: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.item_category_all)

        val Clothes: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.item_category_clothes)

        val Footwear: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.item_category_footwear)

        val Accessories: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.item_category_accessories)

        val Books: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.item_category_books)

        val Grooming: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.item_category_grooming)

        val Tech: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.item_category_tech)

        val Other: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.item_category_other)
    }

    @Immutable
    object Labels {
        val Reserved: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.label_reserved)
    }

    @Immutable
    object Account {
        val Birthday: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.account_birthday)

        val Currency: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.account_currency)
    }

    @Immutable
    object Settings {
        val Title: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.settings_title)

        val DisplaySection: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.settings_display_section)

        val DisplaySectionTheme: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.settings_display_section_theme)

        val DisplaySectionThemeDialogLight: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.settings_display_section_theme_dialog_light)

        val DisplaySectionThemeDialogDark: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.settings_display_section_theme_dialog_dark)

        val DisplaySectionThemeDialogUseSystem: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.settings_display_section_theme_dialog_use_system)

        val NotificationsSection: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.settings_notifications_section)

        val NotificationsSectionFriendRequests: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.settings_notifications_section_friend_requests)

        val NotificationsSectionAdditionToGroups: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.settings_notifications_section_addition_to_groups)

        val AboutSection: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.settings_about_section)

        val AboutSectionVersionAndUpdates: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.settings_about_section_version_and_updates)

        val AboutSectionPrivacyPolicy: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.settings_about_section_privacy_policy)

        val AboutSectionTermsOfUse: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.settings_about_section_terms_of_service)
    }

    @Immutable
    object SendFeedback {
        val Title: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.send_feedback_title)
    }

    @Immutable
    object ReportABug {
        val Title: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.report_bug_title)

        val TitleHint: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.report_bug_title_hint)

        val DescriptionHint: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.report_bug_description_hint)
    }

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

        val Continue: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.button_continue)

        val SignIn: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.button_sign_in)

        val SignOut: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.button_sign_out)

        val ContinueAsGuest: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.button_continue_as_guest)

        val Save: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.button_save)

        val PrivacyPolicy: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.button_privacy_policy)

        val TermsOfService: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.button_terms_of_service)

        val DeleteAccount: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.button_delete_account)

        val ViewAll: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.button_view_all)

        val Gifted: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.button_gifted)

        val GroupGift: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.button_group_gift)

        val Gift: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.button_gift)

        val Send: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.button_send)

        val Accept: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.button_accept)

        val Reject: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.button_reject)

        val Finish: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.button_finish)

        val Release: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.button_release)

        val PersonalGift: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.button_personal_gift)

        val LeaveGroup: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.button_leave_group)

        val TransferAdmin: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.button_make_admin)

        val MarkAsPaid: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.button_mark_as_paid)

        val Ok: String
            @ReadOnlyComposable
            @Composable
            get() = stringResource(id = R.string.button_ok)
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