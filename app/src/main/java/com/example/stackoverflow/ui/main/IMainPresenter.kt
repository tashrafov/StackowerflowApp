package com.example.stackoverflow.ui.main

import android.content.Context
import com.example.stackoverflow.model.Question
import com.example.stackoverflow.ui.common.BaseView

interface IMainPresenter {
    interface Presenter {
        fun getQuestions()
        fun showDetailed(id: Long)
        fun initView(context: Context, view: View)
        fun getQuestions(content:String)
    }

    interface View : BaseView {
        fun bindQuestion(list: List<Question>?)
        fun onFailure(message: String?)
    }
}