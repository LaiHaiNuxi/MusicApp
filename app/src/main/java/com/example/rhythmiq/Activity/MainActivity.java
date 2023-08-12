package com.example.rhythmiq.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rhythmiq.Adapter.MainFragmentAdapter;
import com.example.rhythmiq.Fragment.Fragment_Play;
import com.example.rhythmiq.Model.Song;
import com.example.rhythmiq.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.mancj.slideup.SlideUp;
import com.mancj.slideup.SlideUpBuilder;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.lang.reflect.TypeVariable;
import java.text.SimpleDateFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout  ;
    ViewPager2 viewPager;
    static SlideUp slideUp ;
    static LinearLayout bottomSheet ;
    static BottomSheetBehavior bottomSheetBehavior ;
    static TextView textView , textView1 , maxTime , time  ;
    static ImageView imageView, playButton , nextButton , preButton  ;
    static SeekBar timeBar ;


    static boolean next = false , isPlay = false ;
    static  MediaPlayer mp3 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Mapping();

        MainFragmentAdapter mainFragmentAdapter = new MainFragmentAdapter(this) ;
        viewPager.setAdapter(mainFragmentAdapter);
        viewPager.setUserInputEnabled(false);


//        bottomSheetBehavior.setPeekHeight(500);

        slideUp = new SlideUpBuilder(tabLayout).withStartState(SlideUp.State.SHOWED).withStartGravity(Gravity.BOTTOM).build() ;

        textView.setSelected(true);

        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(newState == BottomSheetBehavior.STATE_EXPANDED) {
                    slideUp.hide();
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP , 28);
                }
                else{
                    slideUp.show();
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP , 23);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    slideUp.show();
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP , 23);
                } else{
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    slideUp.hide();
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP , 28);
                }
            }
        });



        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position){
                case 0 :
                    tab.setText("Trang Chủ") ;
                    tab.setIcon(R.drawable.iconhome) ;
                    break;
                case 1 :
                    tab.setText("Tìm Kiếm") ;
                    tab.setIcon(R.drawable.iconfind) ;
                    break;
                case 2 :
                    tab.setText("Thư Viện") ;
                    tab.setIcon(R.drawable.iconlibrary) ;
            }

        }).attach();



    }
    private void Mapping(){

        viewPager = findViewById(R.id.myViewPager) ;
        tabLayout = findViewById(R.id.myTabLayout) ;
        bottomSheet = findViewById(R.id.playLayout);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet) ;

        textView  = bottomSheet.findViewById(R.id.nameSongWhenPlay) ;
        textView1 = bottomSheet.findViewById(R.id.name) ;
        imageView = bottomSheet.findViewById(R.id.image) ;

        timeBar = bottomSheet.findViewById(R.id.timeBar) ;
        maxTime = bottomSheet.findViewById(R.id.maxtime) ;
        time    = bottomSheet.findViewById(R.id.time) ;

        nextButton = bottomSheet.findViewById(R.id.nextSong) ;
        preButton  = bottomSheet.findViewById(R.id.preSong) ;
        playButton = bottomSheet.findViewById(R.id.playSong) ;

    }

    @Override
    protected void onPause() {
        super.onPause();

        if(mp3 != null){
            mp3.reset();
            mp3.release();
        }
    }

    public static void Play(List<Song> songList , int k ){
        SetClick(songList , k ) ;
        Song song = songList.get(k) ;
        slideUp.hide();
        textView.setText(song.getNameSong());
        textView1.setText(song.getNameSong());
        Picasso.with(imageView.getContext()).load(song.getImageSong()).into(imageView);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        new Player().execute(song.getLink()) ;
        Handler handler = new Handler() ;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(next == true ){
                    if(k==songList.size()-1) Play(songList , 0 );
                    else Play(songList, k+1 );
                    next = false ;
                }else handler.postDelayed(this , 300) ;
            }
        } , 300) ;

    }

    private static void SetClick(List<Song> songList, int k) {
        bottomSheetBehavior.setPeekHeight(410);
        Song song = songList.get(k) ;
        slideUp.hide();
        textView.setText(song.getNameSong());
        textView1.setText(song.getNameSong());
        Picasso.with(imageView.getContext()).load(song.getImageSong()).into(imageView);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);


        playButton.setImageResource(R.drawable.pausebutton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPlay == true ){
                    playButton.setImageResource(R.drawable.playbutton);
                    isPlay = false ;
                    mp3.pause();
                }else {
                    playButton.setImageResource(R.drawable.pausebutton);
                    isPlay = true ;
                    mp3.start();
                }
            }
        });


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(k == songList.size() -1 ) Toast.makeText(MainActivity.bottomSheet.getContext(),  "Cuối Danh Sách", Toast.LENGTH_LONG).show();
                else Play(songList , k+1);
            }
        });

        preButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(k==0) Toast.makeText(MainActivity.bottomSheet.getContext(), "Đầu Danh Sách" , Toast.LENGTH_LONG).show();
                else Play(songList , k-1);
            }
        });
    }


    static class Player extends AsyncTask<String , Void , String>{

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);

            if(mp3 != null) mp3.release();

            mp3 = new MediaPlayer() ;

            try {
            mp3.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mp3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mp3.stop();
                    mp3.reset();
                }
            });

            mp3.setDataSource(baihat);
            mp3.prepare();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            mp3.start();
            SetTime();
        }
    }

    static void SetTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss") ;
        maxTime.setText(simpleDateFormat.format(mp3.getDuration()));
        timeBar.setMax(mp3.getDuration());

        timeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mp3.seekTo(seekBar.getProgress());
            }
        });


        final Handler handler = new Handler() ;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mp3 != null){
                    timeBar.setProgress(mp3.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("mm:ss") ;
                    time.setText(simpleDateFormat1.format(mp3.getCurrentPosition()));
                    handler.postDelayed(this , 300) ;
                    mp3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            next = true ;
                        }
                    });
                }
            }
        } , 300) ;
    }
}