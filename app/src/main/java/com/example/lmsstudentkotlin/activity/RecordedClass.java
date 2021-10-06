package com.example.lmsstudentkotlin.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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

public class RecordedClass extends AppCompatActivity {
    Spinner spinnerAubN;
//    private var requestQueue: RequestQueue? = null

    Button buttonCLick;
    List<String> ids = new ArrayList<>();
    List<String> subName = new ArrayList<>();
    String subSelectId, subSelectName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorded_class);

        spinnerAubN = findViewById(R.id.spinnerAubRe);
        buttonCLick = findViewById(R.id.buRecorded);

        ids.add("0");
        subName.add("Select");
        subjectAdapter();

        apiResponse();

        buttonCLick.setOnClickListener(v -> {
            if(subSelectId!="0"){
                Intent intent = new Intent(RecordedClass.this, Recorded.class);
                intent.putExtra("ids",subSelectId);
                intent.putExtra("name", subSelectName);
                startActivity(intent);
            }else {
                Toast.makeText(this, "Please select the above details", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void apiResponse() {

        String url = "http://dlapi.ambiguousit.com/studentSubjectList.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONObject jsonObject1 = jsonObject.getJSONObject("result");
                JSONArray jsonArray = jsonObject1.getJSONArray("subjectList");
                ids.clear();
                subName.clear();
                ids.add("0");
                subName.add("Select");

                for (int i = 0; i < jsonArray.length() ; i++) {
                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                    ids.add(jsonObject2.getString("id"));
                    subName.add(jsonObject2.getString("subjectName"));
                }

                subjectAdapter();


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
                map.put("request","getSubjectList");
                map.put("classID","43");
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void subjectAdapter() {
//        spinnerSub = findViewById(R.id.spinnerSub);
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(RecordedClass.this, R.layout.spinner_layout_background, subName);

        // Drop down layout style - list view with radio button
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerAubN.setAdapter(dataAdapter1);


        spinnerAubN.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if(position!=0){

                    subSelectId = ids.get(position);
                    subSelectName = subName.get(position);

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
//                checkSum = "not selected";
            }
        });
    }

}