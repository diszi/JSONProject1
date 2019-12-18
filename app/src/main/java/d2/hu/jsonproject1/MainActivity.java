package d2.hu.jsonproject1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import android.widget.Button;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


//    @BindView(R.id.recyclerView_list)
//    RecyclerView recyclerView_ad;
    @BindView(R.id.button_next)
    Button next_page;

    private MainAdapter adapter;

    String json_string = "{\n" +
            "  \"title\":\"JSONParserTutorial\",\n" +
            "  \"array\":[\n" +
            "    {\n" +
            "    \"company\":\"Google\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"company\":\"Facebook\"\n" +
            "    },\n" +
            "    {\n" +
            "    \"company\":\"LinkedIn\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"company\" : \"Microsoft\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"company\": \"Apple\"\n" +
            "    }\n" +
            "    ],\n" +
            "    \"nested\":{\n" +
            "    \"flag\": true,\n" +
            "    \"random_number\":1\n" +
            "    }\n" +
            "}";

    List<String> items = new ArrayList<>();
    RecyclerView rec=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        rec = findViewById(R.id.recyclerView_list);



        try {



            JSONObject root = new JSONObject(json_string);

            JSONArray array= root.getJSONArray("array");



            for(int i=0;i<array.length();i++)
            {
                JSONObject object= array.getJSONObject(i);
                items.add(object.getString("company"));
            }



            JSONObject nested= root.getJSONObject("nested");
            Log.d("TAG","flag value "+nested.getBoolean("flag"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.setupRecyclerView();

    }


    public void setupRecyclerView() {
        Context context = getApplicationContext();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        this.adapter = new MainAdapter(items);
        this.rec.setLayoutManager(layoutManager);
        this.rec.setAdapter(this.adapter);
    }

//    private void setArray(List<String> array){
//        adapter.setArrayList(array);
//    }

    @OnClick(R.id.button_next)
    public void onClickNextButton(){
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }
}
