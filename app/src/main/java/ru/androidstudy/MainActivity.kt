package ru.androidstudy

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    private val textEmail by lazy { findViewById<EditText>(R.id.email) }
    private val textPassword by lazy { findViewById<EditText>(R.id.password) }

    private val btnSignUpPage by lazy { findViewById<Button>(R.id.signUpPage) }
    private val btnSignIn by lazy { findViewById<Button>(R.id.signIn) }

    private val auth by lazy { Firebase.auth }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSignUpPage.setOnClickListener { btnSignUpPageClick() }
        btnSignIn.setOnClickListener { btnSignInClick() }
    }

    private fun btnSignUpPageClick() {
        startActivity(Intent(this, SignUpActivity::class.java))
    }

    private fun btnSignInClick() {
        auth.signInWithEmailAndPassword(textEmail.text.toString(), textPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    Toast.makeText(
                        baseContext,
                        "Авторизация прошла успешно",
                        Toast.LENGTH_SHORT,
                    ).show()
                    startActivity(Intent(this, HelloPageActivity::class.java))
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Авторизация провалена",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }

}