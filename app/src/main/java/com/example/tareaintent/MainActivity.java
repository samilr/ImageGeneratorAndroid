package com.example.tareaintent;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ImageView imgContinue, imgExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appComponents();
    }

    public void appComponents(){
        imgContinue = findViewById(R.id.imgContinue);
        imgExit = findViewById(R.id.imgExitApp);
    }
    public void goToActivityUserInfo(View view) {
        Intent intent = new Intent(this, UserInfo.class);
        startActivity(intent);
    }
    public void exitApp(View view) {
        final  AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Estas seguro que quieres salir?")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finishAffinity();
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder.create();
        builder.show();
    }
}

