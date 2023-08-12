package com.example.rhythmiq.Service;

public class APIService {
    private static String base_url = "https://rhythmiq.000webhostapp.com/" ;

    public static DataService getService(){
        return APIClient.getclient(base_url).create(DataService.class) ;
    }
}