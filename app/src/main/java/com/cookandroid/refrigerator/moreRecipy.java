package com.cookandroid.refrigerator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class moreRecipy extends Activity implements View.OnClickListener{

    Button back;
    ImageView foodimag, intro;
    ListView morelist;
    MoreListAdapter adapter;
    TextView morename;
    ArrayList<Food> foodArrayList = new ArrayList<>();
    ArrayList<String> tag = new ArrayList<>();
    ArrayList<String> temp = new ArrayList<>();

    RecipyInfo recipyInfo;
    int size;
    int a = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailrecipyexp);

        a = 0;
        back = (Button) findViewById(R.id.btnback);
        foodimag = (ImageView) findViewById(R.id.moreimage);

        morelist = (ListView)findViewById(R.id.morelist);

        morename = (TextView)findViewById(R.id.morename);
        intro = (ImageView)findViewById(R.id.intro);
        adapter = new MoreListAdapter();
        Intent rxIntent = getIntent();
        Bundle extras = rxIntent.getExtras();

        recipyInfo = (RecipyInfo) extras.getSerializable("Object");
        foodimag.setImageResource(recipyInfo.getPicture());
        morename.setText(recipyInfo.getName());
        tag = (ArrayList<String>) recipyInfo.getEssentialIngredients().clone();

        foodArrayList = findUsingFood();
        //foodlist 로 변경
        size = foodArrayList.size();

        for(int i = 0; i < size; i++){
            adapter.addItem(new MoreListItem(foodArrayList.get(i).getImage(), foodArrayList.get(i).getName(), this, foodArrayList.get(i).getExpiration_date(), foodArrayList.get(i).getInput_date()));
        }

        morelist.setAdapter(adapter);

        for (int i = 0; i < foodArrayList.size(); i++){
            //Toast.makeText(getApplicationContext(), foodArrayList.get(i).getName(), Toast.LENGTH_SHORT ).show();

        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public final ArrayList<Food> findUsingFood(){
        ArrayList<Food> foodList = ((MainActivity)MainActivity.mainContext).getFoodlist(); //보유 재료 리스트 (mainActivty에 get메소드 만들었습니다.)
        ArrayList<Food> usingFoodList = new ArrayList<>();  //사용할 재료 리스트 (이 리스트에 저장해서 반환)


        size = tag.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < foodList.size(); j++) {
                if (tag.get(i).equals(foodList.get(j).getName())) {
                    usingFoodList.add(foodList.get(j));
                    break;   //같은 이름의 재료 중 유통기한이 얼마 안남은 재료를 선택하기 위해 찾으면 반복문을 빠져나간다.
                }
            }
        }
        return usingFoodList;
    }

    @Override
    public void onClick(View view) {
        View parent = (View) view.getParent();
        String positi = (String) parent.getTag();
        int position = Integer.parseInt(positi);

        morelist = (ListView)findViewById(R.id.morelist);

        adapter = new MoreListAdapter();
        Intent rxIntent = getIntent();
        Bundle extras = rxIntent.getExtras();

        if(a == 0){
            recipyInfo = (RecipyInfo) extras.getSerializable("Object");
            tag = (ArrayList<String>) recipyInfo.getEssentialIngredients().clone();
            a++;
        }


        deleteFood(tag.get(position));
        tag.remove(position);
        temp = (ArrayList<String>)tag.clone();
        foodArrayList = findUsingFood();
        //foodlist 로 변경
        int size = foodArrayList.size();

        for(int i = 0; i < size; i++){
            adapter.addItem(new MoreListItem(foodArrayList.get(i).getImage(), foodArrayList.get(i).getName(), this, foodArrayList.get(i).getExpiration_date(), foodArrayList.get(i).getInput_date()));
        }

        morelist.setAdapter(adapter);

        //Toast.makeText(getApplicationContext(), "click" + position, Toast.LENGTH_SHORT).show();
    }

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
