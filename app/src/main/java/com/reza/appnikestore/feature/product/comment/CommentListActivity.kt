package com.reza.appnikestore.feature.product.comment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reza.appnikestore.common.EXTRA_KEY_ID
import com.reza.appnikestore.data.Comment
import com.reza.appnikestore.databinding.ActivityCommentListBinding
import com.reza.appnikestore.feature.product.CommentAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CommentListActivity : AppCompatActivity() {
    lateinit var binding: ActivityCommentListBinding
    private val commentAdapter = CommentAdapter(true)
    val viewModel: CommentListViewModel by viewModel {
        parametersOf(
            intent.extras!!.getInt(
                EXTRA_KEY_ID
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeCommentList()
        intiRecyclerView()
    }

    private fun observeCommentList() {
        viewModel.commentsLiveData.observe(this) {
            commentAdapter.comments = it as ArrayList<Comment>
        }
    }

    private fun intiRecyclerView() {
        binding.commentsRv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.commentsRv.adapter = commentAdapter
    }
}