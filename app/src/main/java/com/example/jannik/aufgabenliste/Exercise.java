package com.example.jannik.aufgabenliste;

import java.io.Serializable;


/**
 * Created by jannik on 02.05.17.
 */

public class Exercise implements Serializable {
    private String headline;
    private String info;

    public Exercise(){
        headline = "";
        info = "";
    }

    public Exercise(String headline, String info) {
        this.headline = headline;
        this.info = info;
    }

    public String getHeadline() {
        return headline;
    }

    public String getInfo() {
        return info;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
