package com.example.rhythmiq.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rhythmiq.Adapter.ListSongAdapter;
import com.example.rhythmiq.Model.Song;
import com.example.rhythmiq.R;
import com.example.rhythmiq.Service.APIService;
import com.example.rhythmiq.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ThuVien extends Fragment {
    View view ;
    List<Song> songList = new ArrayList<>();
    RecyclerView recyclerView ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thuvien , container , false );
        recyclerView = view.findViewById(R.id.lovelist) ;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL , false) ;
        recyclerView.setLayoutManager(linearLayoutManager);
        GetData() ;
        return view ;
    }

    private void GetData(){
        DataService dataService = APIService.getService() ;
        Call<List<Song>> callback = dataService.GetLoveSong() ;
        callback.enqueue(new retrofit2.Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songList = response.body() ;
                ListSongAdapter listSongAdapter = new ListSongAdapter() ;
                listSongAdapter.setData(songList) ;
                recyclerView.setAdapter(listSongAdapter);
                Log.d("EEE" , "Done") ;
            }
            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                Log.d("EEE" , "Fall") ;

            }
        });
    }
}
