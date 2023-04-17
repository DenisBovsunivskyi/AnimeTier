package com.denisbovsunivskyi.animetier.presentation.utils

import android.content.Context
import android.net.Uri
import java.io.*

fun Uri.toFile(context: Context, fileName: String): File {
    val inStream = context.contentResolver.openInputStream(this)

    val tempFile = File(context.cacheDir, fileName)
    tempFile.createNewFile()

    var outStream: FileOutputStream? = null

    try {
        outStream = FileOutputStream(tempFile)
        outStream.write(inStream?.readBytes())
        outStream.flush()
    } catch (fileEx: FileNotFoundException) {
        fileEx.printStackTrace()
    } catch (ioEx: IOException) {
        ioEx.printStackTrace()
    } finally {
        outStream?.close()
    }

    return tempFile
}
fun Uri.toFileBuffered(context: Context, fileName: String): File {
    val inStream = context.contentResolver.openInputStream(this)

    val tempFile = File(context.cacheDir, fileName)
    tempFile.createNewFile()
    var outStream: FileOutputStream? = null
    var bufferedOutStream: BufferedInputStream? = null
    val baf = ByteArray(BUFFER_SIZE)
    try {
        outStream = FileOutputStream(tempFile)
        bufferedOutStream = BufferedInputStream(inStream, BUFFER_SIZE)
        var actual = 0
        while (actual != -1) {
            outStream.write(baf, 0, actual)
            actual = bufferedOutStream.read(baf, 0, BUFFER_SIZE)
        }
    } catch (fileEx: FileNotFoundException) {
        fileEx.printStackTrace()
    } catch (ioEx: IOException) {
        ioEx.printStackTrace()
    } finally {
        outStream?.close()
    }

    return tempFile
}
const val BUFFER_SIZE = 23 * 1024

fun clearFiles(cacheDir: File) {
    cacheDir.listFiles()?.forEach { file ->
        if (file.name.startsWith("tmp_image_file")) {
            file.delete()
        }
    }
}