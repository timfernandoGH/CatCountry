package com.example.admiralareospeedwag.catcountry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button play = (Button)findViewById(R.id.play1);
        play.setOnClickListener(this);
}
    @Override
    public void onClick(View v)
    {
        Intent intent;
        switch(v.getId()) {
            case R.id.play1:
                intent = new Intent(this, PlayActivity.class);
                startActivity(intent);
                break;
            case R.id.options:
                //intent = new Intent(this, PlayActivity.class);
                //startActivity(intent);
                break;
            case R.id.ads:
               // intent = new Intent(this, PlayActivity.class);
                //startActivity(intent);
                break;

        }

    }
}
