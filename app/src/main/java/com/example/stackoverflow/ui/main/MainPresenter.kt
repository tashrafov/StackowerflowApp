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
    override fun resetPageNumber() {
        currentPage = 1
    }

    override fun increasePageNumber() {
        currentPage++
    }

    override fun getTaggedQuestions(tag: String) {
        checkLastOperation(2)
        value = tag
        val call: Call<QuestionList> =
            dataService.getTaggedQuestions(tag, currentPage, QUESTION_LIST_SIZE)
        call.enqueue(object : Callback<QuestionList> {
            override fun onFailure(call: Call<QuestionList>, t: Throwable) {
                view?.onFailure(t.message)
            }

            override fun onResponse(call: Call<QuestionList>, response: Response<QuestionList>) {
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
        increasePageNumber()
    }

    var view: IMainPresenter.View? = null
    var QUESTION_LIST_SIZE: Int = 50
    var QUESTION_MAX_LIST_SIZE: Int = 150
    var currentPage = 1
    lateinit var dataService: GetDataService
    var questions = listOf<Question>()
    private var lastOperation: Int = 0 // 0 - getQuestions, 1 - getSearch, 2 - getTagged
    var value: String = ""

    override fun getMoreQuestions() {
        when (lastOperation) {
            0 -> getQuestions()
            1 -> getQuestions(value)
            2 -> getTaggedQuestions(value)
        }
    }


    override fun getQuestions(content: String) {
        checkLastOperation(1)
        value = content
        val call: Call<QuestionList> =
            dataService.getSearchedQuestions(content, currentPage, QUESTION_LIST_SIZE)
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
        increasePageNumber()
    }

    override fun initView(view: IMainPresenter.View) {
        this.view = view
        this.dataService = RetrofitClassInstance.dataServiceInstance!!
    }

    override fun getQuestions() {
        checkLastOperation(0)
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

    fun checkLastOperation(lastOperation: Int) {
        if (this.lastOperation != lastOperation) {
            resetPageNumber()
            this.lastOperation = lastOperation
            questions = emptyList()
        }

    }
}