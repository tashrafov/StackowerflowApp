package com.example.stackoverflow.ui.main

import com.example.stackoverflow.model.Question
import com.example.stackoverflow.model.QuestionList
import com.example.stackoverflow.network.GetDataService
import com.example.stackoverflow.network.RetrofitClassInstance
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter : IMainPresenter.Presenter {

    var view: IMainPresenter.View? = null
    var QUESTION_LIST_SIZE: Int = 50
    var QUESTION_MAX_LIST_SIZE: Int = 150
    lateinit var dataService: GetDataService
    var questions = listOf<Question>()

    override fun getMoreQuestions() {
//        if (questions.size == QUESTION_MAX_LIST_SIZE)
//            questions =
        getQuestions()

    }

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

    override fun initView(view: IMainPresenter.View) {
        this.view = view
        this.dataService = RetrofitClassInstance.dataServiceInstance!!
    }

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
                    questions = questions.plus(response.body()?.questions!!)
                    view?.bindQuestion(questions)
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

    override fun showDetailed(id: Long) {
        view?.showDetailed(id)
    }
}