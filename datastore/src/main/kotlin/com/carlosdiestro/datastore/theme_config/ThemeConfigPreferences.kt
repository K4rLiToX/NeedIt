package com.carlosdiestro.datastore.theme_config

enum class ThemeConfigPreferences {
    FollowSystem,
    Light,
    Dark
}

fun ThemeConfigPreferences.asValue(): Int = this.ordinal
fun Int?.asPreferences(): ThemeConfigPreferences = this?.let {
    ThemeConfigPreferences
        .entries[it]
} ?: ThemeConfigPreferences.FollowSystem