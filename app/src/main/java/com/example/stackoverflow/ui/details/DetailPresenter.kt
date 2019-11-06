package com.example.stackoverflow.ui.details

import android.content.Context
import com.example.stackoverflow.model.AnswerList
import com.example.stackoverflow.model.QuestionList
import com.example.stackoverflow.network.GetDataService
import com.example.stackoverflow.network.RetrofitClassInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter : IDetailPresenter.Presenter {
    override fun getQuestion(id: Long) {
        view?.showProgressBar(true)
        val call: Call<QuestionList> = dataService?.getQuestionWithBody(id)
        call.enqueue(object : Callback<QuestionList> {
            override fun onFailure(call: Call<QuestionList>, t: Throwable) {
                view?.showProgressBar(false)
                view?.onFailure(t.message)
            }

            override fun onResponse(call: Call<QuestionList>, response: Response<QuestionList>) {
                view?.showProgressBar(false)
                if (response.isSuccessful) {
                    response.body()?.questions?.get(0)?.let { view?.setQuestion(it) }
                } else {
                    view?.onFailure(response.errorBody().toString())
                }
            }

        })
    }

    override fun getAnswers(id: Long) {
        view?.showProgressBar(true)
        val call: Call<AnswerList> = dataService?.getAnswersWithBody(id)
        call.enqueue(object : Callback<AnswerList> {
            override fun onFailure(call: Call<AnswerList>, t: Throwable) {
                view?.showProgressBar(false)
                view?.onFailure(t.message)
            }

            override fun onResponse(call: Call<AnswerList>, response: Response<AnswerList>) {
                view?.showProgressBar(false)
                if (response.isSuccessful) {
                    response.body()?.answers?.let { view?.setAnswers(it) }
                } else {
                    view?.onFailure(response.errorBody().toString())
                }
            }

        })
    }

    lateinit var context: Context
    var view: IDetailPresenter.View? = null


    lateinit var dataService: GetDataService

    override fun initView(context: Context, view: IDetailPresenter.View) {
        this.context = context
        this.view = view
        dataService = RetrofitClassInstance.dataServiceInstance!!
    }
}