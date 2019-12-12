package com.example.stackoverflow.Adapter.main

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stackoverflow.R
import com.example.stackoverflow.model.Question
import kotlinx.android.synthetic.main.question_item_layout.view.*
import java.text.SimpleDateFormat
import java.util.*


class QuestionRecyclerAdapter(
    var questions: List<Question>,
    val context: Context,
    val onClickListener: onClickListener
) :
    RecyclerView.Adapter<QuestionRecyclerAdapter.QuestionHolder>() {

    fun updateList(questions: List<Question>) {
        this.questions = questions
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QuestionRecyclerAdapter.QuestionHolder {
        return QuestionHolder(
            LayoutInflater.from(context).inflate(
                R.layout.question_item_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    override fun onBindViewHolder(holder: QuestionRecyclerAdapter.QuestionHolder, position: Int) {
        holder.questionText.text = Html.fromHtml(questions[position].title)
        holder.userName.text = Html.fromHtml(questions[position].owner.display_name)
        holder.questionDate.text = Html.fromHtml(getDate(questions[position].creation_date))
        holder.questionAnswer.text = Html.fromHtml(questions[position].answer_count.toString())
        holder.questionScore.text = Html.fromHtml(questions[position].score.toString())
        holder.questionView.text = Html.fromHtml(questions[position].view_count.toString())
        Glide.with(context).load(questions[position].owner.profile_image).into(holder.userImage)
        holder.userImage.animation =
            AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation)
        holder.container.animation =
            AnimationUtils.loadAnimation(context, R.anim.fade_scale_animation)
        holder.itemView.setOnClickListener {
            onClickListener.onClicked(questions[position].question_id)
        }


    }


    class QuestionHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionText = itemView.questionText

        val questionDate = itemView.questionDate
        val userImage = itemView.userImg
        val userName = itemView.userName
        val container = itemView.container
        val questionScore = itemView.questionScore
        val questionAnswer = itemView.questionAnswers
        val questionView = itemView.questionViews
    }

    private fun getDate(millis: Long): String {
        val formatter = SimpleDateFormat("dd-MMM-yyyy")

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        val calendar = Calendar.getInstance()
        calendar.setTimeInMillis(millis * 1000)
        return formatter.format(calendar.getTime())
    }
}