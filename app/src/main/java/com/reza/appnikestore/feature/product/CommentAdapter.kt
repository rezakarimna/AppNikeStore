package com.reza.appnikestore.feature.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reza.appnikestore.data.Comment
import com.reza.appnikestore.databinding.ItemCommentBinding

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    var comments = ArrayList<Comment>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindComment(comment: Comment) {
            binding.apply {
                commentTitleTv.text = comment.title
                commentAuthor.text = comment.author.email
                commentContentTv.text = comment.content
                commentDateTv.text = comment.date
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindComment(comments[position])
    }

    override fun getItemCount(): Int {
        return if (comments.size > 3) 3 else comments.size
    }
}