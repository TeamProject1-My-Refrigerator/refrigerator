package com.cookandroid.refrigerator;

import android.view.View;
import android.widget.ListView;

public class MoreListItem {
    /*
    private int image;
    private String name;

     */
    private int image;
    private String name;
    private String exdate;
    private String indate;
    public View.OnClickListener onClickListener;


    public int getImage(){
        return image;
    }
    public void setImage(int image){
        this.image = image;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getExdate(){return exdate;}
    public String getIndate(){return indate;}


    MoreListItem(int image, String name, View.OnClickListener clickListener, String exdate, String indate){
        this.image = image;
        this.name = name;
        this.exdate = exdate;
        this.indate = indate;
        this.onClickListener = clickListener;
    }

}
