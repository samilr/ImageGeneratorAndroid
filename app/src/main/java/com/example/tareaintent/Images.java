package com.example.tareaintent;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.io.ByteArrayOutputStream;
import java.util.Random;

public class Images extends AppCompatActivity {
    ImageView imgBack, imgExit;
    TextView tvName;
    ImageView imgV, imgShare;
    FrameLayout flSuperacion, flPhrases;
    String gender;
    int[] menPhrases, womenPhrases, menRefletion, womenRefletion;
    int imgSelected;
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        component();
        getInfoFromUserInfo();
        imgShare.setVisibility(View.INVISIBLE);
        imgV.setImageResource(R.drawable.predeterminada);

        menPhrases = new int[]{R.drawable.sh1, R.drawable.sh2, R.drawable.sh3, R.drawable.sh4, R.drawable.sh5, R.drawable.sh6, R.drawable.sh7, R.drawable.sh8, R.drawable.sh9, R.drawable.sh10};
        womenPhrases = new int[]{R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5, R.drawable.s6, R.drawable.s7, R.drawable.s8, R.drawable.s9, R.drawable.s10};
        menRefletion = new int[]{R.drawable.rh1, R.drawable.rh2, R.drawable.rh3, R.drawable.rh4,R.drawable.rh5,R.drawable.rh6, R.drawable.rh7,R.drawable.rh8,R.drawable.rh9, R.drawable.rh10};
        womenRefletion = new int[]{R.drawable.r1, R.drawable.r2, R.drawable.r3, R.drawable.r11,R.drawable.r5,R.drawable.r6, R.drawable.r7,R.drawable.r8,R.drawable.r9, R.drawable.r10};
    }

    public void component(){
        tvName = findViewById(R.id.tvName);
        imgV = findViewById(R.id.imgIMG);
        imgBack = findViewById(R.id.imgBack);
        imgExit = findViewById(R.id.imgExitApp);

        imgShare = findViewById(R.id.imgShare);
        flSuperacion = findViewById(R.id.flSuperacion);
        flPhrases = findViewById(R.id.flPhrases);
    }

    public void getInfoFromUserInfo(){
        Bundle getName = getIntent().getExtras();
        tvName.setText("Hola, "+ getName.getString("nombre"));

        Bundle getGender = getIntent().getExtras();
        gender = getGender.getString("gender");
    }

    public void shareImages(View view) {
        Bitmap b = BitmapFactory.decodeResource(getResources(),imgSelected);
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), b, "Frases para tu vida   ", null);
        Uri imageUri =  Uri.parse(path);
        share.putExtra(Intent.EXTRA_STREAM, imageUri);
        startActivity(Intent.createChooser(share, "Select"));
    }

    public void goToInfoActivityFromImg(View view) {
        Intent intent = new Intent(this, UserInfo.class);
        startActivity(intent);
    }

    public void ShowPersonalPhrases(View view){
        imgShare.setVisibility(View.VISIBLE);
        switch (gender) {
            case "Hombre":
                imgSelected = menPhrases[rand.nextInt(menPhrases.length)];
                imgV.setImageResource(imgSelected);
                break;
            case "Mujer":
                imgSelected = womenPhrases[rand.nextInt(womenPhrases.length)];
                imgV.setImageResource(imgSelected);
                break;
            default:
                imgV.setImageResource(R.drawable.sh1);
        }
    }

    public void ShowLifeReflection(View view){
        imgShare.setVisibility(View.VISIBLE);
        switch (gender) {
            case "Hombre":
                imgSelected = menRefletion[rand.nextInt(menRefletion.length)];
                imgV.setImageResource(imgSelected);
                break;
            case "Mujer":
                imgSelected = womenRefletion[rand.nextInt(womenRefletion.length)];
                imgV.setImageResource(imgSelected);
                break;
            default:
                imgV.setImageResource(R.drawable.sh1);
        }
    }

    public void ExitAppFromUserImg(View view) {        final  AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Estas seguro que quieres salir?")
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
