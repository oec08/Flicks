package com.ocarty.flicks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MovieDetailsActivity extends AppCompatActivity {
    RatingBar ratingBar;
    TextView tvMovieDetails;
    TextView tvOriginalTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        tvMovieDetails = (TextView)findViewById(R.id.tvMovieOverviewDetails);
        tvOriginalTitle = (TextView)findViewById(R.id.tvMovieTitleDetails);

        String voteOverview = getIntent().getStringExtra("voteOverview");
        String movieDetails = getIntent().getStringExtra("movieDetails");
        String originalTitle = getIntent().getStringExtra("originalTitle");

        tvMovieDetails.setText(movieDetails);
        tvOriginalTitle.setText(originalTitle);
        if(voteOverview != null) {
            ratingBar.setRating(Float.parseFloat(voteOverview));
        }



    }
}
