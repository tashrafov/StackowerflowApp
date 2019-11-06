package com.example.stackoverflow.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AnswerList {
    @SerializedName("items")
    @Expose
    lateinit var answers: List<Answer>
}