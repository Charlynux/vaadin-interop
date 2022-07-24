package com.example.application.views.main

class TextFieldLabelService : LabelService {
    override fun getLabel(name: String): String {
        return "Your name from Kotlin"
    }
}