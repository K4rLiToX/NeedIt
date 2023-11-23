package com.carlosdiestro.feature.upsert_wish

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.design_system.lists.WishCategoryPlo
import com.carlosdiestro.user.usecases.GetSignedInUserUseCase
import com.carlosdiestro.wish.domain.model.Book
import com.carlosdiestro.wish.domain.model.Clothes
import com.carlosdiestro.wish.domain.model.Footwear
import com.carlosdiestro.wish.domain.model.Other
import com.carlosdiestro.wish.domain.model.Wish
import com.carlosdiestro.wish.domain.model.WishCategory
import com.carlosdiestro.wish.domain.model.WishInformation
import com.carlosdiestro.wish.usecases.CreateWishUseCase
import com.carlosdiestro.wish.usecases.GetImageLocalPathUseCase
import com.carlosdiestro.wish.usecases.GetWishUseCase
import com.carlosdiestro.wish.usecases.UpdateWishUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class UpsertViewModel @Inject constructor(
    private val getWish: GetWishUseCase,
    private val createWish: CreateWishUseCase,
    private val updateWish: UpdateWishUseCase,
    private val getImageLocalPath: GetImageLocalPathUseCase,
    private val getSignedInUser: GetSignedInUserUseCase,
    private val imageCompressor: ImageCompressor,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val args = UpsertNavArgs.fromSavedState(savedStateHandle)
    private val category: WishCategoryPlo = args.category
    private val wishId: String = args.wishId

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
        if (wishId != "none") {
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
                        imageLocalPath = it.imageLocalPath.ifEmpty { it.imageUrl },
                        category = it.category.asPlo()
                    )
                }
                updateTitle(it.title)
                updateSubtitle(it.subtitle)
                updatePrice(it.price.toString())
                updateWebUrl(it.webUrl)
                updateDescription(it.description)
                when (it) {
                    is Clothes -> {
                        updateSize(it.size)
                        updateColor(it.color)
                    }

                    is Footwear -> {
                        updateSize(it.size)
                        updateColor(it.color)
                    }

                    is Book -> updateIsbn(it.isbn)
                    is Other -> Unit
                }
            }
        }
    }

    private fun fetchImageLocalPath() {
        viewModelScope.launch {
            val uri = getImageLocalPath()
            _state.update {
                it.copy(
                    imageLocalPath = uri
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
        if (wishId != "none") update()
        else create()
    }

    private fun update() {
        viewModelScope.launch {
            wish?.let {
                updateWish(
                    wish = it,
                    price = if (price.isEmpty()) 0.0 else price.toDouble(),
                    description = description,
                    webUrl = webUrl,
                    title = title,
                    subtitle = subtitle,
                    size = size,
                    color = color,
                    isbn = isbn
                )
            }
        }
    }

    private fun create() {
        viewModelScope.launch(scope.coroutineContext) {
            val imageLocalPath = state.value.imageLocalPath
            val compressedImage = imageCompressor.compress(imageLocalPath)
            val userId = getSignedInUser().first().id
            val args = WishInformation(
                userId = userId,
                imageLocalPath = imageLocalPath,
                price = if (price.isEmpty()) 0.0 else price.toDouble(),
                description = description,
                webUrl = webUrl,
                category = category.asDomain(),
                title = title,
                subtitle = subtitle
            )
            createWish(
                args = args,
                compressedImage = compressedImage,
                size = size,
                color = color,
                isbn = isbn
            )
        }
    }
}

fun WishCategoryPlo.asDomain(): WishCategory = WishCategory.entries[this.ordinal]
fun WishCategory.asPlo(): WishCategoryPlo = WishCategoryPlo.entries[this.ordinal]

