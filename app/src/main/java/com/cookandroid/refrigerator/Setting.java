package com.cookandroid.refrigerator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Setting extends Fragment {

    Button alart, day1, day3, day5, day7, btnOn, btnOff, btnHelp;
    Button save;
    int daycount = 5;
    int push = 0; // 0 on 1 off


    FragmentSettingListener listener;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof FoodPlus.FragmentPlusListener){
            listener = (FragmentSettingListener) context;
        }
        else{
            //오류
        }
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.setting, container, false);

        Bundle data = getArguments();
        if(data!= null){
            daycount = data.getInt("pushday");
            push = data.getInt("push");
        }
        alart = v.findViewById(R.id.alertname);
        day1 = v.findViewById(R.id.btn1day);
        day3 = v.findViewById(R.id.btn3day);
        day5 = v.findViewById(R.id.btn5day);
        day7 = v.findViewById(R.id.btn7day);

        btnOn = v.findViewById(R.id.btnon);
        btnOff = v.findViewById(R.id.btnoff);

        btnHelp = v.findViewById(R.id.btnhelp);
        save = v.findViewById(R.id.setsave);

        //데이터 가져오기

        if(push == 0){
            btnOn.setBackgroundResource(R.drawable.button_mini_select);
        }
        else{
            btnOff.setBackgroundResource(R.drawable.button_mini_select);
        }

        if(daycount == 1){
            day1.setBackgroundResource(R.drawable.button_mini_select);
        }
        else if(daycount == 3){
            day3.setBackgroundResource(R.drawable.button_mini_select);
        }
        else if(daycount == 5){
            day5.setBackgroundResource(R.drawable.button_mini_select);
        }
        else{
            day7.setBackgroundResource(R.drawable.button_mini_select);
        }




        day1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PushdayButtonInit();
                day1.setBackgroundResource(R.drawable.button_mini_select);
                daycount = 1;
            }
        });

        day3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PushdayButtonInit();
                day3.setBackgroundResource(R.drawable.button_mini_select);
                daycount = 3;
            }
        });

        day5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PushdayButtonInit();
                day5.setBackgroundResource(R.drawable.button_mini_select);
                daycount = 5;
            }
        });

        day7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PushdayButtonInit();
                day7.setBackgroundResource(R.drawable.button_mini_select);
                daycount = 7;
            }
        });

        btnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PushButtonInit();
                btnOn.setBackgroundResource(R.drawable.button_mini_select);
                push = 0;

            }
        });

        btnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PushButtonInit();
                btnOff.setBackgroundResource(R.drawable.button_mini_select);
                push = 1;

            }
        });

        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), HelpActivity.class);
                startActivity(intent);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onInputSettingSend(daycount, push);
            }
        });

        return v;
    }

    public void PushdayButtonInit(){
        day1.setBackgroundResource(R.drawable.button_mini);
        day3.setBackgroundResource(R.drawable.button_mini);
        day5.setBackgroundResource(R.drawable.button_mini);
        day7.setBackgroundResource(R.drawable.button_mini);
    }

    public void PushButtonInit(){
        btnOn.setBackgroundResource(R.drawable.button_mini);
        btnOff.setBackgroundResource(R.drawable.button_mini);
    }


    public interface FragmentSettingListener{
        void onInputSettingSend(int pushday, int push);
    }

}
