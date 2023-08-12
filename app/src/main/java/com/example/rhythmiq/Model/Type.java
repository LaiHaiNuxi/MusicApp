package com.example.rhythmiq.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Type implements Serializable {

@SerializedName("id_type")
@Expose
private String idType;
@SerializedName("name_type")
@Expose
private String nameType;
@SerializedName("image_type")
@Expose
private String imageType;

public String getIdType() {
return idType;
}

public void setIdType(String idType) {
this.idType = idType;
}

public String getNameType() {
return nameType;
}

public void setNameType(String nameType) {
this.nameType = nameType;
}

public String getImageType() {
return imageType;
}

public void setImageType(String imageType) {
this.imageType = imageType;
}

}