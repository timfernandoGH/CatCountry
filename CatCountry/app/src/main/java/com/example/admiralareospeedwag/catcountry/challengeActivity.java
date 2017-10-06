package com.example.admiralareospeedwag.catcountry;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class challengeActivity extends AppCompatActivity  implements View.OnClickListener{

    public static int tempCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);

        final Button escape = (Button)findViewById(R.id.Escape);
        escape.setOnClickListener(this);

        final Button attack = (Button)findViewById(R.id.dmgButton);
        attack.setOnClickListener(this);

    }





    public void onClick(View view)
   {
        Intent intent;
        switch(view.getId())
        {
           case R.id.Escape:
                intent = new Intent(this, MapActivity.class);
                startActivity(intent);
                break;

            case R.id.dmgButton:
                tempCounter++;
                String tempText = Integer.toString(this.tempCounter);
                R.id.dmgButton.setText( tempText );
                break;


        }
    }
}
