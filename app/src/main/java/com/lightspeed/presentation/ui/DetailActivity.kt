package com.lightspeed.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.lightspeed.lightspeedproject.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        receiveIntent()
    }

    private fun receiveIntent() {
        val intentReceivedDownloadUrl = intent.getStringExtra("download_url")
        val intentReceivedExpenseAuthor = intent.getStringExtra("author")

        Glide.with(img_output_img)
            .load(intentReceivedDownloadUrl)
            .placeholder(R.drawable.photols)
            .error(R.drawable.photols)
            .into(img_output_img)

        tv_output_text.text = intentReceivedExpenseAuthor
    }

}