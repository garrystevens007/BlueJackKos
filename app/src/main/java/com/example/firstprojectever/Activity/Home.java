package com.example.firstprojectever.Activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.firstprojectever.Adapter.Adapter;
import com.example.firstprojectever.R;
import com.example.firstprojectever.Storage.array_api_datakos;
import com.example.firstprojectever.Storage.data_kos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.firstprojectever.Storage.Storage.aad;
import static com.example.firstprojectever.Storage.Storage.items;

public class Home extends AppCompatActivity implements View.OnClickListener , Adapter.OnImageListener {

    public data_kos dks;
    public array_api_datakos aad;
    private RecyclerView rv;
   // private final String JSON_URL = "https://raw.githubusercontent.com/dnzrx/SLC-REPO/master/MOBI6006/E202-MOBI6006-KR01-00.json";
    private RequestQueue requestQueue;
    private List<array_api_datakos> data;
    private JsonArrayRequest request;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.booking){
            Intent ii  = new Intent(Home.this,booking_transaction_form.class);
            startActivity(ii);
        }else if(item.getItemId() == R.id.logout){
            Intent i = new Intent(Home.this,MainActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        jsonrequest();
        data = new ArrayList<>();
        rv = findViewById(R.id.recyclerView);


    }

    private void jsonrequest() {
        String URL = "https://raw.githubusercontent.com/dnzrx/SLC-REPO/master/MOBI6006/E202-MOBI6006-KR01-00.json";
        requestQueue = Volley.newRequestQueue(this);
        request = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.i("JSON","Number of surveys in feed: "+response.length());

                for(int i = 0 ; i < response.length();i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        aad = new array_api_datakos();
                        aad.setIdkos(jsonObject.getInt("id"));
                        aad.setNamakos(jsonObject.getString("name"));
                        aad.setPrice(jsonObject.getString("price"));
                        aad.setFasilitaskos(jsonObject.getString("facilities"));
                        aad.setImagekos(jsonObject.getString("image"));
                        aad.setAddresskos(jsonObject.getString("address"));
                        aad.setLatkos(jsonObject.getString("LAT"));
                        aad.setLngkos(jsonObject.getString("LNG"));

                        data.add(aad);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                showData(data);
                Log.i("Data array", "Jumlah array aad : " );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue.add(request);

    }

    private void showData(List<array_api_datakos> listData){
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        Adapter mAdapter = new Adapter(this,listData);
        rv.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view) {

    }
    @Override
    public void onImageClick(int position) {
        //data_kos dk = items.get(position);
//        array_api_datakos array = aad.get(position);
//        Intent intent = new Intent(this,detail_form.class);
//        intent.putExtra("array_api_datakos", (Parcelable) array);
//        startActivity(intent);

    }
}
