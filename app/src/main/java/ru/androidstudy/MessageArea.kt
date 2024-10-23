package ru.androidstudy

import java.util.Date

class MessageArea(text: String? = "", user: String? = "Unknown") {

    private var messageText = text
    private var messageUser = user
    private var messageTime = Date().time


    fun getMessageText(): String? {
        return messageText
    }

    fun setMessageText(text: String) {
        messageText = text
    }

    fun getMessageUser(): String? {
        return messageUser
    }

    fun setMessageUser(user: String) {
        messageUser = user
    }

    fun getMessageTime(): Long {
        return messageTime
    }

    fun setMessageTime(user: Long) {
        messageTime = user
    }
}