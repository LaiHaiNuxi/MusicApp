package com.example.rhythmiq.Fragment;

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

import com.example.rhythmiq.Adapter.TypeAdapter;
import com.example.rhythmiq.Model.Type;
import com.example.rhythmiq.R;
import com.example.rhythmiq.Service.APIService;
import com.example.rhythmiq.Service.DataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Type extends Fragment {
    View view ;
    RecyclerView rcv ;
    TextView text ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_row , container , false) ;
        rcv = view.findViewById(R.id.row_rcv) ;
        text = view.findViewById(R.id.row_txt) ;
        text.setText("Recommend");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext() , RecyclerView.HORIZONTAL , false) ;
        rcv.setLayoutManager(linearLayoutManager);
        GetType();
        return view ;
    }

    public void GetType(){
        DataService dataService = APIService.getService() ;
        Call<List<Type>> callback = dataService.GetDataType() ;
        callback.enqueue(new Callback<List<Type>>() {
            @Override
            public void onResponse(Call<List<Type>> call, Response<List<Type>> response) {
                List<Type> list = response.body() ;
                TypeAdapter typeAdapter = new TypeAdapter() ;
                typeAdapter.setdata(list);
                rcv.setAdapter(typeAdapter);
            }

            @Override
            public void onFailure(Call<List<Type>> call, Throwable t) {

            }
        });
    }
}
























