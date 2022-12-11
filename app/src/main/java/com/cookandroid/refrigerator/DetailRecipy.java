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

    TextView recipy_name, recipy_need,recipy_add, recipy_recipy;
    ImageView recipy_image;
    ImageView recipy_video;
    String name;
    String summary;
    String need;
    String more;
    String recipy;
    String video;
    int image;
    int size;
    int i;

    Button btmore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailrecipy);

        recipy_image = (ImageView)findViewById(R.id.recipy_image);
        recipy_name = (TextView)findViewById(R.id.recipy_name);
        recipy_need = (TextView)findViewById(R.id.recipy_need);
        recipy_recipy = (TextView)findViewById(R.id.recipy_recipy);
        recipy_video = (ImageView) findViewById(R.id.recipy_video);
        recipy_add = (TextView)findViewById(R.id.recipy_add);
        btmore = (Button)findViewById(R.id.btnmore);
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
        need = "필수 재료"+"\n";
        size = recipyInfo.getEssentialIngredients().size()-1;
        i = 0;
        while( i < size){
            need = need + recipyInfo.getEssentialIngredients().get(i)+", ";
            i++;
        }
        need = need + recipyInfo.getEssentialIngredients().get(i);
        more = "추가 재료"+"\n";
        size = recipyInfo.getAdditionalIngredients().size()-1;
        i = 0;
        while( i < size ){
            more = more + recipyInfo.additionalIngredients.get(i)+", ";
            i++;
        }
        need = need + recipyInfo.getAdditionalIngredients().get(i);
        video = recipyInfo.getYoutubeLink();
        recipy_name.setText(name);
        recipy_recipy.setText(recipy);
        recipy_need.setText(need);
        recipy_add.setText(more);
        recipy_image.setImageResource(image);
        recipy_video.setImageResource(R.drawable.youtube);


        //동영상 주소 변경 구현 필요
        btmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), moreRecipy.class);
                intent.putExtra("Object",recipyInfo);
                startActivity(intent);
            }
        });

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

    public final ArrayList<Food> findUsingFood(){
        ArrayList<Food> foodList = ((MainActivity)MainActivity.mainContext).getFoodlist(); //보유 재료 리스트 (mainActivty에 get메소드 만들었습니다.)
        ArrayList<Food> usingFoodList = new ArrayList<>();  //사용할 재료 리스트 (이 리스트에 저장해서 반환)

        Intent rxIntent = getIntent();
        Bundle extras = rxIntent.getExtras();
        RecipyInfo recipyInfo;
        recipyInfo = (RecipyInfo) rxIntent.getSerializableExtra("Object");

        size = recipyInfo.getEssentialIngredients().size()-1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < foodList.size(); j++) {
                if (recipyInfo.getEssentialIngredients().get(i) == foodList.get(j).getName()) {
                    usingFoodList.add(foodList.get(j));
                    break;   //같은 이름의 재료 중 유통기한이 얼마 안남은 재료를 선택하기 위해 찾으면 반복문을 빠져나간다.
                }
            }
        }
        return usingFoodList;
    }

    //레시피->재료정보 화면에서 재료 삭제하는 함수
    //어디 만들어야 할지 몰라서 일단 여기다가 만들겠습니다..!
    public void deleteFood(String deletename) {
        ArrayList<Food> foodList = ((MainActivity)MainActivity.mainContext).getFoodlist(); //보유 재료 리스트 (mainActivty에 get메소드 만들었습니다.)

        //삭제할 재료 이름이랑 입고날짜or유통기한 받아서 변수로 저장
        //비교는 재료이름이랑 날짜로 하려고 하는데 유통기한 기준 정렬 되어있으면 이름으로만 비교해도 될 것 같습니다.
        String delete_name=deletename; // = ... 일단 ""로 초기화 해놓았습니다.
        for (int i = 0; i < foodList.size(); i++) {
            if (delete_name.equals(foodList.get(i).getName())) {
                foodList.remove(i);
                break;
            }
        }
        ((MainActivity)MainActivity.mainContext).setFoodlist(foodList);  //메인액티비티에 있는 재료 리스트를 업데이트 해준다.
    }
}
