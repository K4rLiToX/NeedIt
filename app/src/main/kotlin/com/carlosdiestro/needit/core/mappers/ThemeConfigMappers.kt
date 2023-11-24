package com.carlosdiestro.needit.core.mappers

import com.carlosdiestro.app_settings.domain.ThemeConfig
import com.carlosdiestro.needit.ThemeConfigPlo

fun ThemeConfig.asPlo(): ThemeConfigPlo = ThemeConfigPlo.entries[this.ordinal]

fun ThemeConfigPlo.asDomain(): ThemeConfig = ThemeConfig.entries[this.ordinal]