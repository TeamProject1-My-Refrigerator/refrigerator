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
    String[] stoarr = new String[]{"냉장: 밀폐 용기에 배추 단면이 위로 오도록 담아 냉장 (0~5도) 보관한다.\n"+"*온도 변화가 많으면 김치가 빨리 익어 김치 전용 냉장고에 넣어 보관한다.\n"+"*김치가 발효되면서 가스가 발생해 국물이 넘칠 위험이 있어, 용기의 7~80% 정도만 담는다\n", "냉장: \n"+"1.\t대파를 보관용기에 담기 쉽게 잘라준다.\n" +"2.\t흰대에서 나온 껍질과 대파잎은 육수용으로 골라내고, 나머지 껍질은 버린다. \n" +"3.\t씻지않고 보관한다.\n" +"4.\t사용시 씻어 사용한다. \n", "냉동: 지퍼팩에 넣어서 보관한다", "냉장: 무청(무 끝에 뾰족한 부분)을 제거하고, 비닐호일로 감싼뒤 보관한다.","냉장: 먹을 만큼의 된장을 따로 보관하여 오염되는 것을 방지한다. ",  "냉장: 세척하지 않고, 각각 랩에 싸서 보관한다", "냉장: 밀폐용기에 키친타월을 깔고, 고추, 키친타원 순으로 겹겹이 쌓아 보관한다.", "냉동: 고등어를 손질한뒤 식초물과, 소금을 뿌리고, 지퍼백에 넣어 보관한다. ","냉동: 지퍼백에 담아 납작하게 해서 보관한다.", "냉장, 냉동:\n" +"1.\t바닥에 랩이나 종이호일을 깔고 삼겹살 한줄을 얹어준다.\n" +"2.\t얹어준 삼겹살 위로 랩을 자르지 말고 위치만 옮겨 삼겹살을 덮어준다.\n" +"3.\t랩으로 덮어진 삼겹살 한줄 위에 비스듬하게 다른 삼겹살을 한줄더 얹어준다.\n" +"4.\t새로 얹어둔 삼겹살을 2번과 동일한 방법으로 랩을 자르지 않고 다시 다른쪽으로 이동해서 덮어준다.\n" + "5.\t구매한 삼겹살을 모두 같은 방법으로 랩으로 포장해둔다.\n", "냉장, 냉동: 소분하여 비닐에 넣은 후에 용기에 담아 보관한다.", "냉장보관", "냉장: 용기에 키친타월을 깔고나서 물기를 제거한 콩나물을 넣고 보관한다.","냉장: 배추를 신문지로 감싼 후에 비닐봉지에 넣어서 보관한다.", "냉장: \n" +"1.\t밀폐용기에 담은 후에 두부가 물에 잠길 정도로 물을 붓는다. \n" +"2.\t소금을 한 꼬집 정도 넣고 보관한다.\n" +"*한번 개봉한 두부는 금방 상하기 때문에 빨리 먹는 것을 추천한다.\n" , "냉동: 비닐에 대패삼겹살을 펼치고 말아주고나서 지퍼팩에 담아 보관한다.", "냉장, 냉동: 밀폐용기에 담아 보관하다.", "냉동: 베이컨을 지퍼백에 소분하여 넣어준뒤 쌓아서 보관한다.", "냉장보관", "냉장: 밀폐용기 바닥에 키친타월과 상추를 번갈아가며 쌓아서 보관한다.", "냉장: 공기가 접촉하지 않게 해서 보관한다.", "냉장: 물기를 없앤 고구마를 신문지에 감싸서 보관한다.",  "냉장보관", "냉동: 새우를 지퍼백에 소분해서 담아 보관한다.","냉장:\n" +"1.\t밀폐용기에 설탕을 1cm두께로 깔아준다.\n" +"2.\t종이호일을 깔아주고 마늘을 넣어서 보관한다. \n", "냉동:\n" +"1.\t얼음틀에 간마늘을 얼려준다.\n" + "2.\t얼린 간마늘을 지퍼백에 넣어서 보관한다. \n" , "냉동보관","밀폐용기에 담아 보관한다.", "냉동: 소분한 떡을 랩을 씌우고 지퍼백에 담아 보관한다.", "냉장, 냉동: 지퍼백에 넣어 보관한다.", "냉장: 물에 씻지 않은 시금치를 신문지나 키친타월에 감싸 지퍼백에 담아 보관한다.", "냉장: 밀폐용기에 물과 식초 그리고 설탕을 2:1:1의 비율로 섞은 후에 단무지를 넣어서 보관한다.", "냉장: 물에 씻지 않은 당근을 신문지나 키친타월에 감싸 지퍼백에 담아 보관한다.", "냉장: 용기에 키친타월을 깐후 물기를 제거한 콩나물을 넣고 보관", "랩으로 싸서 보관한다.", "냉장: 물기를 제거한 파프리카를 지퍼팩에 넣어서 보관한다.", "냉장보관", "냉장: 물기를 제거한 오이를 랩이나 키친타월로 감싸서 보관한다.", "냉동: 소시지를 소분해서 지퍼팩에 넣어서 보관한다.", "냉장: 물기를 제거한 오렌지를 랩으로 감싸서 보관한다.", "냉장: 연어에 청주를 조금 발라준 뒤 랩으로 밀봉하여 용기에 넣어서 보관한다.", "냉장: 씨앗을 제거한 단호박을 먹기 좋은 크기로 잘라서 밀폐용기에 넣어서 보관한다.", "냉장: 알맞은 크기로 자른 수박을 밀폐용기에 넣어서 보관한다."};

    EditText edtname, edtessyear, edtessmonth, edtessday, edtinpyear, edtinpmonth, edtinpday;
    Button btnadd, btncool, btnice, btnusual;
    String inputdate = "";
    String expdate = "";
    String name = "";
    int cool = 0;
    boolean check = false;
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
                    check = false;
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
                            storag = stoarr[i];
                            check = true;
                            break;
                        }
                    }
                    if(check){
                        Food food = new Food(name, expdate, inputdate, cool, storag, imageset);
                        listener.onInputSend(food);
                    }
                    else{
                        Toast.makeText(getActivity().getApplicationContext(), " 재료명을 확인해 주세요.", Toast.LENGTH_SHORT).show();
                    }


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
