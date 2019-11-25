package com.example.stackoverflow.ui.main

import com.example.stackoverflow.model.Question
import com.example.stackoverflow.ui.common.BaseView

interface IMainPresenter {
    interface Presenter {
        fun getQuestions()
        fun showDetailed(id: Long)
        fun initView(view: View)
        fun getQuestions(content: String)
        fun getMoreQuestions()
    }

    interface View : BaseView {
        fun bindQuestion(list: List<Question>?)
        fun showDetailed(id: Long)
        fun onFailure(message: String?)
    }
}