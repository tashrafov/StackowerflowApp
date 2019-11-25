package com.example.stackoverflow.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.net.URL

class Owner {
    @SerializedName("reputation")
    @Expose
    var reputation: Int = 0
    @SerializedName("user_id")
    @Expose
    var user_id: Long = 0L
    @SerializedName("user_type")
    @Expose
    lateinit var user_type: String
    @SerializedName("profile_image")
    @Expose
    var profile_image: String? = null
    @SerializedName("display_name")
    @Expose
    lateinit var display_name: String
    @SerializedName("link")
    @Expose
    lateinit var link: URL
}