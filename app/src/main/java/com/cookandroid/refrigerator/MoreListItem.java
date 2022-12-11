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



    MoreListItem(int image, String name, View.OnClickListener clickListener){
        this.image = image;
        this.name = name;
        this.onClickListener = clickListener;
    }

}
