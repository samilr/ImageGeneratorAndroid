package com.example.tareaintent;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UserInfo extends AppCompatActivity {
    Button btnContinueImages, btnExitAppFromUserInfo, btnGoBackToMain;
    RadioGroup rbgGender;
    RadioButton rbGender;
    EditText txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        component();
    }
    public void component(){
        btnContinueImages = findViewById(R.id.btnContinueImg);
        btnGoBackToMain = findViewById(R.id.btnGoBackToMain);
        btnExitAppFromUserInfo = findViewById(R.id.btnExitAppFromInfo);
        txtName = findViewById(R.id.txtName);
        rbgGender = findViewById(R.id.rbgGender);
    }
    public void goToActivityImages(View view) {
        String nombre = txtName.getText().toString();
        int gender = rbgGender.getCheckedRadioButtonId();
        rbGender = findViewById(gender);
        String genderSelected = rbGender.getText().toString();

            Intent intent = new Intent(this, Images.class);
            intent.putExtra("gender",genderSelected);
            intent.putExtra("nombre",nombre);
            startActivity(intent);

    }
    public void goToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void ExitAppFromUserInfo(View view) {
        final  AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Estas seguro que quieres salir?")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        UserInfo.this.finish();
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
}
