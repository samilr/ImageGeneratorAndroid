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
    TextView tvName, tvFrases;
    ImageView imgV, imgShare;
    FrameLayout flSuperacion, flPhrases;
    String gender, selectionCategory, txtSelected;
    int[] menPhrases, womenPhrases;
    String [] menRefletion, womenRefletion;

    int imgSelected;
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        component();
        getInfoFromUserInfo();
        imgShare.setVisibility(View.INVISIBLE);
        tvFrases.setVisibility(View.INVISIBLE);
        imgV.setImageResource(R.drawable.predeterminada);

        menPhrases = new int[]{R.drawable.sh1, R.drawable.sh2, R.drawable.sh3, R.drawable.sh4, R.drawable.sh5, R.drawable.sh6, R.drawable.sh7, R.drawable.sh8, R.drawable.sh9, R.drawable.sh10};
        womenPhrases = new int[]{R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5, R.drawable.s6, R.drawable.s7, R.drawable.s8, R.drawable.s9, R.drawable.s10};
        menRefletion = new String[]{"¿Sabes cual es el hombre perfecto? \n No es aquel que tiene musculos, ni dinero, ni carro. Si no aquel que hace todo lo posible por 'Verte sonreir'.",
                                    "Es una reflexion penosa para un hombre considerar lo que ha hecho, comparado con lo que debio hacer.",
                                    "Aveces lo mas dificil y lo correcto son la misma cosa.",
                                    "Ellos se rien de mi por ser diferente, yo me rio de todos por ser iguales.",
                                    "Los hombres mas arrogantes son los que generalmente estan equivocados, otorgan toda la pasion a sus puntos de vista sin una apropiada reflexion.",
                                    "Es su naturaleza, no su posicion, lo que hace al hombre bueno.",
                                    "Nuestro peor problema de comunicacion es que no escuchamos parr entender, si no que escuchamos para contestar.",
                                    "Si caminas solo iras mas rapido, si caminas acompañado llegaras mas lejos.",
                                    "Caerse mil veces y levantarse de nuevo, en eso consiste la vida."};

        womenRefletion = new String[]{"No es de quien te dice princesa, si no de quien te trata como una.",
                                        "En el proceso de la violencia es mas importante que recuerdes que los malos maltratos cada vez seran mas frecuentes, mas intentos y peligrosos.",
                                        "Las mujeres son la mitad de la sociedad que da a luz a la otra mitad, por lo que es comos si fueran la sociedad entera.",
                                        "La mujer es mucho mas que un genero, esta llena de desafios.",
                                        "Las mujeres inteligentes y fuertes no usan la venganza, solo esperan a que el tiempo pase para ver aquellos que le han hecho daño.",
                                        "Amiga, que brilles hoy mas que ayer, con el resplandor de tus buenas acciones.",
                                        "Bendita las mujeres que el diario caminar hacen de un grito de libertad. De amor y esperanza."};
    }

    public void component(){
        tvName = findViewById(R.id.tvName);
        tvFrases = findViewById(R.id.tvFrases);
        flPhrases = findViewById(R.id.flPhrases);
        imgV = findViewById(R.id.imgIMG);
        imgBack = findViewById(R.id.imgBack);
        imgExit = findViewById(R.id.imgExitApp);
        imgShare = findViewById(R.id.imgShare);
        flSuperacion = findViewById(R.id.flSuperacion);
    }

    public void getInfoFromUserInfo(){
        Bundle getName = getIntent().getExtras();
        tvName.setText(getName.getString("nombre"));

        Bundle getGender = getIntent().getExtras();
        gender = getGender.getString("gender");
    }

    public void shareImages(View view) {
        switch (selectionCategory) {
            case "superacion":
                Bitmap b = BitmapFactory.decodeResource(getResources(),imgSelected);
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/jpeg");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                String path = MediaStore.Images.Media.insertImage(getContentResolver(), b, "Superacion", null);
                Uri imageUri =  Uri.parse(path);
                share.putExtra(Intent.EXTRA_STREAM, imageUri);
                startActivity(Intent.createChooser(share, "Select"));
                break;

            case "reflexion":
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, txtSelected+"\n\n -Share from SG APP");
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                break;
            default:
                imgV.setImageResource(R.drawable.sh1);
        }
    }

    public void goToInfoActivityFromImg(View view) {
        Intent intent = new Intent(this, UserInfo.class);
        startActivity(intent);
    }

    public void ShowPersonalPhrases(View view){
        selectionCategory = "superacion";

        tvFrases.setVisibility(View.INVISIBLE);
        imgV.setVisibility(View.VISIBLE);
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
        selectionCategory = "reflexion";

        imgV.setVisibility(View.INVISIBLE);
        tvFrases.setVisibility(View.VISIBLE);
        imgShare.setVisibility(View.VISIBLE);
        switch (gender) {
            case "Hombre":
                txtSelected =  menRefletion[rand.nextInt(menRefletion.length)];
                tvFrases.setText(txtSelected);
                break;
            case "Mujer":
                txtSelected = womenRefletion[rand.nextInt(womenRefletion.length)];
                tvFrases.setText(txtSelected);
                break;
            default:
                imgV.setImageResource(R.drawable.sh1);
        }
    }

    public void ExitAppFromUserImg(View view) {
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
