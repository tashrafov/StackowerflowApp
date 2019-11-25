package com.example.stackoverflow.ui.details

import android.content.Context
import com.example.stackoverflow.model.AnswerList
import com.example.stackoverflow.model.QuestionList
import com.example.stackoverflow.network.GetDataService
import com.example.stackoverflow.network.RetrofitClassInstance
import com.example.stackoverflow.ui.main.IMainPresenter
import com.example.stackoverflow.ui.main.MainActivity
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter : IDetailPresenter.Presenter {
    override fun tagClicked(tag: String) {
        //TODO implement the tag clicked method
    }

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
                    try {
                        val error = JSONObject(response.errorBody()?.string().toString())
                        view?.onFailure(error.getString("error_message"))
                    } catch (e: Exception) {
                        view?.onFailure(e.message)
                    }
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
                    try {
                        val error = JSONObject(response.errorBody()?.string().toString())
                        view?.onFailure(error.getString("error_message"))
                    } catch (e: Exception) {
                        view?.onFailure(e.message)
                    }
                }
            }

        })
    }
    var view: IDetailPresenter.View? = null


    lateinit var dataService: GetDataService

    override fun initView(view: IDetailPresenter.View) {
        this.view = view
        dataService = RetrofitClassInstance.dataServiceInstance!!
    }
}