package com.example.stackoverflow.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.net.URL

class Question {
    @SerializedName("tags")
    @Expose
    lateinit var tags: List<String>
    @SerializedName("owner")
    @Expose
    lateinit var owner: Owner
    @SerializedName("is_answered")
    @Expose
    var is_answered: Boolean = false
    @SerializedName("view_count")
    @Expose
    var view_count: Int = 0
    @SerializedName("answer_count")
    @Expose
    var answer_count: Int = 0
    @SerializedName("score")
    @Expose
    var score: Int = 0
    @SerializedName("last_activity_date")
    @Expose
    var last_activity_date: Long = 0L
    @SerializedName("creation_date")
    @Expose
    var creation_date: Long = 0
    @SerializedName("question_id")
    @Expose
    var question_id: Long = 0
    @SerializedName("link")
    @Expose
    lateinit var link: URL
    @SerializedName("title")
    @Expose
    lateinit var title: String
    @SerializedName("body")
    @Expose
    lateinit var body: String
}