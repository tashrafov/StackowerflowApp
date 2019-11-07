package com.example.stackoverflow.Adapter.main

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stackoverflow.R
import com.example.stackoverflow.model.Question
import kotlinx.android.synthetic.main.question_item_layout.view.*

class QuestionRecyclerAdapter(
    val questions: List<Question>,
    val context: Context,
    val onClickListener: onClickListener
) :
    RecyclerView.Adapter<QuestionRecyclerAdapter.QuestionHolder>() {


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
        if (questions[position].answer_count == 0) {
            holder.answerCount.background =
                context.resources.getDrawable(R.drawable.not_answered_background)
            holder.answerCount.setTextColor(context.resources.getColor(android.R.color.darker_gray))
        } else {
            holder.answerCount.background =
                context.resources.getDrawable(R.drawable.answered_background)
            holder.answerCount.setTextColor(context.resources.getColor(android.R.color.holo_green_light))
        }
        holder.answerCount.text = questions[position].answer_count.toString().trim()
        holder.itemView.setOnClickListener {
            onClickListener.onClicked(questions[position].question_id)
        }


    }


    class QuestionHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionText = itemView.questionText
        val answerCount = itemView.answerCount
    }

}