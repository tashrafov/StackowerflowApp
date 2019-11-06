package com.example.stackoverflow.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Answer {
    @SerializedName("owner")
    @Expose
    lateinit var owner: Owner
    @SerializedName("is_accepted")
    @Expose
    var is_accepted: Boolean = false
    @SerializedName("score")
    @Expose
    var score: Int = 0
    @SerializedName("last_activity_date")
    @Expose
    var last_activity_date: Long = 0L
    @SerializedName("creation_date")
    @Expose
    var creation_date: Long = 0L
    @SerializedName("answer_id")
    @Expose
    var answer_id: Long = 0L
    @SerializedName("question_id")
    @Expose
    var question_id: Long = 0L
    @SerializedName("body")
    @Expose
    lateinit var body: String

}