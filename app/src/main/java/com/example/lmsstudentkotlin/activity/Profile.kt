package com.example.lmsstudentkotlin.activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.lmsstudentkotlin.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import org.json.JSONException
import org.json.JSONObject

class Profile : AppCompatActivity() {

    private var requestQueue: RequestQueue? = null
    var proId: String? = null
    var selecte: String? = null
    private var fname: TextView? = null
    private var mothername: TextView? = null
    private var mobile: TextView? = null
    private var emerno: TextView? = null
    private var classname: TextView? = null
    private var uid: TextView? = null
    private var rollno: TextView? = null
    private var bllodgroup: TextView? = null
    private var address: TextView? = null
    private var status: TextView? = null
    private var name: TextView? = null
    private var profiepic: CircleImageView? = null
    private var rollNO: TextView? = null
    private var moName: TextView? = null
    private var other: TextView? = null
    lateinit var progressSDialog : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        progressSDialog = ProgressDialog(this)
        progressSDialog.setMessage("Loading...")
        progressSDialog.setCancelable(false)
        progressSDialog.show()

        fname = findViewById(R.id.fname)
        mothername = findViewById(R.id.mothername)
        mobile = findViewById(R.id.mobile)
        emerno = findViewById(R.id.emerno)
        classname = findViewById(R.id.classname)
        rollno = findViewById(R.id.rollno)
        uid = findViewById(R.id.uid)
        bllodgroup = findViewById(R.id.bllodgroup)
        address = findViewById(R.id.gender)
        status = findViewById(R.id.status)
        name = findViewById(R.id.name)
        profiepic = findViewById(R.id.profiepic)
        rollNO = findViewById(R.id.rollNO)
        moName = findViewById(R.id.moName)
        other = findViewById(R.id.other)

        apiResponse()
    }

     private fun apiResponse() {
        val url = "http://dlapi.ambiguousit.com/studentProfileDetails.php";

        val sharedPreferences = getSharedPreferences("kotlinsharedpreference", MODE_PRIVATE)
        val classID = sharedPreferences.getString("classID", "")

        val stringRequest = object : StringRequest(
                Request.Method.POST, url,
                com.android.volley.Response.Listener<String> { response ->
        try {
            progressSDialog.dismiss()

            val obj = JSONObject(response)
            var jsonObject = obj.getJSONObject("result")

            address?.setText(jsonObject.getString("address"))
            Picasso.get().load(jsonObject.getString("imgLink")).fit().into(profiepic)
            name?.setText(jsonObject.getString("name"))
            mobile?.setText(jsonObject.getString("contactNumber"))
            rollno?.setText(jsonObject.getString("rollNumber"))
            if (jsonObject.getString("status").equals("0")){
                status?.setText("Inactive")
            }else{
                status?.setText("Active")
            }
            fname?.setText(jsonObject.getString("fatherName"))
            mothername?.setText(jsonObject.getString("motherName"))
            bllodgroup?.setText(jsonObject.getString("bloodGroup"))
            emerno?.setText(jsonObject.getString("emergencyContactNumber"))


        } catch (e: JSONException) {
            e.printStackTrace()
        }
                },
        object : com.android.volley.Response.ErrorListener {
            override fun onErrorResponse(volleyError: VolleyError) {
                Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show()
            }
        }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params.put("request", "getProfileDetails")
                if (classID != null) {
                    params.put("uID", "S359")
                }
                return params
            }
        }

        requestQueue = Volley.newRequestQueue(this)
        requestQueue?.add(stringRequest)


    }
}