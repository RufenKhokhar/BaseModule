package com.rkgroup.base.basemodule.extensions

import android.util.Log
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.SecretKeySpec

private const val ALGORITHM = "AES";
private const val TRANSFORMATION = "AES"
private  val KEY  = byteArrayOf(36,-90,-32,-122,96,-25,15,-74,27,59,114,-29,53,-118,-44,68)
fun ByteArray.encrypt(): ByteArray {
    return getCipher(Cipher.ENCRYPT_MODE).doFinal(this)
}

fun ByteArray.decrypt(): ByteArray {
    return getCipher(Cipher.DECRYPT_MODE).doFinal(this)
}


private fun getCipher(mode: Int): Cipher {
    val secretKey = SecretKeySpec(KEY, ALGORITHM)
    val cipher = Cipher.getInstance(TRANSFORMATION)
    cipher.init(mode, secretKey)
    return cipher
}