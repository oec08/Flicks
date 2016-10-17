package com.ocarty.flicks.com.ocarty.flicks.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ocarty.flicks.R;
import com.ocarty.flicks.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends ArrayAdapter<Movie> {
    public MovieAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
           convertView = inflater.inflate(R.layout.item_movie, parent, false);
        }

        ImageView ivImage = (ImageView)convertView.findViewById(R.id.ivMovieImage);

        ivImage.setImageResource(0);
        TextView tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);
        TextView tvOverview = (TextView)convertView.findViewById(R.id.tvOverview);
        final ProgressBar progressBar = (ProgressBar)convertView.findViewById(R.id.progressBar);

        tvTitle.setText(movie.getOriginalTitle());
        tvOverview.setText(movie.getOverview());
        progressBar.setVisibility(View.VISIBLE);
        int orientation = getContext().getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT) {
            showPicassoView(ivImage, progressBar, movie.getPosterPath());
        }
        else if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
            showPicassoView(ivImage, progressBar, movie.getBackdropPath());
        }
        return convertView;
    }

    private void showPicassoView(ImageView imageToLoad, final ProgressBar progressBar, String pathToLoad) {
        Picasso.with(getContext()).load(pathToLoad)
                .into(imageToLoad, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        if (progressBar != null) {
                            progressBar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });

    }
}
