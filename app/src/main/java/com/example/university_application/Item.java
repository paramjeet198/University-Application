package com.example.university_application;

import android.widget.Button;
import android.widget.EditText;

public class Item {
    String universityId;
    String country;
    Button website;


    String Myurl;
    public Item(String universityId, String country) {
        this.universityId = universityId;
        this.country = country;
    }

    public Item(String universityId, String country, String myurl) {
        this.universityId = universityId;
        this.country = country;
        Myurl = myurl;
    }

    public String getMyurl() {
        return Myurl;
    }

    public void setMyurl(String myurl) {
        Myurl = myurl;
    }

    public Button getWebsite() {
        return website;
    }


    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String universityId) {
        this.universityId = universityId;
    }

    public void setCountry(String country) {
        this.country = country;
    }

//    public void setWebsite(Button website) {
//        this.website = website;
//    }



    public String getCountry() {
        return country;
    }

//    public Button getWebsite() {
//        return website;
//    }
}
