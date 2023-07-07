package com.rkgroup.base.basemodule.extensions

import android.database.Cursor
import androidx.core.database.getFloatOrNull
import androidx.core.database.getIntOrNull
import androidx.core.database.getLongOrNull
import androidx.core.database.getStringOrNull


fun Cursor.getString(columnName: String) = getStringOrNull(getColumnIndex(columnName))
fun Cursor.getLong(columnName: String) = getLongOrNull(getColumnIndex(columnName))
fun Cursor.getInt(columnName: String) = getIntOrNull(getColumnIndex(columnName))
fun Cursor.getFloat(columnName: String) = getFloatOrNull(getColumnIndex(columnName))

