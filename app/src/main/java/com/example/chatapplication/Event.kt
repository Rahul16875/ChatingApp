package com.example.chatapplication

open class Event<out T> (val content : T){
    private var hasBeenHandled = false
    fun getContentOrNull(): T? {
        return if (hasBeenHandled) null
        else{
            hasBeenHandled = true
            content
        }
    }
}
