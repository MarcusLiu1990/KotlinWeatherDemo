package com.personal.demo.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.cmoney.demo.base.BaseAdapter
import com.personal.demo.Constant
import com.personal.demo.R
import com.personal.demo.adapter.WeatherAdapter
import com.personal.demo.model.Time
import com.personal.demo.model.WeatherInfo
import com.personal.demo.network.ApiRequest

class MainActivity : AppCompatActivity() {

    private lateinit var mList : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mList = findViewById(R.id.list)

        ApiRequest().getTimeInfo(object : ApiRequest.OnRequestCallback<WeatherInfo>{
            override fun onSuccess(data : WeatherInfo){
                val adapter = WeatherAdapter()
                var list : ArrayList<Time> = arrayListOf()
                for(element in data.records.location[0].weatherElement[0].time){
                    list.add(element)
                    list.add(Time("", ""))
                }
                adapter.addItems(list)
                adapter.setOnItemClickListener(object : BaseAdapter.OnItemClickListener<Time> {
                    override fun onItemClick(position: Int, data: Time){
                        val intent = Intent(applicationContext, DetailActivity::class.java).apply {
                            putExtra(Constant.TIME_INFO, data)
                        }
                        startActivity(intent)
                    }
                })
                mList.adapter = adapter
            }

            override fun onFailure(message : String){
                println(message)
            }
        })
    }
}
