package com.example.pts_api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class API_Activity extends AppCompatActivity {

    private MovieAdapter adapter;
    private ArrayList<MovieModel> arrayList;
    private RecyclerView recyclerView;
    private MovieModel movieModel;


    void getDataApi() {
        AndroidNetworking.get("https://api.themoviedb.org/3/movie/now_playing?api_key=6ac7a042ac3b7599a689eb943fa0b6d0&language=en-US")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject  response) {
                        try {
                            arrayList = new ArrayList<>();

                            JSONArray result = response.getJSONArray("results");
                            for (int i = 0; i < result.length(); i++) {
                                JSONObject movieobject = result.getJSONObject(i);
                                String language = movieobject.getString("original_language");
                                String title = movieobject.getString("original_title");
                                String overview = movieobject.getString("overview");
                                String date = movieobject.getString("release_date");
                                String poster = movieobject.getString("poster_path");
                                String vote = movieobject.getString("vote_average");
                                movieModel = new MovieModel(language, title, overview, date, poster, vote);
                                arrayList.add(movieModel);
                            }
                            adapter = new MovieAdapter(getApplicationContext(), arrayList);

                            adapter.setOnItemClickListener(new MovieAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    Intent intent = new Intent(getApplicationContext(), Detail_API.class);
                                    intent.putExtra("original_language", arrayList.get(position).getOriginal_language());
                                    intent.putExtra("original_title", arrayList.get(position).getOriginal_title());
                                    intent.putExtra("overview", arrayList.get(position).getOverview());
                                    intent.putExtra("release_date", arrayList.get(position).getRelease_date());
                                    intent.putExtra("poster_path", arrayList.get(position).getPoster_path());
                                    intent.putExtra("vote_average", arrayList.get(position).getVote_average());
                                    intent.putExtra("id", 0);
                                    intent.putExtra("isFavorite", false);
                                    startActivity(intent);
                                }
                            });

                            recyclerView = findViewById(R.id.rv_main);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(API_Activity.this);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            recyclerView.setAdapter(adapter);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();

                            Log.d("error", "onErrors: " + e.toString());
                        }

                        Log.d("hasil response", "onErrors: " + response.toString());
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.d("error", anError.toString());
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_activity);

        getDataApi();


    }
}

