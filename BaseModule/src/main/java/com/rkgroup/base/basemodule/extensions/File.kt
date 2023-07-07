package com.rkgroup.base.basemodule.extensions

import android.content.Context
import androidx.core.content.FileProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.IOException
import java.security.DigestInputStream
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


suspend fun File.getPublicUri(mContext: Context, authority: String) = withContext(Dispatchers.IO) {
    FileProvider.getUriForFile(mContext, authority, this@getPublicUri)
}

@Throws(IOException::class, NoSuchAlgorithmException::class)
suspend fun File.getHash() = withContext(Dispatchers.IO) {
    val digest = MessageDigest.getInstance("SHA-256")
    val inputStream = inputStream()
    val messageDigest = DigestInputStream(inputStream, digest)
    digest.digest(messageDigest.readBytes())
    val result = StringBuilder()
    digest.digest().forEach {
        result.append(String.format("%02x", it))
    }
    result.toString()
}



