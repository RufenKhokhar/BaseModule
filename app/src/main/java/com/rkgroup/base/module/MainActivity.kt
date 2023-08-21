package com.rkgroup.base.module

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.core.view.drawToBitmap
import com.rkgroup.base.basemodule.activity.BaseActivity1
import com.rkgroup.base.basemodule.extensions.disable
import com.rkgroup.base.basemodule.extensions.enable
import com.rkgroup.base.basemodule.extensions.gone
import com.rkgroup.base.basemodule.extensions.inVisible
import com.rkgroup.base.basemodule.extensions.permissionGranted
import com.rkgroup.base.basemodule.extensions.resourcesManager
import com.rkgroup.base.basemodule.extensions.shareTextData
import com.rkgroup.base.basemodule.extensions.visible
import com.rkgroup.base.basemodule.recyclerview.RecyclerBaseAdapter
import com.rkgroup.base.module.databinding.ActivityMainBinding

class MainActivity : BaseActivity1<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




    }


    companion object {
        private const val TAG = "MainActivity"
    }
}

class RVAdaptrer:RecyclerBaseAdapter<ActivityMainBinding,Int>(ActivityMainBinding::inflate){

    override fun onBindViewHolder(holder: BaseView<ActivityMainBinding>, position: Int) {
        super.onBindViewHolder(holder, position)
        val item = getItemAt(position)
        holder.binding.tvHello.text = item.toString()
    }

}