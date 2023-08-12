package com.example.rhythmiq.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rhythmiq.Adapter.AlbumAdapter;
import com.example.rhythmiq.Model.Album;
import com.example.rhythmiq.R;
import com.example.rhythmiq.Service.APIService;
import com.example.rhythmiq.Service.DataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Album extends Fragment {
    View view ;
    RecyclerView rcv ;
    TextView textView ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_row , container , false ) ;
        rcv = view.findViewById(R.id.row_rcv) ;
        textView = view.findViewById(R.id.row_txt) ;
        textView.setText("Album");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext() , RecyclerView.HORIZONTAL , false) ;
        rcv.setLayoutManager(linearLayoutManager);
        GetAlbum();
        return view;
    }

    public void GetAlbum(){
        DataService dataService = APIService.getService();
        Call<List<Album>> callback = dataService.GetDataAlbum() ;
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                List<Album> albumList = response.body() ;
                AlbumAdapter albumAdapter = new AlbumAdapter() ;
                albumAdapter.setdata(albumList) ;
                rcv.setAdapter(albumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

}
