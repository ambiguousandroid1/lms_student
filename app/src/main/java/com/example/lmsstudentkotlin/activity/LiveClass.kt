package com.example.lmsstudentkotlin.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.lmsstudentkotlin.Adapter.AdapterLiveClass
import com.example.lmsstudentkotlin.R
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList

class LiveClass : AppCompatActivity() {
    private var requestQueue: RequestQueue? = null
    var subName : String = ""
    var subID : String = ""
    lateinit var recLive : RecyclerView

    private var subjectid: MutableList<String> = ArrayList()
    var id: MutableList<String> = ArrayList()
    private var classLink: MutableList<String> = ArrayList()
    private var topic: MutableList<String> = ArrayList()
    private var startDate: MutableList<String> = ArrayList()
    private var duration: MutableList<String> = ArrayList()
    private var password: MutableList<String> = ArrayList()
    private var meetingID: MutableList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_class)

        subID = intent.getStringExtra("ids").toString()
        subName = intent.getStringExtra("name").toString()

        recLive = findViewById(R.id.recLive)

        apiResponse()
        //request:getLiveClassList
        //subjectID:78

    }

    private fun apiResponse() {
        val url  = "http://dlapi.ambiguousit.com/studentLiveClass.php"

        val stringRequest = object : StringRequest(
                Method.POST, url,
                Response.Listener<String> { response ->
                    try {
                        val obj = JSONObject(response)
                        var jsonObject = obj.getJSONObject("result")
                        var jsonArray = jsonObject.getJSONArray("liveClassData")
                        for(i in 0 until jsonArray.length()){
                            var jsonObject2 = jsonArray.getJSONObject(i)
                            id.add(jsonObject2.getString("id"))
                            subjectid.add(jsonObject2.getString("subjectID"))
                            classLink.add(jsonObject2.getString("classLink"))
                            topic.add(jsonObject2.getString("topic"))
                            startDate.add(jsonObject2.getString("startDate"))
                            duration.add(jsonObject2.getString("duration"))
                            password.add(jsonObject2.getString("password"))
                            meetingID.add(jsonObject2.getString("meetingID"))

                        }

                    val adapterLiveClass = AdapterLiveClass(this, id, classLink, topic, startDate, duration, password, meetingID, subName )
                        recLive.layoutManager = LinearLayoutManager(this)
                        recLive.setHasFixedSize(true)
                        recLive.adapter = adapterLiveClass

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                object : Response.ErrorListener {
                    override fun onErrorResponse(volleyError: VolleyError) {
                        Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show()
                    }
                }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params.put("request", "getLiveClassList")
                params.put("subjectID", "78")

                return params
            }
        }
        requestQueue = Volley.newRequestQueue(this)
        requestQueue?.add(stringRequest)
    }

}