package ru.androidstudy

import android.app.Activity
import android.view.View
import android.widget.ListView
import android.widget.TextView
import com.firebase.ui.database.FirebaseListAdapter
import com.google.firebase.database.DatabaseReference

open class MyFirebaseListAdapter(
    mainActivity: Activity,
    java: Class<MessageArea>,
    areaChat: Int,
    reference: DatabaseReference,
    private val chatView: ListView,
) : FirebaseListAdapter<MessageArea>(mainActivity, java, areaChat, reference) {

    override fun populateView(v: View, model: MessageArea, position: Int) {
        val messageText = v.findViewById<TextView>(R.id.messageText)
        val messageUser = v.findViewById<TextView>(R.id.messageUser)
        val messageTime = v.findViewById<TextView>(R.id.messageTime)
        messageText.text = model.getMessageText()
        messageUser.text = model.getMessageUser()
        messageTime.text = android.text.format.DateFormat.format(
            "dd-MM-yyyy (HH:mm:ss)",
            model.getMessageTime()
        )
    }
    override fun onDataChanged() {
        chatView.smoothScrollToPosition(chatView.adapter.count)
    }
}