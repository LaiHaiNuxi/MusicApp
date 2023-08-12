package com.example.rhythmiq.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.rhythmiq.Fragment.Fragment_ThuVien;
import com.example.rhythmiq.Fragment.Fragment_TimKiem;
import com.example.rhythmiq.Fragment.Fragment_TrangChu;

import java.util.ArrayList;

public class MainFragmentAdapter extends FragmentStateAdapter {
    public MainFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0 :
                return new Fragment_TrangChu() ;
            case 1 :
                return new Fragment_TimKiem() ;
            default:
                return new Fragment_ThuVien() ;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
