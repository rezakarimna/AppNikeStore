package com.reza.appnikestore.feature.product.comment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reza.appnikestore.common.EXTRA_KEY_ID
import com.reza.appnikestore.common.NikeActivity
import com.reza.appnikestore.data.Comment
import com.reza.appnikestore.databinding.ActivityCommentListBinding
import com.reza.appnikestore.feature.product.CommentAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CommentListActivity : NikeActivity() {
    lateinit var binding: ActivityCommentListBinding
    private val commentAdapter = CommentAdapter(true)
    private val viewModel: CommentListViewModel by viewModel {
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
        observeProgressbar()
        observeCommentList()
        intiRecyclerView()
        onBack()
    }

    private fun observeProgressbar() {
        viewModel.progressBarLiveData.observe(this) {
            setProgressIndicator(it)
        }
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

    private fun onBack() {
        binding.commentListToolbar.onBackButtonClickListener = View.OnClickListener { finish() }
    }


}