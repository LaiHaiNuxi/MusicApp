package com.example.rhythmiq.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rhythmiq.Activity.MainActivity;
import com.example.rhythmiq.Adapter.ListSongAdapter;
import com.example.rhythmiq.Model.Album;
import com.example.rhythmiq.Model.Playlistfortoday;
import com.example.rhythmiq.Model.Song;
import com.example.rhythmiq.Model.Type;
import com.example.rhythmiq.R;
import com.example.rhythmiq.Service.APIService;
import com.example.rhythmiq.Service.DataService;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class Fragment_List_Song extends Fragment {
    CoordinatorLayout coordinatorLayout ;
    CollapsingToolbarLayout collapsingToolbarLayout ;
    RecyclerView recyclerView ;
    Toolbar toolbar ;
    Button playbutton ;
    TextView name ;
    ImageView image ;
    List<Song> songList = new ArrayList<>() ;
    View view ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_list_song , container , false ) ;
        Mapping();
        Init();
        DataBundle();
        return view ;
    }
    private void Mapping() {
        collapsingToolbarLayout = view.findViewById(R.id.collapsing) ;
        coordinatorLayout       = view.findViewById(R.id.coordinator) ;
        recyclerView            = view.findViewById(R.id.rcvList) ;
        playbutton              = view.findViewById(R.id.playbutton) ;
        image                   = view.findViewById(R.id.imageofList) ;
        name                    = view.findViewById(R.id.textofList) ;
        toolbar                 = view.findViewById(R.id.toolBarOfList) ;
    }
    private void Init(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL , false) ;
        recyclerView.setLayoutManager(linearLayoutManager);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationIcon(R.drawable.iconback);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getFragmentManager() != null){
                    getFragmentManager().popBackStack();
                }
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.BLACK);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextSize(85);

    }
    private void DataBundle() {
        Bundle bundle = getArguments() ;
        if(bundle != null){
            Playlistfortoday playlistfortoday = (Playlistfortoday) bundle.getSerializable("PlayList");
            Type type = (Type) bundle.getSerializable("Type");
            Album album = (Album) bundle.getSerializable("Album") ;


            if(playlistfortoday != null && !playlistfortoday.getNamePlayList().isEmpty() ){
                Picasso.with(getContext()).load(playlistfortoday.getImageIcon()).into(image);
                name.setText(playlistfortoday.getNamePlayList());
                setbackground(playlistfortoday.getImageIcon() , collapsingToolbarLayout);
                collapsingToolbarLayout.setTitle(playlistfortoday.getNamePlayList());
                GetDataPlayList(playlistfortoday.getIdPlayList()) ;
            }else if(type != null && !type.getNameType().isEmpty()){
                Picasso.with(getContext()).load(type.getImageType()).into(image);
                name.setText(type.getNameType());
                setbackground(type.getImageType() , collapsingToolbarLayout);
                collapsingToolbarLayout.setTitle(type.getNameType());
                GetDataType(type.getIdType()) ;
            }else if(album != null  && !album.getNameAlbum().isEmpty()){
                Picasso.with(getContext()).load(album.getImageAlbum()).into(image);
                name.setText(album.getNameAlbum());
                setbackground(album.getImageAlbum() , collapsingToolbarLayout);
                collapsingToolbarLayout.setTitle(album.getNameAlbum());
                GetDataAlbum(album.getIdAlbum()) ;
            }
        }
    }

    void setbackground(String path , CollapsingToolbarLayout abc){
        ImageView img = new ImageView(getContext()) ;
        Picasso.with(getContext()).load(path).into(img, new Callback() {
            @Override
            public void onSuccess() {
                abc.setBackground(img.getDrawable());
            }
            @Override
            public void onError() {

            }
        });
    }

    private void GetDataPlayList(String idPlayList) {
        DataService dataService = APIService.getService() ;
        Call<List<Song>> callback = dataService.GetlistSongOfPlayList(idPlayList);
        callback.enqueue(new retrofit2.Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songList = response.body() ;
                ListSongAdapter listSongAdapter = new ListSongAdapter() ;
                listSongAdapter.setData(songList) ;
                recyclerView.setAdapter(listSongAdapter);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });

    }
    private void GetDataType(String idType) {
        DataService dataService = APIService.getService() ;
        Call<List<Song>> callback = dataService.GetlistSongOfType(idType) ;
        callback.enqueue(new retrofit2.Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songList = response.body() ;
                ListSongAdapter listSongAdapter = new ListSongAdapter() ;
                listSongAdapter.setData(songList) ;
                recyclerView.setAdapter(listSongAdapter);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void GetDataAlbum(String idAlbum){
        DataService dataService = APIService.getService() ;
        Call<List<Song>> callback = dataService.GetlistSongOfAlbum(idAlbum) ;
        callback.enqueue(new retrofit2.Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songList = response.body() ;
                ListSongAdapter listSongAdapter = new ListSongAdapter() ;
                listSongAdapter.setData(songList) ;
                recyclerView.setAdapter(listSongAdapter);
            }
            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }
}
