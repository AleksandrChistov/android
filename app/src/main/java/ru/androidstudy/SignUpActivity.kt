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


class SignUpActivity : AppCompatActivity() {
    private val textEmail by lazy { findViewById<EditText>(R.id.email) }
    private val textPassword by lazy { findViewById<EditText>(R.id.password) }
    private val textRepeatPassword by lazy { findViewById<EditText>(R.id.repeatPassword) }

    private val btnSignInPage by lazy { findViewById<Button>(R.id.signInPage) }
    private val btnSignUp by lazy { findViewById<Button>(R.id.signUp) }

    private val auth by lazy { Firebase.auth }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        btnSignInPage.setOnClickListener { btnSignInPageClick() }
        btnSignUp.setOnClickListener { btnSignUpClick() }
    }

    private fun btnSignInPageClick() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun btnSignUpClick() {
        val valid = validatePassword()
        if (valid) {
            auth.createUserWithEmailAndPassword(textEmail.text.toString(), textPassword.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "createUserWithEmail:success")
                        Toast.makeText(
                            baseContext,
                            "Регистрация прошла успешно! Вы можете авторизоваться",
                            Toast.LENGTH_SHORT,
                        ).show()
                        startActivity(Intent(this, MainActivity::class.java))
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Регистрация провалена",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        }
    }

    private fun validatePassword(): Boolean {
        if (textPassword.text.toString() != textRepeatPassword.text.toString()) {
            textRepeatPassword.error = "Пароли не совпадают"
            return false
        } else if (textPassword.text.length < 6 || textRepeatPassword.text.length < 6) {
            textPassword.error = "Пароль не может быть меньше 6 символов"
            textRepeatPassword.error = "Пароль не может быть меньше 6 символов"
            return false
        }
        return true
    }

}