package com.dicoding.associate.projectcataloguemovie1;


import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieItems>>  {

    ListView listView ;
    MovieAdapter adapter;
    EditText editMovie;
    Button buttonCari;

    static final String EXTRAS_MOVIE = "EXTRAS_MOVIE";
    private static final String position = "position";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MovieAdapter(this);
        adapter.notifyDataSetChanged();
        listView = (ListView)findViewById(R.id.listView);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovieItems item = (MovieItems)parent.getItemAtPosition(position);

                Log.d("Mov", "onItemClick");

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);

                intent.putExtra(DetailActivity.EXTRA_TITLE, item.getCurrentMovie());
                intent.putExtra(DetailActivity.EXTRA_OVERVIEW, item.getDescription());
                intent.putExtra(DetailActivity.EXTRA_RELEASE_DATE, item.getReleaseDate());
                intent.putExtra(DetailActivity.EXTRA_POSTER_JPG, item.getImgPoster());

                startActivity(intent);
            }
        });

        editMovie = (EditText)findViewById(R.id.edit_movie);
        buttonCari = (Button)findViewById(R.id.btn_movie);

        buttonCari.setOnClickListener(myListener);

        String movie = editMovie.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_MOVIE,movie);

        getLoaderManager().initLoader(0, bundle, this);


    }

    //Fungsi ini yang akan menjalankan proses myasynctaskloader
    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int id, Bundle args) {

        String kumpulanMovie = "";
        if (args != null){
            kumpulanMovie = args.getString(EXTRAS_MOVIE);
        }

        return new MyAsyncTaskLoader(this,kumpulanMovie);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> data) {

        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MovieItems>> loader) {
        adapter.setData(null);

    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String movie = editMovie.getText().toString();

            if (TextUtils.isEmpty(movie))return;

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_MOVIE,movie);
            getLoaderManager().restartLoader(0,bundle,MainActivity.this);
        }
    };
}
