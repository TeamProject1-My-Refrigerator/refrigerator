package com.cookandroid.refrigerator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

public class DetailRecipy extends Activity {

    TextView recipy_name, recipy_need, recipy_recipy;
    ImageView recipy_image;
    ImageView recipy_video;
    String name;
    String summary;
    String need;
    String recipy;
    String video;
    int image;
    int size;
    int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailrecipy);

        recipy_image = (ImageView)findViewById(R.id.recipy_image);
        recipy_name = (TextView)findViewById(R.id.recipy_name);
        recipy_need = (TextView)findViewById(R.id.recipy_need);
        recipy_recipy = (TextView)findViewById(R.id.recipy_recipy);
        recipy_video = (ImageView) findViewById(R.id.recipy_video);

        Intent rxIntent = getIntent();
        Bundle extras = rxIntent.getExtras();
        RecipyInfo recipyInfo;

        recipyInfo = (RecipyInfo) rxIntent.getSerializableExtra("Object");

        //need data -> image, name, need, recipy, youtube url
        /*
        name = extras.getString("Name");
        need = extras.getString("Need");
        recipy = extras.getString("Recipy");
        image = extras.getInt("Image");
        //video = extras.getString("Video");


         */
        name = recipyInfo.getName() ;
        recipy = recipyInfo.getDesc();
        image = recipyInfo.getPicture();
        need = "필수 재료 : ";
        size = recipyInfo.getEssentialIngredients().size()-1;
        i = 0;
        while( i < size){
            need = need + recipyInfo.getEssentialIngredients().get(i)+", ";
            i++;
        }
        need = need + recipyInfo.getEssentialIngredients().get(i)+"\n\n";
        need = need + "추가 재료 : ";
        size = recipyInfo.getAdditionalIngredients().size()-1;
        i = 0;
        while( i < size ){
            need = need + recipyInfo.additionalIngredients.get(i)+", ";
            i++;
        }
        need = need + recipyInfo.getAdditionalIngredients().get(i);
        video = recipyInfo.getYoutubeLink();
        recipy_name.setText(name);
        recipy_recipy.setText(recipy);
        recipy_need.setText(need);
        recipy_image.setImageResource(image);
        recipy_video.setImageResource(R.drawable.youtube);


        //동영상 주소 변경 구현 필요

        recipy_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW)
                        .setData(Uri.parse(video)).setPackage("com.google.android.youtube"));
            }
        });
        //유튜브 링크
        /*
        startActivity(new Intent(Intent.ACTION_VIEW)
            .setData(Uri.parse("youtube_url"))
            .setPackage("com.google.android.youtube"));
         */

    }
    //계환
    //레시피 화면에서 사용하는 재료의 정보를 출력하기 위해 사용할 재료를 검색하는 메소드(필수재료와 보유재료를 비교)
    public final ArrayList<Food> findUsingFood(){
        MainActivity mainActivity = new MainActivity();
        ArrayList<Food> foodList = mainActivity.getFoodList();  //보유 재료 리스트 (mainActivty에 get메소드 만들었습니다.)
        ArrayList<Food> usingFoodList = new ArrayList<>();  //사용할 재료 리스트 (이 리스트에 저장해서 반환)

        Intent rxIntent = getIntent();
        Bundle extras = rxIntent.getExtras();
        RecipyInfo recipyInfo;
        recipyInfo = (RecipyInfo) rxIntent.getSerializableExtra("Object");

        size = recipyInfo.getEssentialIngredients().size()-1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < foodList.size(); j++) {
                if(recipyInfo.getEssentialIngredients().get(i) == foodList.get(j).getName()){
                    usingFoodList.add(foodList.get(j));
                    break;   //같은 이름의 재료 중 유통기한이 얼마 안남은 재료를 선택하기 위해 찾으면 반복문을 빠져나간다.
                }
            }
        }
        return usingFoodList;
    }
}
