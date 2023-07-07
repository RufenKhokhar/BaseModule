package com.rkgroup.base.basemodule.utils

sealed class ProgressState {
    object Empty : ProgressState()
    object Started : ProgressState()
    class Progress<T>(val progress: T) : ProgressState()
    class Error<T>(val exception: Throwable,val msg: T) : ProgressState()
    class Complete<T>(val result: T) : ProgressState()
}