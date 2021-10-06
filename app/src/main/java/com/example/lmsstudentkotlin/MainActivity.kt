package com.example.lmsstudentkotlin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.lmsstudentkotlin.R
import com.example.lmsstudentkotlin.activity.Dashboard
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private var requestQueue: RequestQueue? = null
    lateinit var loginButton : Button
    lateinit var email : EditText
    lateinit var password : EditText
    private val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        password = findViewById(R.id.passwordLogin)
        loginButton = findViewById(R.id.buttonLogin)
        email = findViewById(R.id.emailLogin)

        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val sharedEmail = sharedPreferences.getString("uID","")

        if(!sharedEmail.isNullOrEmpty()){
            intent = Intent(applicationContext, Dashboard::class.java)
            startActivity(intent)
        }



        loginButton.setOnClickListener({
            if(email.text.toString().isEmpty()){
                Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show()
            }else if(password.text.toString().isEmpty()){
                Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show()
            }else{
                apiLogin()
            }
        })
    }

    private fun apiLogin() {
        val url  = "http://dlapi.ambiguousit.com/studentLogin.php"

        val stringRequest = object : StringRequest(
                Method.POST, url,
                Response.Listener<String> { response ->
                    try {
                        val obj = JSONObject(response)
                        var jsonObject = obj.getJSONObject("result")
                        if(obj.getString("msg").equals("Login Successfull!")) {

                            val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
                            val editor:SharedPreferences.Editor =  sharedPreferences.edit()

                            editor.putString("classID",jsonObject.getString("classID"))
                            editor.putString("uID",jsonObject.getString("uID"))
                            editor.apply()
                            editor.commit()

                            intent = Intent(applicationContext, Dashboard::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this, "Please check the credentials...", Toast.LENGTH_SHORT).show()
                        }
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
                params.put("request", "studentLogin")
                params.put("uID", email.text.toString())
                params.put("password", password.text.toString())
                return params
            }
        }
        requestQueue = Volley.newRequestQueue(this)
        requestQueue?.add(stringRequest)
    }
}