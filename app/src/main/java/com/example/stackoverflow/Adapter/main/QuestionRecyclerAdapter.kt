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
        holder.questionText?.text = Html.fromHtml(questions[position].title)
        when (questions[position].is_answered) {
            true -> holder.questionState?.setImageResource(R.drawable.ic_answered)
            false -> holder.questionState?.setImageResource(R.drawable.ic_not_answered)
        }
        holder.itemView.setOnClickListener {
            onClickListener.onClicked(questions[position].question_id)
        }


    }


    class QuestionHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionText = itemView.questionText
        val questionState = itemView.questionState
    }

}