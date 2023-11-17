package com.carlosdiestro.needit.framework.datastore.models

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