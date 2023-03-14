package com.example.coe141homework1

object CaesarCipher {

    private const val ALPHABET_LENGTH = 26

    fun encrypt(input: String, offset: Int): String {
        return caesarCipher(input, offset, true)
    }

    fun decrypt(input: String, offset: Int): String {
        return caesarCipher(input, offset, false)
    }

    private fun caesarCipher(input: String, offset: Int, isEncrypt: Boolean): String {
        if (offset <= 0) {
            return input
        }

        return buildString {
            input.forEach { char ->
                val result = when (char) {
                    in 'a'..'z' -> {
                        val moveBy = if (isEncrypt) {
                            char - 'a' + offset
                        } else {
                            char - 'a' - offset + ALPHABET_LENGTH
                        }
                        'a' + moveBy % ALPHABET_LENGTH
                    }

                    in 'A'..'Z' -> {
                        val moveBy = if (isEncrypt) {
                            char - 'A' + offset
                        } else {
                            char - 'A' - offset + ALPHABET_LENGTH
                        }
                        'A' + moveBy % ALPHABET_LENGTH
                    }

                    else -> char // not a letter
                }
                append(result)
            }
        }
    }
}