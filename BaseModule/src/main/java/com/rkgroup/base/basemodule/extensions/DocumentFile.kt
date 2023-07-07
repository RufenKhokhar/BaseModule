package com.rkgroup.base.basemodule.extensions

import android.content.Context
import androidx.documentfile.provider.DocumentFile


val DocumentFile.isHidden
    get() = name.toString().startsWith(".")

fun DocumentFile.inputStream(mContext: Context) = mContext.contentResolver.openInputStream(uri)
fun DocumentFile.outputStream(mContext: Context) = mContext.contentResolver.openOutputStream(uri)