package com.carlosdiestro.needit.domain.wishes

import android.util.Patterns

class Validators(
    val emptyValidator: EmptyValidator,
    val webValidator: WebValidator,
    val priceValidator: PriceValidator
) {

    class EmptyValidator {
        operator fun invoke(value: String): Boolean = value.trim().isNotEmpty()
    }

    class WebValidator {
        operator fun invoke(value: String): Boolean {
            val isNotEmpty = EmptyValidator()
            if (isNotEmpty(value)) return true
            return Patterns.WEB_URL.matcher(value).matches()
        }
    }

    class PriceValidator {
        operator fun invoke(value: String): Boolean {
            val isNotEmpty = EmptyValidator()
            if (isNotEmpty(value)) return false
            return value.toDouble() == 0.0
        }
    }
}