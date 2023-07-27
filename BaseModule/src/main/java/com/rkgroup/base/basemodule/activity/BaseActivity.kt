package com.rkgroup.base.basemodule.activity

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.annotation.Keep
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.rkgroup.base.basemodule.utils.AppLanguageChanger
import java.lang.reflect.ParameterizedType
import javax.inject.Inject
import kotlin.system.exitProcess

@Suppress("DEPRECATION")
@Keep
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.setDefaultUncaughtExceptionHandler { _, e ->
            Firebase.crashlytics.recordException(e)
            exitProcess(0)
        }
        super.onCreate(savedInstanceState)
        initBackPressDisparate()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(AppLanguageChanger.wrapContext(newBase))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            handleOnBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initBackPressDisparate() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                this@BaseActivity.handleOnBackPressed()
            }
        })
    }

    open fun handleOnBackPressed() {
        finish()
    }

    override fun getResources(): Resources {
        return super.getResources().apply {
            configuration.fontScale = 0.9F
            updateConfiguration(configuration, displayMetrics)
        }
    }
}

@Keep
abstract class BaseActivity1<VB : ViewBinding>(private val inflaterCallbacks: (LayoutInflater) -> VB) : BaseActivity() {
    private lateinit var _binding: VB
    protected val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflaterCallbacks.invoke(layoutInflater)
        setContentView(_binding.root)
    }


}


