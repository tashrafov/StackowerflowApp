package com.example.stackoverflow.Adapter.detail

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stackoverflow.R
import com.example.stackoverflow.model.Answer
import kotlinx.android.synthetic.main.answer_item_layout.view.*

class QuestionAnswerAdapter(val answerList: List<Answer>, val context: Context) :
    RecyclerView.Adapter<QuestionAnswerAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(context).inflate(
                R.layout.answer_item_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return answerList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        when (answerList[position].is_accepted) {
            true -> holder.is_answered.setImageResource(R.drawable.ic_answered)
        }
        Glide.with(context).load(answerList[position].owner.profile_image).into(holder.ownerImage)
        holder.ownerName.text = Html.fromHtml(answerList[position].owner.display_name)
        holder.answer.text = Html.fromHtml(answerList[position].body)

    }


    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val is_answered = itemView.answerState
        val ownerImage = itemView.ownerAvatar
        val ownerName = itemView.ownerName
        val answer = itemView.answerBody
    }
}