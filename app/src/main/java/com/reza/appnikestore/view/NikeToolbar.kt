package com.reza.appnikestore.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.reza.appnikestore.R
import com.reza.appnikestore.databinding.ViewToolbarBinding

class NikeToolbar(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    private val binding =
        ViewToolbarBinding.inflate(LayoutInflater.from(context), this, true)

    var onBackButtonClickListener: View.OnClickListener? = null
        set(value) {
            field = value
            binding.backBtn.setOnClickListener(onBackButtonClickListener)
        }

    init {
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.NikeToolbar)
            val title = a.getString(R.styleable.NikeToolbar_nt_title)
            if (title != null && title.isNotEmpty()) {
                binding.toolbarTitleTv.text = title
            }
            a.recycle()
        }
    }
}