package com.example.coe141homework1

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CaesarCipherViewModel : ViewModel() {
    data class ViewState(
        val input: String,
        val offset: Int,
        val isEncrypt: Boolean
    ) {
        val output: String by lazy {
            if (isEncrypt) {
                CaesarCipher.encrypt(input, offset)
            } else {
                CaesarCipher.decrypt(input, offset)
            }
        }
    }

    private val _viewState = MutableStateFlow(
        ViewState(input = "", offset = 13, isEncrypt = true)
    )

    fun onInputChanged(input: String) {
        _viewState.value = _viewState.value.copy(input = input)
    }

    fun onOffsetChanged(offset: Int) {
        _viewState.value = _viewState.value.copy(offset = offset)
    }

    fun onEncryptChanged(isEncrypt: Boolean) {
        _viewState.value = _viewState.value.copy(isEncrypt = isEncrypt)
    }

    val viewState = _viewState.asStateFlow()
}
