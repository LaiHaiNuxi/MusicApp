package com.example.rhythmiq.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rhythmiq.Activity.MainActivity;
import com.example.rhythmiq.Adapter.PlayListAdapter;
import com.example.rhythmiq.Model.Playlistfortoday;
import com.example.rhythmiq.R;
import com.example.rhythmiq.Service.APIService;
import com.example.rhythmiq.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_PlayList extends Fragment {
    View view ;
    RecyclerView rcv ;
    TextView text ;
    Context context ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_row, container , false ) ;
        rcv = view.findViewById(R.id.row_rcv) ;
        text = view.findViewById(R.id.row_txt) ;
        text.setText("PlayList");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext() , RecyclerView.HORIZONTAL , false ) ;
        rcv.setLayoutManager(linearLayoutManager);
        Getplaylist();
        return view ;
    }
    private void Getplaylist(){
        DataService dataService = APIService.getService() ;
        Call<List<Playlistfortoday>> callback = dataService.GetDataPlaylist() ;
        callback.enqueue(new Callback<List<Playlistfortoday>>() {
            @Override
            public void onResponse(Call<List<Playlistfortoday>> call, Response<List<Playlistfortoday>> response) {
                List<Playlistfortoday> playlistfortodays = response.body() ;
                PlayListAdapter playListAdapter = new PlayListAdapter() ;
                playListAdapter.setdata(playlistfortodays);
                rcv.setAdapter(playListAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlistfortoday>> call, Throwable t) {
                Log.d("BBBBB" , "false") ;
            }
        });
    }

}
