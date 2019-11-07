package com.example.stackoverflow.ui.main

import android.content.Context
import android.content.Intent
import com.example.stackoverflow.model.QuestionList
import com.example.stackoverflow.network.GetDataService
import com.example.stackoverflow.network.RetrofitClassInstance
import com.example.stackoverflow.ui.details.DetailsActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter : IMainPresenter.Presenter {
    override fun getQuestions(content: String) {
        val call: Call<QuestionList> = dataService.getSearchedQuestions(content)
        call.enqueue(object : Callback<QuestionList> {
            override fun onFailure(call: Call<QuestionList>, t: Throwable) {
                view?.onFailure(t.message)
            }

            override fun onResponse(
                call: Call<QuestionList>,
                response: Response<QuestionList>
            ) {
                if (response.isSuccessful) {
                    view?.bindQuestion(response.body()?.questions)
                } else {
                    view?.onFailure(response.errorBody().toString())
                }
            }
        })
    }

    override fun initView(context: Context, view: IMainPresenter.View) {
        this.view = view
        this.dataService = RetrofitClassInstance.dataServiceInstance!!
        this.context = context
    }


    var view: IMainPresenter.View? = null
    var QUESTION_LIST_SIZE: Int = 20
    lateinit var dataService: GetDataService
    lateinit var context: Context


    override fun getQuestions() {
        val call: Call<QuestionList> = dataService.getQuestions(QUESTION_LIST_SIZE)
        call.enqueue(object : Callback<QuestionList> {
            override fun onFailure(call: Call<QuestionList>, t: Throwable) {
                view?.onFailure(t.message)
            }

            override fun onResponse(
                call: Call<QuestionList>,
                response: Response<QuestionList>
            ) {
                if (response.isSuccessful) {
                    view?.bindQuestion(response.body()?.questions)
                } else {
                    view?.onFailure(response.errorBody().toString())
                }
            }
        })
    }

    override fun showDetailed(id: Long) {
        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra("id", id)
        context.startActivity(intent)
    }
}