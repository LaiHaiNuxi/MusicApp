package com.example.rhythmiq.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Playlistfortoday implements Serializable {

@SerializedName("id_playList")
@Expose
private String idPlayList;
@SerializedName("name_playList")
@Expose
private String namePlayList;
@SerializedName("image_icon")
@Expose
private String imageIcon;
@SerializedName("back_ground")
@Expose
private String backGround;

public String getIdPlayList() {
return idPlayList;
}

public void setIdPlayList(String idPlayList) {
this.idPlayList = idPlayList;
}

public String getNamePlayList() {
return namePlayList;
}

public void setNamePlayList(String namePlayList) {
this.namePlayList = namePlayList;
}

public String getImageIcon() {
return imageIcon;
}

public void setImageIcon(String imageIcon) {
this.imageIcon = imageIcon;
}

public String getBackGround() {
return backGround;
}

public void setBackGround(String backGround) {
this.backGround = backGround;
}

}