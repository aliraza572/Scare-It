package com.aliraza.scareit

import android.content.ContentResolver
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import java.io.File

class SurpriseActivity : AppCompatActivity() {

    lateinit var scaryImageView: ImageView

    lateinit var photoUri: Uri
    lateinit var soundUri: Uri

    lateinit var tts: TextToSpeech
    lateinit var mediaPlayer: MediaPlayer

    var acceptTouch: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surprise)

        scaryImageView = findViewById(R.id.img_scare)

        photoUri =
            Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + File.pathSeparator + File.separator + File.separator + packageName + "/drawable/doll")
        soundUri =
            Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + File.pathSeparator + File.separator + File.separator + packageName + "/raw/scream1")

        Toast.makeText(this, "Ready", Toast.LENGTH_SHORT).show()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

    }

    private fun userTriggerAction() {
        if (!acceptTouch) {
            return
        }
        acceptTouch = false
        showImage()
        playSound()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        userTriggerAction()
        return super.onTouchEvent(event)
    }

    private fun playSound() {
        mediaPlayer = MediaPlayer.create(this, soundUri)
        mediaPlayer.setOnCompletionListener {
            finish()
        }
        mediaPlayer.start()

    }

    private fun showImage() {

        Glide.with(this)
            .load(photoUri)
            .into(scaryImageView)
        scaryImageView.visibility = View.VISIBLE
    }
}