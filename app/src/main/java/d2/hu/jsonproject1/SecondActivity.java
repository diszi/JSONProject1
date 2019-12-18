package d2.hu.jsonproject1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends AppCompatActivity {



    String json_test = "{\n" +
            "    \"adult\": false,\n" +
            "    \"backdrop_path\": \"/mMZRKb3NVo5ZeSPEIaNW9buLWQ0.jpg\",\n" +
            "    \"belongs_to_collection\": null,\n" +
            "    \"budget\": 63000000,\n" +
            "    \"genres\": [\n" +
            "        {\n" +
            "            \"id\": 18,\n" +
            "            \"name\": \"Drama\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"homepage\": \"http://www.foxmovies.com/movies/fight-club\",\n" +
            "    \"id\": 550,\n" +
            "    \"imdb_id\": \"tt0137523\",\n" +
            "    \"original_language\": \"en\",\n" +
            "    \"original_title\": \"Fight Club\",\n" +
            "    \"overview\": \"A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \\\"fight clubs\\\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.\",\n" +
            "    \"popularity\": 32.18,\n" +
            "    \"poster_path\": \"/adw6Lq9FiC9zjYEpOqfq03ituwp.jpg\",\n" +
            "    \"production_companies\": [\n" +
            "        {\n" +
            "            \"id\": 508,\n" +
            "            \"logo_path\": \"/7PzJdsLGlR7oW4J0J5Xcd0pHGRg.png\",\n" +
            "            \"name\": \"Regency Enterprises\",\n" +
            "            \"origin_country\": \"US\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 711,\n" +
            "            \"logo_path\": \"/tEiIH5QesdheJmDAqQwvtN60727.png\",\n" +
            "            \"name\": \"Fox 2000 Pictures\",\n" +
            "            \"origin_country\": \"US\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 20555,\n" +
            "            \"logo_path\": \"/hD8yEGUBlHOcfHYbujp71vD8gZp.png\",\n" +
            "            \"name\": \"Taurus Film\",\n" +
            "            \"origin_country\": \"DE\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 54051,\n" +
            "            \"logo_path\": null,\n" +
            "            \"name\": \"Atman Entertainment\",\n" +
            "            \"origin_country\": \"\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 54052,\n" +
            "            \"logo_path\": null,\n" +
            "            \"name\": \"Knickerbocker Films\",\n" +
            "            \"origin_country\": \"US\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 25,\n" +
            "            \"logo_path\": \"/qZCc1lty5FzX30aOCVRBLzaVmcp.png\",\n" +
            "            \"name\": \"20th Century Fox\",\n" +
            "            \"origin_country\": \"US\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 4700,\n" +
            "            \"logo_path\": \"/A32wmjrs9Psf4zw0uaixF0GXfxq.png\",\n" +
            "            \"name\": \"The Linson Company\",\n" +
            "            \"origin_country\": \"\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"production_countries\": [\n" +
            "        {\n" +
            "            \"iso_3166_1\": \"DE\",\n" +
            "            \"name\": \"Germany\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"iso_3166_1\": \"US\",\n" +
            "            \"name\": \"United States of America\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"release_date\": \"1999-10-15\",\n" +
            "    \"revenue\": 100853753,\n" +
            "    \"runtime\": 139,\n" +
            "    \"spoken_languages\": [\n" +
            "        {\n" +
            "            \"iso_639_1\": \"en\",\n" +
            "            \"name\": \"English\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"status\": \"Released\",\n" +
            "    \"tagline\": \"Mischief. Mayhem. Soap.\",\n" +
            "    \"title\": \"Fight Club\",\n" +
            "    \"video\": false,\n" +
            "    \"vote_average\": 8.4,\n" +
            "    \"vote_count\": 17458\n" +
            "}";

    private List<String> companyNameList = new ArrayList<>();
    private MainAdapter adapter;

    @BindView(R.id.recyclerView_list)
    RecyclerView rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        try {
            JSONObject root = new JSONObject(json_test);
            JSONArray production_companies = root.getJSONArray("production_companies");



            for(int i=0;i<production_companies.length();i++)
            {
                JSONObject company= production_companies.getJSONObject(i);
                companyNameList.add(company.getString("name"));
            }


            this.setupRecyclerView();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void setupRecyclerView() {
        Context context = getApplicationContext();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        this.adapter = new MainAdapter(companyNameList);
        this.rec.setLayoutManager(layoutManager);
        this.rec.setAdapter(this.adapter);
    }

    @OnClick(R.id.button_nextpage)
    public void onClickonButton(){
        Intent intent = new Intent(this,ThirdActivity.class);
        startActivity(intent);
    }

}
