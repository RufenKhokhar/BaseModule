package com.rkgroup.base.basemodule.extensions

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import com.rkgroup.base.basemodule.utils.FileManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File


suspend fun Uri.getImageFile(mContext: Context) = withContext(Dispatchers.IO) {
    val projection = arrayOf(MediaStore.Images.ImageColumns.DATA)
    val query = mContext.contentResolver.query(this@getImageFile, projection,
        null, null, null)
    var result: String? = null
    if (query != null && query.moveToFirst()) {
        result = query.getString(projection[0])
    }
    query?.close()
    result
}

suspend fun Uri.toPhysicalImageFile(mContext: Context) = withContext(Dispatchers.IO){
    val imageFile = getImageFile(mContext)
   val parentDir =  mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    if (parentDir?.exists()==false) {
        parentDir.mkdirs()
    }
    val physicalImageFile = imageFile?.let {
        File(parentDir,imageFile.toFile().name)
    }?:run{
        File(parentDir,FileManager.generateImageName())
    }
    if (physicalImageFile.exists().not()) {
        physicalImageFile.createNewFile()
    }
    val sourceStream  =imageFile?.let {
        imageFile.toFile().inputStream()
    }?:run{
        mContext.contentResolver.openInputStream(this@toPhysicalImageFile)
    }
    sourceStream.use {inStream->
        physicalImageFile.outputStream().use {
            inStream?.copyTo(it)

        }
    }
    physicalImageFile
}