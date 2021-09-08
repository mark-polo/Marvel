package com.mrm.marvel;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder>{
    private final List<FilmModel> lists;
    Context context;

    public FilmAdapter(List<FilmModel> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FilmAdapter.FilmViewHolder(LayoutInflater.from(context).inflate(R.layout.marvel_films, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
//        int imageId = context.getResources().getIdentifier(lists.get(position).getPoster(), "drawable", context.getPackageName());
//        holder.filmPoster.setImageResource(imageId);

        Picasso.with(holder.filmPoster.getContext()).load(lists.get(position).getPoster()).into(holder.filmPoster);
        holder.itemView.setOnClickListener(v -> {

            Intent i = new Intent(context, DescActivity.class);

            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation((Activity) context,
                    new Pair<>(holder.filmPoster, "filmPoster"));

            i.putExtra("filmPoster", lists.get(position).getPoster());
            i.putExtra("desc", lists.get(position).getName());

            context.startActivity(i, activityOptions.toBundle());
        });
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    static class FilmViewHolder extends RecyclerView.ViewHolder {

        ImageView filmPoster;

        public FilmViewHolder(@NonNull View itemView) {
            super(itemView);

            filmPoster = itemView.findViewById(R.id.posters);
        }
    }
}