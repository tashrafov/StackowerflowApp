package com.example.stackoverflow.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class QuestionList {
    @SerializedName("items")
    @Expose
    lateinit var questions: List<Question>
}