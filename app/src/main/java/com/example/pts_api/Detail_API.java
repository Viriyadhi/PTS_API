package com.example.pts_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Detail_API extends AppCompatActivity {
    private Realm realm;
    private RealmHelper realmHelper;
    private String language,title,overview,date,vote,posterpath;
    private Integer id;
    private boolean isFavorite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tv_language,tv_title,tv_overview,tv_date,tv_vote;
        ImageView poster,star;

        Realm.init(Detail_API.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        tv_language = findViewById(R.id.language_detail);
        tv_title = findViewById(R.id.title_detail);
        tv_overview = findViewById(R.id.overview_detail);
        tv_date = findViewById(R.id.release_date_detail);
        tv_vote = findViewById(R.id.vote_average_detail);
        poster = findViewById(R.id.poster);
        star = findViewById(R.id.star);
        Bundle bundle = getIntent().getExtras();
        System.out.println(bundle);
        if (bundle != null) {
             language= bundle.getString("original_language");
             title = bundle.getString("original_title");
             overview = bundle.getString("overview");
             date = bundle.getString("release_date");
             vote = bundle.getString("vote_average");
             posterpath = bundle.getString("poster_path");
             id = bundle.getInt("id");
             isFavorite = bundle.getBoolean("isFavorite");

            if (isFavorite == true && id != 0) {
                star.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_baseline_star_24));
            }

            tv_language.setText(language);
            tv_title.setText(title);
            tv_overview.setText(overview);
            tv_date.setText(date);
            tv_vote.setText(vote);
            Picasso.get()
                    .load(posterpath)
                    .into(poster);
        }

        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFavorite == true && id != 0) {
                    star.setBackground(ContextCompat.getDrawable(Detail_API.this, R.drawable.ic_baseline_star_border_24));
                    realmHelper.delete(id);
                    onBackPressed();
                } else {
                    star.setBackground(ContextCompat.getDrawable(Detail_API.this, R.drawable.ic_baseline_star_24));

                    MovieModel model = new MovieModel(language,title,overview,date,posterpath,vote);
                    realmHelper.save(model);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }
}