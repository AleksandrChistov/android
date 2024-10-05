package ru.androidstudy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class HelloPageActivity : AppCompatActivity() {

    private val btnSignOut by lazy { findViewById<Button>(R.id.signOut) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello)
        btnSignOut.setOnClickListener { btnSignOutClick() }
    }

    private fun btnSignOutClick() {
        Firebase.auth.signOut()
        startActivity(Intent(this, MainActivity::class.java))
    }
}