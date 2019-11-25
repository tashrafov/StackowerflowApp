package com.example.stackoverflow.ui.details

import android.content.Context
import com.example.stackoverflow.model.Answer
import com.example.stackoverflow.model.Question
import com.example.stackoverflow.ui.common.BaseView

interface IDetailPresenter {
    interface Presenter {
        fun initView(view: View)
        fun getQuestion(id: Long)
        fun getAnswers(id: Long)
        fun tagClicked(tag:String)
    }

    interface View : BaseView {
        fun onFailure(message: String?)
        fun setQuestion(question: Question)
        fun setAnswers(answers: List<Answer>)
        fun showProgressBar(state: Boolean)
    }
}