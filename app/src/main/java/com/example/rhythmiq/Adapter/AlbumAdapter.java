package com.example.rhythmiq.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rhythmiq.Fragment.Fragment_List_Song;
import com.example.rhythmiq.Model.Album;
import com.example.rhythmiq.Model.Playlistfortoday;
import com.example.rhythmiq.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AlbumAdapter extends  RecyclerView.Adapter<AlbumAdapter.ItemViewHolder> {


    private List<Album> albumList ;
    private View view ;
    private Context context ;
    public void setdata(List<Album> lst){
        this.albumList = lst ;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item , parent , false ) ;
        context = view.getContext() ;
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Album album = albumList.get(position) ;
        if(album == null) return ;
        holder.textView.setText(album.getNameAlbum());
        Picasso.with(context).load(album.getImageAlbum()).into(holder.imageView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_List_Song fragment_list_song = new Fragment_List_Song() ;
                Bundle bundle = new Bundle() ;
                bundle.putSerializable("Album" , album);
                fragment_list_song.setArguments(bundle);

                AppCompatActivity activity = (AppCompatActivity) context;
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainLayout , fragment_list_song).addToBackStack("").commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        if( albumList == null) return 0 ;
        return albumList.size();
    }

    public static class ItemViewHolder extends  RecyclerView.ViewHolder{
        private TextView textView ;
        private ImageView imageView ;
        public ItemViewHolder(@NonNull View itemview) {
            super(itemview);
            textView = itemview.findViewById(R.id.textofitem) ;
            imageView = itemview.findViewById(R.id.imageofitem) ;
        }
    }
}
