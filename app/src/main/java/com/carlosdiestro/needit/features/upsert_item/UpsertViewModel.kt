package com.carlosdiestro.needit.features.upsert_item

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.needit.core.design_system.components.lists.WishCategoryPlo
import com.carlosdiestro.needit.core.design_system.components.lists.toWishCategoryPlo
import com.carlosdiestro.needit.core.mappers.asPlo
import com.carlosdiestro.needit.domain.wishes.Wish
import com.carlosdiestro.needit.domain.wishes.usecases.GetImageUriUseCase
import com.carlosdiestro.needit.domain.wishes.usecases.GetWishUseCase
import com.carlosdiestro.needit.domain.wishes.usecases.InsertWishUseCase
import com.carlosdiestro.needit.domain.wishes.usecases.UpdateWishUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpsertViewModel @Inject constructor(
    private val getWish: GetWishUseCase,
    private val insertWish: InsertWishUseCase,
    private val updateWish: UpdateWishUseCase,
    private val getImageUri: GetImageUriUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val category: WishCategoryPlo = (savedStateHandle[argCategory] ?: -1)
        .toWishCategoryPlo()
    private val wishId: Long = savedStateHandle[argWishId] ?: -1L
    private var wish: Wish? = null

    private var _state: MutableStateFlow<UpsertDataState> = MutableStateFlow(
        UpsertDataState("", category)
    )
    val state = _state.asStateFlow()

    val saveButtonEnabled by derivedStateOf {
        title.trim().isNotEmpty()
    }

    var title by mutableStateOf("")
        private set

    var subtitle by mutableStateOf("")
        private set

    var price by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    var webUrl by mutableStateOf("")
        private set

    var size: String? by mutableStateOf(null)
        private set

    var color: String? by mutableStateOf(null)
        private set

    var isbn: String? by mutableStateOf(null)
        private set


    init {
        if (wishId != -1L) {
            fetchWish()
        } else {
            fetchImageLocalPath()
        }
    }

    private fun fetchWish() {
        viewModelScope.launch {
            wish = getWish(wishId).first()
            wish?.let {
                _state.update { currentState ->
                    currentState.copy(
                        imageUrl = it.imageLocalPath.ifEmpty { it.imageUrl },
                        category = it.category.asPlo()
                    )
                }
                updateTitle(it.title)
                updateSubtitle(it.subtitle)
                updatePrice(it.price.toString())
                updateWebUrl(it.webUrl)
                updateDescription(it.description)
                updateSize(it.size)
                updateColor(it.color)
                updateIsbn(it.isbn)
            }

        }
    }

    private fun fetchImageLocalPath() {
        viewModelScope.launch {
            val uri = getImageUri()
            _state.update {
                it.copy(
                    imageUrl = uri
                )
            }
        }
    }

    fun updateTitle(value: String) {
        title = value
    }

    fun updateSubtitle(value: String) {
        subtitle = value
    }

    fun updatePrice(value: String) {
        price = value
    }

    fun updateWebUrl(value: String) {
        webUrl = value
    }

    fun updateDescription(value: String) {
        description = value
    }

    fun updateSize(value: String?) {
        size = value.orEmpty()
    }

    fun updateColor(value: String?) {
        color = value.orEmpty()
    }

    fun updateIsbn(value: String?) {
        isbn = value.orEmpty()
    }

    fun save() {
        viewModelScope.launch {
            if (wishId != -1L) {
                wish?.let {
                    val updatedWish = it.copy(
                        title = title,
                        subtitle = subtitle,
                        price = if (price.isEmpty()) 0.0 else price.toDouble(),
                        webUrl = webUrl,
                        description = description,
                        size = size,
                        color = color,
                        isbn = isbn
                    )
                    updateWish(updatedWish)
                }
            } else {
                insertWish(
                    imageLocalPath = state.value.imageUrl,
                    title = title,
                    subtitle = subtitle,
                    price = price,
                    webUrl = webUrl,
                    description = description,
                    category = category,
                    size = size,
                    color = color,
                    isbn = isbn
                )
            }
        }
    }
}

