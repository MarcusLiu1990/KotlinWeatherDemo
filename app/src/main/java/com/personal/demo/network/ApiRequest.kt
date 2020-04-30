package com.personal.demo.network

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.personal.demo.model.WeatherInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL

class ApiRequest{

    interface OnRequestCallback<T>{

        fun onSuccess(data : T)

        fun onFailure(message: String)
    }

    fun getTimeInfo(callback: OnRequestCallback<WeatherInfo>) {
        startRequest("https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-C0032-001?Authorization=CWB-9D9CACE6-042D-468A-9D16-540CAD2B2F99&locationName=%E8%87%BA%E5%8C%97%E5%B8%82", callback)
    }

    private inline fun <reified T> startRequest(url: String, callback : OnRequestCallback<T>){
        GlobalScope.launch (Dispatchers.Default){
            try {
                val url = URL(url)
                var result : T? = null
                with(url.openConnection() as HttpURLConnection) {
                    try{
                        result = fromJson<T>(inputStream.bufferedReader().readText())
                    }
                    catch (e : Exception){
                        var exc: String = e.message + "\n"
                        val trace: Array<StackTraceElement> =  e.stackTrace
                        for (traceElement in trace) exc += "\nat $traceElement"
                        callback.onFailure(exc)
                    }
                }

                GlobalScope.launch (Dispatchers.Main){
                    callback.onSuccess(result!!)
                }
            }
            catch (e : Exception){

            }
        }
    }

    private inline fun <reified T> fromJson(json: String): T {
        return Gson().fromJson(json, object: TypeToken<T>(){}.type)
    }
}