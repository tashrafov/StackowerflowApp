package com.example.stackoverflow.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stackoverflow.Adapter.main.QuestionRecyclerAdapter
import com.example.stackoverflow.Adapter.main.onClickListener
import com.example.stackoverflow.BaseApp
import com.example.stackoverflow.R
import com.example.stackoverflow.model.Question
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), IMainPresenter.View, onClickListener {
    override fun onClicked(id: Long) {
        presenter.showDetailed(id)
    }

    @Inject
    lateinit var presenter: IMainPresenter.Presenter

    override fun bindQuestion(list: List<Question>?) {
        val adapter = list?.let { QuestionRecyclerAdapter(list, this, this) }
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        mainRecyclerView.adapter = adapter
    }

    override fun onFailure(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun getRootView(): View? {
        return this.getRootView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as BaseApp).baseComponent.inject(this)
        presenter.initView(this, this)
        presenter.getQuestions()
        mainRefreshView.setOnRefreshListener {
            presenter.getQuestions()
            mainRefreshView.isRefreshing = false
        }
    }
}
