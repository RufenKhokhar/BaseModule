package com.rkgroup.base.basemodule.utils

import com.rkgroup.base.basemodule.extensions.toFormattedDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

object FileManager {


    fun generateImageName(): String {
        val prefix = "invoice"
        val time = System.currentTimeMillis()
        val postfix = time.toFormattedDate("ssMMddyyyy")
        return "${prefix}_${postfix}.jpg"
    }
    fun generateReportName(): String {
        val prefix = "report"
        val time = System.currentTimeMillis()
        val postfix = time.toFormattedDate("ssMMddyyyy")
        return "${prefix}_${postfix}.pdf"
    }

   suspend fun generateFile(outDir: File, fileName: String = generateImageName()): File  = withContext(Dispatchers.IO){
        if (outDir.exists().not()) {
            outDir.mkdirs()
        }
        val result = File(outDir, fileName)
        if (result.exists().not()) {
            result.createNewFile()
        }
         result
    }


}