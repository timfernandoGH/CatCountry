package com.example.admiralareospeedwag.catcountry;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MapActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        final Button challenge = (Button) findViewById(R.id.challenge);
        final Button menu = (Button) findViewById(R.id.menu);

        challenge.setOnClickListener(this);
        menu.setOnClickListener(this);
    }

    public void onClick(View view)
    {
        Intent intent;
        switch(view.getId())
        {
            case R.id.challenge:
                intent = new Intent(this, challengeActivity.class);
                startActivity(intent);
                break;
            case R.id.menu:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.item:
                break;
            case R.id.party:
                break;
        }
    }
}
