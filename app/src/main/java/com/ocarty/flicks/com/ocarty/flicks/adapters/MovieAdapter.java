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

    private static class ViewHolder{
        TextView tvTitle;
        TextView tvOverview;
        ProgressBar progressBar;
        ImageView ivImage;
    }
    public MovieAdapter(Context context, List<Movie> movies) {
        super(context, R.layout.item_movie, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);
        ViewHolder viewHolder;
        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
            viewHolder.ivImage = (ImageView)convertView.findViewById(R.id.ivMovieImage);
            viewHolder.tvTitle =  (TextView)convertView.findViewById(R.id.tvTitle);
            viewHolder.tvOverview = (TextView)convertView.findViewById(R.id.tvOverview);
            viewHolder.progressBar = (ProgressBar)convertView.findViewById(R.id.progressBar);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.ivImage.setImageResource(0);
        viewHolder.tvTitle.setText(movie.getOriginalTitle());
        viewHolder.tvOverview.setText(movie.getOverview());
        viewHolder.progressBar.setVisibility(View.VISIBLE);
        int orientation = getContext().getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT) {
            showPicassoView(viewHolder.ivImage, viewHolder.progressBar, movie.getPosterPath());
        }
        else if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
            showPicassoView(viewHolder.ivImage, viewHolder.progressBar, movie.getBackdropPath());
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
