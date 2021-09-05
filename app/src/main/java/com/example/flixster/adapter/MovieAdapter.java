package com.example.flixster.adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import java.util.List;

//After define the ViewHolder, we need to further extends the MovieAdapter from RecycleView.Adapter,
//and parameterize with MovieAdapter.ViewHolder
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    //key data we need
    //1) where the adapter is contructed from
    Context context;
    List<Movie> movies;


    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    //The viewHolder is the representation of the row of our recyclerView
    //Usually involves inflating a layout from XML and returning the holder
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter", "onCreateViewHolder");
        //use the static method from() in LayoutInflater class, Obtains the LayoutInflater from the given context.
        //inflate(int resource, ViewGroup root, boolean attachToRoot)
        //Inflate a new view hierarchy from the specified xml resource.
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    //Involve populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter", "onBindViewHolder" + position);
        //Get the movie from the passed position
        Movie movie = movies.get(position);
        holder.bind(movie);
        //Bind the movie data into the ViewHolder
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

      TextView tvTitle;
      TextView tvOverview;
      ImageView ivPoster;

      public ViewHolder(@NonNull View itemView) {
          super(itemView);
          tvTitle = itemView.findViewById(R.id.tvTitle);
          tvOverview = itemView.findViewById(R.id.tvOverview);
          ivPoster = itemView.findViewById(R.id.ivPoster);
      }

        public void bind(Movie movie) {
          tvTitle.setText(movie.getTitle());
          tvOverview.setText(movie.getOverview());
          String imageUrl;
          //If phone is in landscape, then imageUrl  = backdrop image
           if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
               imageUrl = movie.getBackdropPath();
           }else{
               imageUrl = movie.getPosterPath();
           }
          //else imageUrl = poster image
          Glide.with(context).load(movie.getPosterPath()).into(ivPoster);
        }
    }
}
