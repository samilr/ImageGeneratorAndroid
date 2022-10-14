package com.example.tareaintent;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Images extends AppCompatActivity {
    Button btnGoBack, btnExitApp, btnShowReflexion, btnShowPersonalSuperation;
    TextView tvName;
    ImageView imgV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        component();

        Bundle getName = getIntent().getExtras();
        tvName.setText("Hola, "+ getName.getString("nombre"));

    }

    public void component(){
        tvName = findViewById(R.id.tvName);
        btnShowPersonalSuperation = findViewById(R.id.btnShowPersonalPhrases);
        btnShowReflexion = findViewById(R.id.btnShowPhrases);
        btnGoBack = findViewById(R.id.btnGoBackToInfo);
        btnExitApp = findViewById(R.id.btnExitAppFromImg);
        imgV = findViewById(R.id.imgIMG);
    }

    public void goToInfoActivityFromImg(View view) {
        Intent intent = new Intent(this, UserInfo.class);
        startActivity(intent);
    }
    public void ExitAppFromUserImg(View view) {        final  AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Estas seguro que quieres salir?")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Images.this.finish();
                        System.exit(0);
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder.create();
        builder.show();
    }

    public void ShowPersonalPhrases(View view){
        Bundle getGender = getIntent().getExtras();
        String gender = getGender.getString("gender");
        int[] menPhrases = {R.drawable.sh1, R.drawable.sh2, R.drawable.sh3, R.drawable.sh4,R.drawable.sh5,R.drawable.sh6, R.drawable.sh7,R.drawable.sh8,R.drawable.sh9, R.drawable.sh10};
        int[] womenPhrases = {R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4,R.drawable.s5,R.drawable.s6, R.drawable.s7,R.drawable.s8,R.drawable.s9, R.drawable.s10};
        Random rand = new Random();

        switch (gender) {
            case "Hombre":
                imgV.setImageResource(menPhrases[rand.nextInt(menPhrases.length)]);
                break;
            case "Mujer":
                imgV.setImageResource(womenPhrases[rand.nextInt(womenPhrases.length)]);
                break;
            default:
                imgV.setImageResource(R.drawable.sh1);
        }
    }

    public void ShowLifeRefletion(View view){
        Bundle getGender = getIntent().getExtras();
        String gender = getGender.getString("gender");
        int[] menRefletion = {R.drawable.rh1, R.drawable.rh2, R.drawable.rh3, R.drawable.rh4,R.drawable.rh5,R.drawable.rh6, R.drawable.rh7,R.drawable.rh8,R.drawable.rh9, R.drawable.rh10};
        int[] womenRefletion = {R.drawable.r1, R.drawable.r2, R.drawable.r3, R.drawable.r11,R.drawable.r5,R.drawable.r6, R.drawable.r7,R.drawable.r8,R.drawable.r9, R.drawable.r10};
        Random rand = new Random();

        switch (gender) {
            case "Hombre":
                imgV.setImageResource(menRefletion[rand.nextInt(menRefletion.length)]);
                break;
            case "Mujer":
                imgV.setImageResource(womenRefletion[rand.nextInt(womenRefletion.length)]);
                break;
            default:
                imgV.setImageResource(R.drawable.sh1);
        }
    }
}
