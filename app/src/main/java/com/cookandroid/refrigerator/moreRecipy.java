package com.cookandroid.refrigerator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

public class moreRecipy extends Activity {

    Button back;
    ImageView foodimag;
    ListView morelist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailrecipyexp);

        back = (Button) findViewById(R.id.btnback);
        foodimag = (ImageView) findViewById(R.id.moreimage);

        morelist = (ListView)findViewById(R.id.morelist);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
