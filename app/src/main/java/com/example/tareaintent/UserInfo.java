package com.example.tareaintent;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.CollationElementIterator;

public class UserInfo extends AppCompatActivity {
    RadioGroup rbgGender;
    RadioButton rbGender, rbMen, rbWomen;
    EditText txtName;
    ImageView imgContinue, imgBack, imgExit, imageGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        appComponents();
    }
    public void appComponents(){
        txtName = findViewById(R.id.txtName);
        rbgGender = findViewById(R.id.rbgGender);
        rbWomen = findViewById(R.id.rbMujer);
        rbMen = findViewById(R.id.rbHombre);
        imgContinue = findViewById(R.id.imgContinue);
        imgBack = findViewById(R.id.imgBack);
        imgExit = findViewById(R.id.imgExitApp);
        imageGender = findViewById(R.id.imageGender);
    }
    public void changeGenderColorMen(View view) {
       imageGender.setImageResource(R.drawable.mengender);
    }
    public void changeGenderColorWomen(View view) {
        imageGender.setImageResource(R.drawable.womengender);
    }
    public void goToActivityImages(View view) {
        String nombre = txtName.getText().toString();



        if ((nombre.length() >= 1 && rbMen.isChecked()) || (nombre.length() >= 1 && rbWomen.isChecked())){
            if (nombre.length() <= 11) {
                int gender = rbgGender.getCheckedRadioButtonId();
                rbGender = findViewById(gender);
                String genderSelected = rbGender.getText().toString();
                Intent intent = new Intent(this, Images.class);
                intent.putExtra("gender",genderSelected);
                intent.putExtra("nombre",nombre);
                startActivity(intent);
            }else{
                Toast.makeText(this, "El nombre no puede contener mas de 11 caracteres",
                        Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Complete todos los campos",
                    Toast.LENGTH_LONG).show();
        }
    }
    public void goToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void ExitAppFromUserInfo(View view) {
        final  AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Esta seguro que quieres salir?")
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
