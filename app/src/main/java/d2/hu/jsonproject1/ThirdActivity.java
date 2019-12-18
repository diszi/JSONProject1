package d2.hu.jsonproject1;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ThirdActivity extends AppCompatActivity {


    private String inline="";
    private JSONObject object = null;
    private JSONArray countries_array = null;
    private List<String> countries=new ArrayList<>();
    private MainAdapter adapter;

    @BindView(R.id.rec)
    RecyclerView recyclerView_rec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        ButterKnife.bind(this);




        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            URL url = new URL("https://api.themoviedb.org/3/movie/550?api_key=d243e02fe7382c503c90edea8d0dbc21");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode==200){
                Scanner sc = new Scanner(url.openStream());
                while(sc.hasNext())
                {
                     inline+=sc.nextLine();
                }

                System.out.println(inline);
                sc.close();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            object = new JSONObject(inline);
            countries_array = object.getJSONArray("production_countries");
            for(int i=0;i<countries_array.length();i++){

                JSONObject item = countries_array.getJSONObject(i);
                countries.add(item.getString("name"));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.setupRecyclerView();
    }


    public void setupRecyclerView() {
        Context context = getApplicationContext();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        this.adapter = new MainAdapter(countries);
        this.recyclerView_rec.setLayoutManager(layoutManager);
        this.recyclerView_rec.setAdapter(this.adapter);
    }

}
