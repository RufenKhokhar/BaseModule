package com.rkgroup.base.basemodule.extensions

import java.io.File

fun String.toFile() = File(this)