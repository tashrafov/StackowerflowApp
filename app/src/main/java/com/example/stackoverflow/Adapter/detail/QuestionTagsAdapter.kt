package com.example.stackoverflow.Adapter.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stackoverflow.R
import kotlinx.android.synthetic.main.tag_item.view.*

class QuestionTagsAdapter(
    val tags: List<String>,
    val context: Context,
    var tagClickListener: tagClickListener
) :
    RecyclerView.Adapter<QuestionTagsAdapter.TagHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagHolder {
        return QuestionTagsAdapter.TagHolder(
            LayoutInflater.from(context).inflate(
                R.layout.tag_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return tags.size
    }

    override fun onBindViewHolder(holder: TagHolder, position: Int) {
        holder.tag?.text = tags[position]
        holder.itemView.setOnClickListener { tagClickListener.onClicked(tags[position]) }
    }

    class TagHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tag = itemView.questionTag
    }
}