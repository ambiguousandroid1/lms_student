package com.example.lmsstudentkotlin.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lmsstudentkotlin.Adapter.RecordedAdapter;
import com.example.lmsstudentkotlin.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recorded extends AppCompatActivity {
    List<String> id = new ArrayList<>();
    List<String> saTitle = new ArrayList<>();
    List<String> saDescription = new ArrayList<>();
    List<String> subID = new ArrayList<>();
    List<String> link = new ArrayList<>();
    List<String> source  = new ArrayList<>();
    RecyclerView recRec;

    String subIDSelected, subNameSelected ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorded);

        recRec = findViewById(R.id.recRec);

        subIDSelected = getIntent().getStringExtra("ids");
        subNameSelected = getIntent().getStringExtra("name");

        apiResponse();
    }
    private void apiResponse() {

        String url = "http://dlapi.ambiguousit.com/recordedVideoList.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONObject jsonObject1 = jsonObject.getJSONObject("result");
                JSONArray jsonArray = jsonObject1.getJSONArray("recordedVideoList");
                for (int i = 0; i <jsonArray.length() ; i++) {
                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                    id.add(jsonObject2.getString("id"));
//                    aoeID.add(jsonObject2.getString("aoeID"));
                    saTitle.add(jsonObject2.getString("videoTitle"));
                    saDescription.add(jsonObject2.getString("videoDescription"));
                    link.add(jsonObject2.getString("videoLink"));
                    source.add(jsonObject2.getString("source"));
                    subID.add(jsonObject2.getString("subjectID"));
//                    saURL.add(jsonObject2.getString("saURL"));
//                    studentID.add(jsonObject2.getString("studentID"));

                }

                RecordedAdapter recordedAdapter = new RecordedAdapter(this, id, saTitle, saDescription, link, source, subID, subNameSelected);
                recRec.setHasFixedSize(true);
                recRec.setLayoutManager(new LinearLayoutManager(this));
                recRec.setAdapter(recordedAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show();
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("request","getRecordedVideoList");
                map.put("subjectID","78");
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}