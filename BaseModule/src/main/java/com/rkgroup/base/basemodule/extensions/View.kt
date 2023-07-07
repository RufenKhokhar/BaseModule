package com.rkgroup.base.basemodule.extensions

import android.os.SystemClock
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible

fun View.setOnSingleClickListener(block: ((View) -> Unit)?) =
    setOnClickListener(SingleClickListener(block))

class SingleClickListener(private val block: ((View) -> Unit)?) : View.OnClickListener {
    private var lastClickTime = 0L
    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastClickTime < 500L) return
        lastClickTime = SystemClock.elapsedRealtime()
        block?.invoke(v)
    }
}

fun View.showKeyBoard(){
    requestFocus()
    val methodManager = context.getSystemService(InputMethodManager::class.java)
    methodManager.showSoftInput(this,0)
}
fun View.hideKeyboard(){
    clearFocus()
    val methodManager = context.getSystemService(InputMethodManager::class.java)
    methodManager.hideSoftInputFromWindow(windowToken,0)
}

fun View.setOnVisibilityChanged(action: (View) -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener {
        val newVis: Int = this.visibility
        if (this.tag as Int? != newVis) {
            this.tag = this.visibility
            // visibility has changed
            action(this)
        }
    }
}

fun ScrollView.scrollToChild(child: View) = post {
    smoothScrollTo(0, child.top)
}

fun TextView.clearError() {
    error = null
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.inVisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.toggleVisibility() {
    isVisible = isVisible.not()
}

fun View.toggleInVisibility() {
    isInvisible = isInvisible.not()
}
fun View.enable(){
    isEnabled = true
}

fun View.disable(){
    isEnabled = false
}
