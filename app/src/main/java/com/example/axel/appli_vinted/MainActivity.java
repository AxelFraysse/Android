package com.example.axel.appli_vinted;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// rediréction vers la page de login
        final Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

//rediréction vers la page d'achats femme
        final Button FButton = (Button) findViewById(R.id.menuButtonFemale);
        FButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScrollingActivity.class); //changer le loginactivity pour rediriger vers la page d'achats femme
                startActivity(intent);
            }
        });
//rediréction vers la page d'achats homme
        final Button MButton = (Button) findViewById(R.id.menuButtonMale);
        MButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScrollingActivityMen.class); //chaner le loginactivity pour rediriger vers la page d'achats homme
                startActivity(intent);
            }
        });
    }
}
