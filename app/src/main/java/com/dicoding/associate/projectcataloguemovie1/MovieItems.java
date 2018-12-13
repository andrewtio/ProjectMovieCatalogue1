package com.dicoding.associate.projectcataloguemovie1;

import java.text.DecimalFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;

import org.json.JSONObject;

public class MovieItems {
    private int id;
    private String nama;
    private String currentMovie;
    private String description;
    private String releaseDate;
    private String imgPoster;
    public String IMG_POSTER = getImgPoster();

    @RequiresApi(api = Build.VERSION_CODES.N)
    public MovieItems(JSONObject object){
        try {
            int id = object.getInt("id");
//            String name = object.getString("name");
            String currentMovie = object.getString("title");
            String description = object.getString("overview");
            String releaseDate = object.getString("release_date");
            String imgPoster = object.getString("poster_path");
//            double tempInKelvin = object.getJSONObject("main").getDouble("temp");
//
//            double tempInCelcius = tempInKelvin - 273;
//            String temperature = new DecimalFormat("##.##").format(tempInCelcius);
            this.id = id;
//            this.nama = name;
            this.currentMovie = currentMovie;
            this.description = description;
            this.releaseDate = releaseDate;
            this.imgPoster = "http://image.tmdb.org/t/p/w92" + imgPoster;

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
//    public String getNama() {
//        return nama;
//    }
//    public void setNama(String nama) {
//        this.nama = nama;
//    }

    public String getCurrentMovie() {
        return currentMovie;
    }

    public void setCurrentMovie(String currentMovie) {
        this.currentMovie = currentMovie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getImgPoster() {
        return imgPoster;
    }

    public void setImgPoster(String imgPoster) {
        this.imgPoster = imgPoster;
    }
}