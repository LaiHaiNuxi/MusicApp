package com.example.rhythmiq.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rhythmiq.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.mancj.slideup.SlideUp;
import com.mancj.slideup.SlideUpBuilder;
import com.squareup.picasso.Picasso;

public class Fragment_Play extends Fragment {
    ImageView imageView ;
    SlideUp slideUp ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.play , container , false ) ;
        imageView = view.findViewById(R.id.image) ;
        slideUp = new SlideUpBuilder(view).withStartState(SlideUp.State.HIDDEN).withStartGravity(Gravity.BOTTOM).build() ;
        return view;
    }
    public void SetData(String path){
        Picasso.with(getContext()).load(path).into(imageView);
        slideUp.show();
    }
}
