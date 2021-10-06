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
import com.example.lmsstudentkotlin.Adapter.NotesAdapter;
import com.example.lmsstudentkotlin.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Notes extends AppCompatActivity {
    String subID, subSelectname;
    List<String> ids = new ArrayList<>();
    List<String> notesName = new ArrayList<>();
    List<String> notesLink = new ArrayList<>();
    List<String> notesDesc = new ArrayList<>();
    RecyclerView recyNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        subID = getIntent().getStringExtra("ids");
        recyNotes = findViewById(R.id.recyNotes);
        subSelectname = getIntent().getStringExtra("name");


        apiResponse();
    }

    private void apiResponse() {

        String url = "http://dlapi.ambiguousit.com/studentShortNotes.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONObject jsonObject1 = jsonObject.getJSONObject("result");
                JSONArray jsonArray = jsonObject1.getJSONArray("shortNotesData");
                for (int i = 0; i < jsonArray.length() ; i++) {
                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                    ids.add(jsonObject2.getString("id"));
                    notesName.add(jsonObject2.getString("noteName"));
                    notesDesc.add(jsonObject2.getString("noteDescription"));
                    notesLink.add(jsonObject2.getString("noteLink"));
                }

                NotesAdapter notesAdapter = new NotesAdapter(this, ids, notesName, notesDesc, notesLink, subSelectname);
                recyNotes.setHasFixedSize(true);
                recyNotes.setLayoutManager(new LinearLayoutManager(this));
                recyNotes.setAdapter(notesAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            Toast.makeText(this, ""+ error, Toast.LENGTH_SHORT).show();
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("request", "getShortNotes");
                map.put("subjectID", "78");
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}