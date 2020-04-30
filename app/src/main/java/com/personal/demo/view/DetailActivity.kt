package com.personal.demo.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.personal.demo.Constant
import com.personal.demo.R
import com.personal.demo.model.Time

class DetailActivity : AppCompatActivity() {

    private lateinit var mInfo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        mInfo = findViewById(R.id.detail_info)
        val detail = intent.getParcelableExtra<Time>(Constant.TIME_INFO)
        mInfo.text = detail.startTime + "\n" + detail.endTime
    }
}