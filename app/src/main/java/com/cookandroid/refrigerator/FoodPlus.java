package com.cookandroid.refrigerator;

import static android.content.Intent.getIntent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FoodPlus extends Fragment {

    String[] namearr = new String[]{"김치", "대파", "꽃게", "무", "된장", "양파", "청양고추", "고등어", "간 돼지고기", "돼지고기", "소고기", "마라소스", "콩나물", "배추", "두부", "대패삼겹살", "닭고기", "베이컨", "우유", "상추", "치즈", "고구마", "달걀", "새우", "마늘", "다진마늘", "우동면", "멸치", "떡", "어묵", "시금치", "단무지", "당근", "숙주", "사과", "파프리카", "콜라", "오이", "소세지", "오렌지", "연어", "단호박", "수박"};
    int[] imagearr = new int[]{ R.drawable.kimchi , R.drawable.leek, R.drawable.crap, R.drawable.radish0, R.drawable.soybean, R.drawable.onion, R.drawable.pepper, R.drawable.mackerel, R.drawable.groundpork, R.drawable.pork, R.drawable.beef, R.drawable.mara, R.drawable.beentree, R.drawable.cabbage, R.drawable.tofu, R.drawable.shaved, R.drawable.chicken, R.drawable.bacon, R.drawable.milk, R.drawable.lettuce, R.drawable.cheese, R.drawable.goguma, R.drawable.egg, R.drawable.shirimp, R.drawable.garlic, R.drawable.garliccrush, R.drawable.udonnoodle, R.drawable.anchovy, R.drawable.dduck, R.drawable.fishcake, R.drawable.spinach, R.drawable.danmuzi, R.drawable.carrot, R.drawable.sukju, R.drawable.apple, R.drawable.paprika, R.drawable.coke, R.drawable.cucumber, R.drawable.sausage, R.drawable.orange, R.drawable.salmon, R.drawable.sweetpum, R.drawable.watermelon};
    String[] stoarr = new String[]{};

    EditText edtname, edtessyear, edtessmonth, edtessday, edtinpyear, edtinpmonth, edtinpday;
    Button btnadd, btncool, btnice, btnusual;
    String inputdate = "";
    String expdate = "";
    String name = "";
    int cool = 0;
    FragmentPlusListener listener;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof FragmentPlusListener){
            listener = (FragmentPlusListener) context;
        }
        else{
            //오류
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.food_plus, container, false);

        edtname = v.findViewById(R.id.edtfoodname);
        edtessyear = v.findViewById(R.id.edtexpyear);
        edtessmonth = v.findViewById(R.id.edtexpmonth);
        edtessday = v.findViewById(R.id.edtexpday);
        edtinpyear = v.findViewById(R.id.edtinyear);
        edtinpmonth = v.findViewById(R.id.edtinmonth);
        edtinpday = v.findViewById(R.id.edtinday);
        btnadd = v.findViewById(R.id.btnadd);
        btncool = v.findViewById(R.id.btnpluscool);
        btnice = v.findViewById(R.id.btnplusice);
        btnusual = v.findViewById(R.id.btnplususual);

        btncool.setBackgroundResource(R.drawable.button_rec_select);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtname.getText().toString().equals("") || edtname.getText().toString() == null){
                    Toast.makeText(getActivity().getApplicationContext(), " 이름을 입력해 주세요.", Toast.LENGTH_SHORT).show();
                }
                else if(edtessyear.getText().toString().equals("") || edtessmonth.getText().toString().equals("") || edtessday.getText().toString().equals("")){
                    Toast.makeText(getActivity().getApplicationContext(), " 유통기한을 입력해 주세요.", Toast.LENGTH_SHORT).show();

                }
                else if(edtinpyear.getText().toString().equals("") || edtinpmonth.getText().toString().equals("") || edtinpday.getText().toString().equals("")){
                    Toast.makeText(getActivity().getApplicationContext(), " 입고기한을 입력해 주세요.", Toast.LENGTH_SHORT).show();

                }
                else{
                    name = String.valueOf(edtname.getText());
                    inputdate = edtinpyear.getText() +"-"+edtinpmonth.getText() +"-" + edtinpday.getText();
                    expdate = edtessyear.getText() +"-"+edtessmonth.getText() +"-" + edtessday.getText();

                    int imageset;
                    imageset = R.drawable.apple;
                    String storag = "보관법";

                    //food 생성 후 activity 에 전송
                    for(int i = 0; i < namearr.length; i++){
                        if(name.equals(namearr[i])){
                            imageset = imagearr[i];
                            break;
                        }
                    }
                    Food food = new Food(name, expdate, inputdate, cool, storag, imageset);
                    listener.onInputSend(food);

                    //finish(); <- 액티비티 종료
                }

            }
        });

        btncool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonColorInit();
                btncool.setBackgroundResource(R.drawable.button_rec_select);
                cool = 0;
            }
        });

        btnice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonColorInit();
                btnice.setBackgroundResource(R.drawable.button_rec_select);
                cool = 1;
            }
        });

        btnusual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonColorInit();
                btnusual.setBackgroundResource(R.drawable.button_rec_select);
                cool = 2;
            }
        });

        //food 생성 후 activity 에 전송


        /*
        Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);

        intent.putExtra("Foodplus", food);
        setResult(RESULT_OK, intent);

        startActivityForResult(intent, 0);


         */

        return v;
    }

    public void buttonColorInit(){
        btncool.setBackgroundResource(R.drawable.button_rec);
        btnice.setBackgroundResource(R.drawable.button_rec);
        btnusual.setBackgroundResource(R.drawable.button_rec);

    }

    public interface FragmentPlusListener{
        void onInputSend(Food input);
    }
}
