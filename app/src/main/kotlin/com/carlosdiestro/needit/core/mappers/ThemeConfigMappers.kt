package com.carlosdiestro.needit.core.mappers

import com.carlosdiestro.needit.ThemeConfigPlo
import com.carlosdiestro.needit.datastore.models.ThemeConfigPreferences
import com.carlosdiestro.needit.domain.theme_config.ThemeConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun ThemeConfigPreferences.asDomain(): ThemeConfig = ThemeConfig.entries[this.ordinal]
fun Flow<ThemeConfigPreferences>.asDomain(): Flow<ThemeConfig> = this.map { it.asDomain() }

fun ThemeConfig.asPreferences(): ThemeConfigPreferences =
    ThemeConfigPreferences.entries[this.ordinal]

fun ThemeConfig.asPlo(): ThemeConfigPlo = ThemeConfigPlo.entries[this.ordinal]

fun Flow<ThemeConfig>.asPlo(): Flow<ThemeConfigPlo> = this.map { it.asPlo() }

fun ThemeConfigPlo.asDomain(): ThemeConfig = ThemeConfig.entries[this.ordinal]