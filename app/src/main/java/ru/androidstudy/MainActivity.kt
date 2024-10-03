package ru.androidstudy

import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream


class MainActivity : AppCompatActivity() {
    private val btnPaint by lazy { findViewById<ImageButton>(R.id.btnPaint) }
    private val btnText by lazy { findViewById<ImageButton>(R.id.btnText) }
    private val btnClear by lazy { findViewById<ImageButton>(R.id.btnClear) }
    private val btnSave by lazy { findViewById<ImageButton>(R.id.btnSave) }

    private val editText by lazy { findViewById<EditText>(R.id.editText) }

    private val paintArea by lazy { findViewById<PaintArea>(R.id.paint) }
    private val paintWrapper by lazy { findViewById<LinearLayout>(R.id.paintWrapper) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnPaint.setOnClickListener { view -> btnPaintClick(view) }
        btnText.setOnClickListener { view -> btnTextClick(view) }
        btnClear.setOnClickListener { view -> btnClearClick(view) }
        btnSave.setOnClickListener { makeScreenshot(paintWrapper) }
        paintArea.visibility = View.INVISIBLE
    }

    private fun btnPaintClick(view: View) {
        paintArea.visibility = View.VISIBLE
        editText.clearFocus()
    }

    private fun btnTextClick(view: View) {
        editText.visibility = View.VISIBLE
        editText.requestFocus()
    }

    private fun btnClearClick(view: View) {
        paintArea.cleanPaintArea()
        editText.visibility = View.INVISIBLE
        editText.text.clear()
        paintArea.invalidate()
    }
    private fun makeScreenshot(view: View) {
        val bitmap = getScreenShotFromView(view)
        if (bitmap != null) {
            saveMediaToStorage(bitmap)
        }
    }

    private fun getScreenShotFromView(v: View): Bitmap? {
        var screenshot: Bitmap? = null
        try {
            screenshot = Bitmap.createBitmap(v.measuredWidth, v.measuredHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(screenshot)
            canvas.drawColor(Color.WHITE)
            v.draw(canvas)
        } catch (e: Exception) {
            Log.e("GFG", "Failed to capture screenshot because:" + e.message)
        }
        return screenshot
    }

    private fun saveMediaToStorage(bitmap: Bitmap) {
        val filename = "${System.currentTimeMillis()}.jpg"
        var fos: OutputStream? = null

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            this.contentResolver?.also { resolver ->
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }
                val imageUri: Uri? =
                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                fos = imageUri?.let { resolver.openOutputStream(it) }
            }
        } else {
            val imagesDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, filename)
            fos = FileOutputStream(image)
        }

        fos?.use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            Toast.makeText(this, "Captured View and saved to Gallery", Toast.LENGTH_SHORT).show()
        }
    }

}