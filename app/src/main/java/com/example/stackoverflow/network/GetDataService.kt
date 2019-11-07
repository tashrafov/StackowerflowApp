package com.example.stackoverflow.network

import com.example.stackoverflow.model.AnswerList
import com.example.stackoverflow.model.QuestionList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetDataService {
    @GET("/2.2/questions?order=desc&sort=activity&site=stackoverflow")
    fun getQuestions(@Query("pagesize") size: Int): Call<QuestionList>

    @GET("/2.2/questions/{id}?order=desc&sort=activity&site=stackoverflow&filter=withbody")
    fun getQuestionWithBody(@Path("id") id: Long): Call<QuestionList>

    @GET("/2.2/questions/{id}/answers?order=desc&sort=activity&site=stackoverflow&filter=withbody")
    fun getAnswersWithBody(@Path("id") id: Long): Call<AnswerList>

    @GET("/2.2/search?order=desc&sort=activity&site=stackoverflow")
    fun getSearchedQuestions(@Query("intitle") content: String): Call<QuestionList>

}
