package com.example.rhythmiq.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rhythmiq.Adapter.ListSongAdapter;
import com.example.rhythmiq.Model.Song;
import com.example.rhythmiq.R;
import com.example.rhythmiq.Service.APIService;
import com.example.rhythmiq.Service.DataService;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_TimKiem extends Fragment {
    ImageView buttonSearch ;
    TextInputEditText key ;
    List<Song> songList = new ArrayList<>();
    RecyclerView recyclerView ;
    View view ;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_timkiem , container , false) ;
        buttonSearch = view.findViewById(R.id.search_button) ;
        key = view.findViewById(R.id.key) ;
        recyclerView = view.findViewById(R.id.searchlist) ;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL , false) ;
        recyclerView.setLayoutManager(linearLayoutManager);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s= String.valueOf(key.getText()) ;
                if(s != null){
                    GetData(s);
                    Toast.makeText(view.getContext() , s , Toast.LENGTH_LONG).show();
                }

            }
        });

        return view ;

    }

    private void GetData(String string){
        DataService dataService = APIService.getService() ;
        Call<List<Song>> callback = dataService.GetlistSongOfSearch(string) ;
        callback.enqueue(new retrofit2.Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songList = response.body() ;
                ListSongAdapter listSongAdapter = new ListSongAdapter() ;
                listSongAdapter.setData(songList) ;
                recyclerView.setAdapter(listSongAdapter);
                Log.d("CCCCC" , "Done") ;
            }
            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                Log.d("CCCCC" , "Fall") ;

            }
        });
    }

}
