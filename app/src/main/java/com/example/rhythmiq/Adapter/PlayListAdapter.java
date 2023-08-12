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
import com.example.rhythmiq.Model.Playlistfortoday;
import com.example.rhythmiq.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlayListAdapter extends  RecyclerView.Adapter<PlayListAdapter.ItemViewHolder> {


    private List<Playlistfortoday> playlist ;
    private View view ;
    private Context context ;
    public void setdata(List<Playlistfortoday> lst){
        this.playlist = lst ;
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
        Playlistfortoday plist = playlist.get(position) ;
        if(plist == null) return ;
        holder.textView.setText(plist.getNamePlayList());
        Picasso.with(context).load(plist.getImageIcon()).into(holder.imageView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_List_Song fragment_list_song = new Fragment_List_Song() ;
                Bundle bundle = new Bundle() ;
                bundle.putSerializable("PlayList" , plist);
                fragment_list_song.setArguments(bundle);

                AppCompatActivity activity = (AppCompatActivity) context;
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainLayout , fragment_list_song).addToBackStack("").commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        if( playlist == null) return 0 ;
        return playlist.size();
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
