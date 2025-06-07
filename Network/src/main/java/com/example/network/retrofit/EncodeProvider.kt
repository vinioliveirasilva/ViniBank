package com.example.network.retrofit

import java.util.Base64

class EncodeProvider {

    fun encode(toEncode: ByteArray) : String {
        return Base64.getEncoder().encodeToString(toEncode)
    }

    fun decode(toDecode: String?) : ByteArray {
        return Base64.getDecoder().decode(toDecode)
    }
}