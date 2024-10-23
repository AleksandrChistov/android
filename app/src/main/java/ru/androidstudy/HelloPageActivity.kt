package ru.androidstudy

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.database.FirebaseListAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class HelloPageActivity : AppCompatActivity() {

    private val btnSend by lazy { findViewById<ImageButton>(R.id.btnSend) }
    private val chatView by lazy { findViewById<ListView>(R.id.chatView) }
    private val inputMessage by lazy { findViewById<EditText>(R.id.inputMessage) }
    private val userNameView by lazy { findViewById<TextView>(R.id.textViewUserName) }

    private lateinit var adapter: FirebaseListAdapter<MessageArea>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello)

        val displayName = FirebaseAuth.getInstance().currentUser?.displayName ?: "Анонимный"
        userNameView.setText(displayName)

        btnSend.setOnClickListener {
            FirebaseDatabase.getInstance()
                .reference
                .push()
                .setValue(
                    MessageArea(
                        inputMessage.text.toString(),
                        displayName
                    )
                )
            inputMessage.setText("")
        }

        showChat()
    }

    private fun showChat() {
        adapter = MyFirebaseListAdapter(
            this,
            MessageArea::class.java,
            R.layout.area_chat,
            FirebaseDatabase.getInstance().reference,
            chatView
        )
        chatView.adapter = adapter
    }

}