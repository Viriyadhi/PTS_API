package com.example.pts_api;

import android.os.Debug;
import android.util.Log;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import java.io.Serializable;
import io.realm.Realm;
import io.realm.RealmObject;

public class MovieModel extends RealmObject {
    @PrimaryKey
    private Integer id;
    private String original_language, original_title, overview, release_date, poster_path, vote_average;

    public MovieModel(String original_language, String original_title, String overview, String release_date, String poster_path, String vote_average) {
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.poster_path = "https://image.tmdb.org/t/p/w200"+poster_path;
        this.release_date = release_date;
        this.vote_average = vote_average;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MovieModel() {

    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

}








