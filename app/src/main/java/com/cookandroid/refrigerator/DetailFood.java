package com.cookandroid.refrigerator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

public class DetailFood extends Activity {

    Button foodsub, foodchan;
    TextView food_name, food_date, food_storagy, food_indate;
    ImageView food_image;
    EditText food_name_chan, food_datechan, food_indatechan, food_storagychan;
    String name = "";
    String date = "";
    String storagy = "";
    String indate = "";
    int image = R.drawable.apple;
    int point;
    int cool;
    boolean change =false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailfood);

        foodsub = (Button)findViewById(R.id.btnfoodsub);
        food_image = (ImageView)findViewById(R.id.food_image);
        food_name = (TextView)findViewById(R.id.food_name);
        food_date = (TextView)findViewById(R.id.food_date);
        food_storagy = (TextView)findViewById(R.id.food_storagy);
        food_indate = (TextView)findViewById(R.id.food_indate);
        foodchan = (Button)findViewById(R.id.btnfoodchange);
        food_name_chan = (EditText)findViewById(R.id.food_name_change);
        food_datechan = (EditText)findViewById(R.id.food_datecha);
        food_indatechan = (EditText)findViewById(R.id.food_indatecha);
        food_storagychan = (EditText)findViewById(R.id.food_storagycha);
        Intent rxIntent = getIntent();
        Bundle extras = rxIntent.getExtras();

        image = extras.getInt("Image");
        name = extras.getString("Name");
        change = false;
        date = "유통기한"+"\n"+ extras.getString("Date");
        indate = "입고날짜"+"\n"+extras.getString("Indate");
        storagy = "<보관방법>\n\n" + extras.getString("Storagy");

        //point 값에 위치 저장 ->다시 main activity 에 전달하여 리스트 수정
        point = extras.getInt("Point");
        cool = extras.getInt("Cool");

        food_image.setImageResource(image);
        food_name.setText(name);
        food_date.setText(date);
        food_indate.setText(indate);
        food_storagy.setText(storagy);

        foodsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent outintent = new Intent(getApplicationContext(), MainActivity.class);
               outintent.putExtra("Point", point);
               if(cool == 0){
                   setResult(4, outintent);
               }
               else if(cool == 1){
                   setResult(5, outintent);
               }
               finish();
            }
        });

        foodchan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!change){
                    change = true;
                    //edittext
                    food_name_chan.setVisibility(View.VISIBLE);
                    food_name_chan.setText(food_name.getText());
                    food_datechan.setVisibility(View.VISIBLE);
                    food_datechan.setText(food_date.getText());
                    food_indatechan.setVisibility(View.VISIBLE);
                    food_indatechan.setText(food_indate.getText());
                    food_storagychan.setVisibility(View.VISIBLE);
                    food_storagychan.setText(food_storagy.getText());
                    //text
                    food_name.setVisibility(View.GONE);
                    food_date.setVisibility(View.GONE);
                    food_indate.setVisibility(View.GONE);
                    food_storagy.setVisibility(View.GONE);

                    foodchan.setText("수정완료");
                }
                else{
                    change=false;

                    name = food_name_chan.getText().toString();
                    indate = food_indatechan.getText().toString();
                    date = food_datechan.getText().toString();
                    storagy = food_storagychan.getText().toString();

                    //text
                    food_name.setVisibility(View.VISIBLE);
                    food_name.setText(name);
                    food_indate.setVisibility(View.VISIBLE);
                    food_indate.setText(indate);
                    food_date.setVisibility(View.VISIBLE);
                    food_date.setText(date);
                    food_storagy.setVisibility(View.VISIBLE);
                    food_storagy.setText(storagy);
                    //edittext
                    food_name_chan.setVisibility(View.GONE);
                    food_storagychan.setVisibility(View.GONE);
                    food_indatechan.setVisibility(View.GONE);
                    food_datechan.setVisibility(View.GONE);

                    foodchan.setText("수정하기");
                    //Food food = new Food(name, expdate, inputdate, cool, "보관법", R.drawable.apple);

                    Food food = new Food(name, date, indate, cool, storagy, image);

                    Intent outintentf = new Intent(getApplicationContext(), MainActivity.class);
                    outintentf.putExtra("Point", point);
                    outintentf.putExtra("Foodchange", food);

                    setResult(6, outintentf);


                }

            }
        });
        //데이터 입력 못받으면 avd오류
        /*
        Intent rxIntent = getIntent();
        Bundle extras = rxIntent.getExtras();

        name = extras.getString("Name");
        date = extras.getString("Date");
        storagy = extras.getString("Storagy");
        image = R.drawable.apple;

        food_name.setText(name);
        food_date.setText(date);
        food_storagy.setText(storagy);
        food_image.setImageResource(image);
        */
    }

}
