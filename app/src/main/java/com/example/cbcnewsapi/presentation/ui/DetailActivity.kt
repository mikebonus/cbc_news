package com.example.cbcnewsapi.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.cbcnewsapi.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    val TAG = "TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        Log.d(TAG, "onCreate: onCreate() --- DETAIL-ACTIVITY.. ")

        val intentReceivedZero = intent.getStringExtra("image1234")
        val intentReceivedOne = intent.getStringExtra("title")
        val intentReceivedTwo = intent.getStringExtra("type")
        val intentReceivedThree = intent.getStringExtra("publish_date")

//        Log.d(TAG, "onCreate: intentReceivedOne is----- $intentReceivedOne")
//        Log.d(TAG, "onCreate: intentReceivedTwo is----- $intentReceivedTwo")
//        Log.d(TAG, "onCreate: intentReceivedThree is----- $intentReceivedThree")
//        Log.d(TAG, "onCreate: intentReceivedFour is -----" + intentReceivedThree?.toUri())

        Glide.with(img_image_view12)
            .load(intentReceivedZero)
            .placeholder(R.drawable.cbc_logo_logo)
            .error(R.drawable.cbc_logo_logo)
            .into(img_image_view12)

        // TEXT-VIEW + IMAGE-VIEW
        tv_text_viewOne.text = intentReceivedOne
        tv_text_viewTwo.text = intentReceivedTwo
        tv_text_viewThree.text = intentReceivedThree

//        Log.d(TAG, "onCreate: intentReceivedFive is -----" + intentReceivedThree?.toUri())

    }
}