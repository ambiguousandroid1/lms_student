package com.example.lmsstudentkotlin.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lmsstudentkotlin.Adapter.AdapterSubjects;
import com.example.lmsstudentkotlin.R;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Subjects extends AppCompatActivity {

    RecyclerView recyclerSubjects;
    final List<String> ids = new ArrayList<>();
    final List<String> subjectName = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);

            recyclerSubjects = findViewById(R.id.recyclerSubjects);

            apiResponse();
    }

    private void apiResponse() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("kotlinsharedpreference", Context.MODE_PRIVATE);
        String classID = sharedPreferences.getString("classID", "");

        String url = "http://dlapi.ambiguousit.com/studentSubjectList.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONObject jsonObject1 = jsonObject.getJSONObject("result");
                JSONArray jsonArray = jsonObject1.getJSONArray("subjectList");

                for (int i = 0; i <jsonArray.length() ; i++) {
                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                    subjectName.add(jsonObject2.getString("subjectName"));
                    ids.add(jsonObject2.getString("id"));
                }

                AdapterSubjects adapterSubjects = new AdapterSubjects(this, subjectName, ids);
                recyclerSubjects.setHasFixedSize(true);
                recyclerSubjects.setLayoutManager(new LinearLayoutManager(this));
                recyclerSubjects.setAdapter(adapterSubjects);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show()){
            @NotNull
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<>();
                map.put("request", "getSubjectList");
                map.put("classID", classID);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}