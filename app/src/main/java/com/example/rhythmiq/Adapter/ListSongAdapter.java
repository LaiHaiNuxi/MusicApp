package com.example.rhythmiq.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rhythmiq.Activity.MainActivity;
import com.example.rhythmiq.Model.Song;
import com.example.rhythmiq.R;
import com.example.rhythmiq.Service.APIService;
import com.example.rhythmiq.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSongAdapter extends RecyclerView.Adapter<ListSongAdapter.ItemViewHolder> {
    private List<Song> songList ;
    private View view ;
    ViewGroup viewGroup ;
    private Context context ;

    public void setData(List<Song> lst ){
        this.songList = lst ;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_song , parent , false) ;
        context = view.getContext() ;
        return new ItemViewHolder(view );
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Song song = songList.get(position) ;
        if(song == null ) return ;
        holder.nameSong.setText(song.getNameSong());
        holder.singer.setText(song.getSinger());
        Picasso.with(context).load(song.getImageSong()).into(holder.imageView);
        int i = Integer.valueOf(song.getLikes()) ;

        if(i == 1 ) holder.isLove.setChecked(true);
        else if(i == 0 ) holder.isLove.setChecked(false);

        holder.isLove.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                DataService dataService = APIService.getService() ;
                Call<String> callback = dataService.UpdateLikes(songList.get(position).getIdSong()) ;
                callback.enqueue(new retrofit2.Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String s = response.body() ;
                       Log.d("DDDDD" , "Done") ;
                       Toast.makeText(view.getContext() , s , Toast.LENGTH_LONG).show() ;
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("DDDDD" , "fall") ;

                    }
                });
            }
        });


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.Play(songList , position);
                Toast.makeText(context , song.getNameSong() , Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(songList == null) return 0 ;
        return songList.size() ;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView ;
        TextView nameSong ;
        TextView singer ;
        CheckBox isLove ;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSong) ;
            nameSong  = itemView.findViewById(R.id.nameSong) ;
            singer    = itemView.findViewById(R.id.singer) ;
            isLove    = itemView.findViewById(R.id.checkLove) ;
        }
    }
}
