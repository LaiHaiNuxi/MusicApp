package com.example.rhythmiq.Service;

import com.example.rhythmiq.Model.Album;
import com.example.rhythmiq.Model.Playlistfortoday;
import com.example.rhythmiq.Model.Song;
import com.example.rhythmiq.Model.Type;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
    @GET("server/playlistforday.php")
    Call<List<Playlistfortoday>> GetDataPlaylist();

    @GET("server/type.php")
    Call<List<Type>> GetDataType() ;

    @GET("server/album.php")
    Call<List<Album>> GetDataAlbum() ;

    @GET("server/lovesong.php")
    Call<List<Song>> GetLoveSong() ;

    @FormUrlEncoded
    @POST("server/listsong.php")
    Call<List<Song>> GetlistSongOfPlayList(@Field("idplaylist") String idPlayList ) ;

    @FormUrlEncoded
    @POST("server/listsong.php")
    Call<List<Song>> GetlistSongOfAlbum(@Field("idAlbum") String idAlbum ) ;

    @FormUrlEncoded
    @POST("server/listsong.php")
    Call<List<Song>> GetlistSongOfType(@Field("idType") String idType ) ;

    @FormUrlEncoded
    @POST("server/listsong.php")
    Call<List<Song>> GetlistSongOfSearch(@Field("key") String key ) ;

    @FormUrlEncoded
    @POST("server/likes.php")
    Call<String> UpdateLikes(@Field("idsong") String idsong) ;


}
