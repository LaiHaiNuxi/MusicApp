package com.example.rhythmiq.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Song implements Serializable {

@SerializedName("id_song")
@Expose
private String idSong;
@SerializedName("id_album")
@Expose
private String idAlbum;
@SerializedName("id_type")
@Expose
private String idType;
@SerializedName("id_playList")
@Expose
private String idPlayList;
@SerializedName("name_song")
@Expose
private String nameSong;
@SerializedName("image_song")
@Expose
private String imageSong;
@SerializedName("singer")
@Expose
private String singer;
@SerializedName("link")
@Expose
private String link;
@SerializedName("likes")
@Expose
private String likes;

public String getIdSong() {
return idSong;
}

public void setIdSong(String idSong) {
this.idSong = idSong;
}

public String getIdAlbum() {
return idAlbum;
}

public void setIdAlbum(String idAlbum) {
this.idAlbum = idAlbum;
}

public String getIdType() {
return idType;
}

public void setIdType(String idType) {
this.idType = idType;
}

public String getIdPlayList() {
return idPlayList;
}

public void setIdPlayList(String idPlayList) {
this.idPlayList = idPlayList;
}

public String getNameSong() {
return nameSong;
}

public void setNameSong(String nameSong) {
this.nameSong = nameSong;
}

public String getImageSong() {
return imageSong;
}

public void setImageSong(String imageSong) {
this.imageSong = imageSong;
}

public String getSinger() {
return singer;
}

public void setSinger(String singer) {
this.singer = singer;
}

public String getLink() {
return link;
}

public void setLink(String link) {
this.link = link;
}

public String getLikes() {
return likes;
}

public void setLike(String likes) {
this.likes = likes;
}

}