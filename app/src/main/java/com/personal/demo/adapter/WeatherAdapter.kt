package com.personal.demo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cmoney.demo.base.BaseAdapter
import com.cmoney.demo.base.BaseViewHolder
import com.personal.demo.R
import com.personal.demo.model.Time

class WeatherAdapter : BaseAdapter<Time>(){

    companion object {
        private const val TYPE_INFO = 0
        private const val TYPE_IMG = 1
    }

    class ImgViewHolder : BaseViewHolder<Time> {

        constructor(v : View, listener: OnItemClickListener<Time>) : super(v)

        override fun onBind(position: Int, data: Time) {
        }
    }

    class InfoViewHolder : BaseViewHolder<Time> {

        var title: TextView? = null
        private var onItemClickListener: OnItemClickListener<Time>? = null

        constructor(v : View, listener: OnItemClickListener<Time>) : super(v) {
            title = v.findViewById(R.id.info_text)
            onItemClickListener = listener
        }

        override fun onBind(position: Int, data: Time) {
            title!!.text = data.startTime + "\n" + data.endTime
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Time> {
        if(viewType == TYPE_INFO)
            return InfoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_info, null), mOnItemClickListener!!)
        else
            return ImgViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_img, null), mOnItemClickListener!!)

    }

    override fun onBindViewHolder(holder: BaseViewHolder<Time>, position: Int) {
        holder.onBind(position, mData!![position])
        holder.itemView.setOnClickListener {
            if(getItemViewType(position) == TYPE_INFO)
                mOnItemClickListener!!.onItemClick(position, mData.get(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(position % 2 == 0)
            TYPE_INFO
        else
            TYPE_IMG
    }

}