package com.example.journalsapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.journalsapp.data.Journal
import com.example.journalsapp.ui.screens.JournalApp
import org.json.JSONArray
import org.json.JSONObject
import java.nio.charset.Charset

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val journalList = remember{
                mutableStateOf(listOf<Journal>())
            }

            getData(this@MainActivity,journalList)
            JournalApp(journalList)
        }
    }
}

private fun getData(
    context: Context,
    journalList: MutableState<List<Journal>>,
    offset: Int = 0,
    limit: Int = 10){
    val url = "http://pressa-api.imb2bs.com/api/v1/journal/categories/0/?" +
            "offset=$offset" +
            "&limit=$limit"
    val requestBody = ""

    val queue = Volley.newRequestQueue(context)

    val stringRequest: StringRequest =
        object : StringRequest(
            Method.GET,
            url,
            Response.Listener { response ->
                Log.d(TAG,response)
                val list = getJournals(response)
                journalList.value = list
            },
            Response.ErrorListener {
                Log.d(TAG,"VolleyError: $it")
            }
        ){
            override fun getHeaders(): MutableMap<String,String>{
                val headers = HashMap<String,String>()
                headers["User-Agent"] = "PostmanRuntime/7.31.0"
                headers["Project-id"] = "2"
                headers["Language"] = "ru"
                return headers
            }

            override fun getBody(): ByteArray {
                return requestBody.toByteArray(Charset.defaultCharset())
            }
        }
    queue.add(stringRequest)
}

private fun getJournals(respose: String): List<Journal>{
    if (respose.isNullOrEmpty()) return emptyList()

    val jsonArray = JSONArray(respose)
    val list = ArrayList<Journal>()

    for (i in 0 until jsonArray.length()){
        val item  = jsonArray[i] as JSONObject
        list.add(
            Journal(
                item.getString("id"),
                item.getString("name"),
                item.getString("description"),
                item.getJSONObject("last_issue").getString("cover")
            )
        )
    }
    return list
}

private fun getInfo(list: List<Journal>){
    list.forEach {
        Log.d(TAG,"id: ${it.id}")
        Log.d(TAG,"name: ${it.name}")
        Log.d(TAG,"desc: ${it.description}")
        Log.d(TAG,"cover: ${it.cover}")
    }
}

