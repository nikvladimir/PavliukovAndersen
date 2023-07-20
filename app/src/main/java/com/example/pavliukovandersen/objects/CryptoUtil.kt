package com.example.pavliukovandersen.objects

import com.example.pavliukovandersen.Constants
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

object CryptoUtil {
    fun getDecryptedKey(): String {
        val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, SecretKeySpec("0123456789abcdef".toByteArray(), "AES"))
        return String(cipher.doFinal(Base64.getDecoder().decode(Constants.KEY)))
    }
}