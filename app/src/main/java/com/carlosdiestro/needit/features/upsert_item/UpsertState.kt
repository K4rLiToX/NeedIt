package com.carlosdiestro.needit.features.upsert_item

import androidx.annotation.StringRes
import com.carlosdiestro.needit.R
import com.carlosdiestro.needit.core.design_system.components.lists.WishCategoryPlo

data class UpsertDataState(
    val imageUrl: String,
    val category: WishCategoryPlo,
) {
    @get:StringRes
    val titleHintId: Int
        get() {
            return if (category == WishCategoryPlo.Books)
                R.string.upsert_title_hint
            else
                R.string.upsert_name_hint
        }

    @get:StringRes
    val subtitleHintId: Int
        get() {
            return if (category == WishCategoryPlo.Books)
                R.string.upsert_author_hint
            else
                R.string.upsert_brand_hint
        }
}