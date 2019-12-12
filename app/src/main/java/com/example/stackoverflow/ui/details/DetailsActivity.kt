package com.example.stackoverflow.ui.details

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.stackoverflow.Adapter.detail.QuestionAnswerAdapter
import com.example.stackoverflow.Adapter.detail.QuestionTagsAdapter
import com.example.stackoverflow.Adapter.detail.tagClickListener
import com.example.stackoverflow.BaseApp
import com.example.stackoverflow.R
import com.example.stackoverflow.model.Answer
import com.example.stackoverflow.model.Question
import com.example.stackoverflow.ui.main.IMainPresenter
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject

class DetailsActivity : AppCompatActivity(), IDetailPresenter.View, tagClickListener {
    override fun onClicked(tag: String) {
        mainPresenter.getTaggedQuestions(tag)
        finish()
    }

    override fun setAnswers(answers: List<Answer>) {
        var sortedList = answers.sortedWith(compareBy(Answer::is_accepted, Answer::score)).reversed()
        detailRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        detailRecyclerView.adapter = QuestionAnswerAdapter(sortedList, this)
        detailRecyclerView.isNestedScrollingEnabled = false
    }


    override fun showProgressBar(state: Boolean) {
        when (state) {
            true -> detailProgressBar.visibility = View.VISIBLE
            false -> detailProgressBar.visibility = View.INVISIBLE
        }
    }

    override fun onFailure(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun setQuestion(question: Question) {
        userName.text = Html.fromHtml(question.owner.display_name)
        Glide.with(this).load(question.owner.profile_image).into(userAvatar)
        questionTitle.text = Html.fromHtml(question.title)
        questionBody.text = Html.fromHtml(question.body)
        detailTagRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        detailTagRecyclerView.adapter = QuestionTagsAdapter(question.tags, this, this)
        if (question.is_answered) {
            presenter.getAnswers(question.question_id)
        }
    }


    override fun getRootView(): View? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Inject
    lateinit var presenter: IDetailPresenter.Presenter
    @Inject
    lateinit var mainPresenter: IMainPresenter.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        (application as BaseApp).baseComponent.inject(this)
        presenter.initView(this)
        presenter.getQuestion(intent.getLongExtra("id", 0L))

    }
}
