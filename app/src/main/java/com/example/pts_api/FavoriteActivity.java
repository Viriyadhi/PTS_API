package com.example.pts_api;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class FavoriteActivity extends AppCompatActivity {
    private final int REQUEST_CODE = 101;
    private MovieAdapter adapter;
    private List<MovieModel> arrayList;
    RecyclerView recyclerView;

    private Realm realm;
    private RealmHelper realmHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        recyclerView = findViewById(R.id.rv_fav);

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .allowWritesOnUiThread(true)
                .build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        addData();

    }

    private void addData() {
        arrayList = new ArrayList<>();
        arrayList = realmHelper.getAllMovie();

        adapter = new MovieAdapter(this, arrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(new FavoriteActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

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
                intent.putExtra("id", arrayList.get(position).getId());
                intent.putExtra("isFavorite", true);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            adapter.notifyDataSetChanged();
        }
    }
}
