package com.dicoding.associate.projectcataloguemovie1;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DetailActivity extends AppCompatActivity {

    public static String EXTRA_TITLE    = "extra_title";
    public static String EXTRA_OVERVIEW = "extra_overview";
    public static String EXTRA_RELEASE_DATE = "extra_release_date";
    public static String EXTRA_POSTER_JPG = "extra_poster_jpg";

    private TextView tvJudul, tvOverview, tvReleaseDate, tvRating;
    private ImageView imgPoster;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvJudul = (TextView)findViewById(R.id.textMovie);
        tvOverview = (TextView)findViewById(R.id.textDesc);
        tvReleaseDate = (TextView)findViewById(R.id.textDate);
        imgPoster = (ImageView)findViewById(R.id.img_item);

        String title = getIntent().getStringExtra(EXTRA_TITLE);
        String overview = getIntent().getStringExtra(EXTRA_OVERVIEW);
        String poster_jpg = getIntent().getStringExtra(EXTRA_POSTER_JPG);
        String release_date = getIntent().getStringExtra(EXTRA_RELEASE_DATE);
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date date = date_format.parse(release_date);

            SimpleDateFormat new_date_format = new SimpleDateFormat("EEEE, dd/MM/yyyy");
            String date_of_release = new_date_format.format(date);
            tvReleaseDate.setText(date_of_release);
        }catch (ParseException e){
            e.printStackTrace();
        }

        tvJudul.setText(title);
        tvOverview.setText(overview);
        Glide
                .with(DetailActivity.this)
                .load(poster_jpg)
                .into(imgPoster);
    }
}
