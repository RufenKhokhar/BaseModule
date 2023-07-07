package com.rkgroup.base.module

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.rkgroup.base.basemodule.extensions.preferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    companion object {
        private const val TAG = "MainActivity"
    }
}