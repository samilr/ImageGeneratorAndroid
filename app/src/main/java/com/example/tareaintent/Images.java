package com.example.tareaintent;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.ByteArrayOutputStream;
import java.util.Random;

public class Images extends AppCompatActivity {
    ImageView imgBack, imgExit;
    TextView tvName, tvFrases, tvGender;
    ImageView imgV, imgShare;
    FrameLayout flName, flSuperacion, flPhrases, flMenu, flGender;
    String gender, selectionCategory, txtSelected, user;
    String [] menRefletion, womenRefletion;
    int[] menPhrases, womenPhrases;
    int imgSelected;
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        appComponents();
        getInfoFromUserInfo();
        colorByGender();
        dataPersonalSuperation();
        tvGender.setText(gender);
    }


    public void colorByGender(){
        Window window = this.getWindow();
        switch (gender) {
            case "Hombre":
                flName.setBackgroundColor(getResources().getColor(R.color.blue));
                flSuperacion.setBackgroundColor(getResources().getColor(R.color.blue));
                flPhrases.setBackgroundColor(getResources().getColor(R.color.blue));
                flMenu.setBackgroundColor(getResources().getColor(R.color.blue));
                flGender.setBackgroundColor(getResources().getColor(R.color.blue));

                imgV.setImageResource(R.drawable.predeterminadamen);
                imgShare.setImageResource(R.drawable.sharemen);
                tvFrases.setTextColor(getResources().getColor(R.color.blue));

                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(this.getResources().getColor(R.color.blue));

                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
                break;
            case "Mujer":
                flName.setBackgroundColor(getResources().getColor(R.color.pink));
                flSuperacion.setBackgroundColor(getResources().getColor(R.color.pink));
                flPhrases.setBackgroundColor(getResources().getColor(R.color.pink));
                flMenu.setBackgroundColor(getResources().getColor(R.color.pink));
                flGender.setBackgroundColor(getResources().getColor(R.color.pink));

                imgV.setImageResource(R.drawable.predeterminadawomen);
                imgShare.setImageResource(R.drawable.sharewomen);
                tvFrases.setTextColor(getResources().getColor(R.color.pink));

                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(this.getResources().getColor(R.color.pink));

                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.pink)));
                break;
            default:

        }
    }
    public void appComponents(){
        tvName = findViewById(R.id.tvName);
        tvFrases = findViewById(R.id.tvFrases);
        tvGender = findViewById(R.id.tvGender);
        imgV = findViewById(R.id.imgIMG);
        imgBack = findViewById(R.id.imgBack);
        imgExit = findViewById(R.id.imgExitApp);
        imgShare = findViewById(R.id.imgShare);
        flPhrases = findViewById(R.id.flPhrases);
        flSuperacion = findViewById(R.id.flSuperacion);
        flName = findViewById(R.id.flName);
        flMenu = findViewById(R.id.flMenu);
        flGender = findViewById(R.id.flGender);


        menPhrases = new int[]{R.drawable.sh1, R.drawable.sh2, R.drawable.sh3, R.drawable.sh4, R.drawable.sh5, R.drawable.sh6, R.drawable.sh7, R.drawable.sh8, R.drawable.sh9, R.drawable.sh10};
        womenPhrases = new int[]{R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5, R.drawable.s6, R.drawable.s7, R.drawable.s8, R.drawable.s9, R.drawable.s10};

        imgShare.setVisibility(View.INVISIBLE);
        tvFrases.setVisibility(View.INVISIBLE);
        imgV.setImageResource(R.drawable.predeterminada);
    }
    public void getInfoFromUserInfo(){
        Bundle getName = getIntent().getExtras();
        user = getName.getString("nombre");
        tvName.setText("Hola " + user);

        Bundle getGender = getIntent().getExtras();
        gender = getGender.getString("gender");
    }
    public void shareImages(View view) {
        Intent share = new Intent(Intent.ACTION_SEND);
        switch (selectionCategory) {
            case "superacion":
                Bitmap b = BitmapFactory.decodeResource(getResources(),imgSelected);
                share.setType("image/jpeg");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                String path = MediaStore.Images.Media.insertImage(getContentResolver(), b, "Superacion", null);
                Uri imageUri =  Uri.parse(path);
                share.putExtra(Intent.EXTRA_STREAM, imageUri);
                startActivity(Intent.createChooser(share, "Select"));
                break;

            case "reflexion":
                share.putExtra(Intent.EXTRA_TEXT,  "¡Hola! "+user+" quiere compartir esta reflexion contigo.        \n\n" + txtSelected+"\n\n -Share from SG APP");
                share.setType("text/plain");
                Intent shareIntent = Intent.createChooser(share, null);
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
    public void dataPersonalSuperation(){
        menRefletion = new String[]{"¿Sabes cual es el hombre perfecto? \n No es aquel que tiene musculos, ni dinero, ni carro. Si no aquel que hace todo lo posible por 'Verte sonreir'.",
                "Es una reflexion penosa para un hombre considerar lo que ha hecho, comparado con lo que debio hacer.",
                "Aveces lo mas dificil y lo correcto son la misma cosa.",
                "Ellos se rien de mi por ser diferente, yo me rio de todos por ser iguales.",
                "Los hombres mas arrogantes son los que generalmente estan equivocados, otorgan toda la pasion a sus puntos de vista sin una apropiada reflexion.",
                "Es su naturaleza, no su posicion, lo que hace al hombre bueno.",
                "Nuestro peor problema de comunicacion es que no escuchamos para entender, si no que escuchamos para contestar.",
                "Si caminas solo iras mas rapido, si caminas acompañado llegaras mas lejos.",
                "Caerse mil veces y levantarse de nuevo, en eso consiste la vida.",
                "Saber lo que hay que hacer elimina el miedo",
                "Nadie te puede hacer sentir inferior sin tu consentimiento",
                "Si la vida te pone obstáculos, tu reto es superarlo",
                "Las estrellas están ahí, sólo debes mirarlas",
                "Algunos buscan la felicidad, otros la crean",
                "Convierte siempre una situación negativa en una positiva",
                "El que no sabe lo que busca, no entiende lo que encuentra",
                "Si la vida te da un limón, haz limonada",
                "Cada proceso enseña algo que necesitamos aprender",
                "Donde una puerta se cierra, otra se abre",
                "Si quieres el arcoíris, tienes que aguantar la lluvia",
                "El poder no te es dado. Tienes que tomarlo",
                "El éxito es la capacidad de ir de fracaso en fracaso sin perder el entusiasmo",
                "Si estás atravesando momentos difíciles, sigue caminando. Lo malo es el momento, no eres tú",
                "El secreto para una vida exitosa es encontrar nuestro propósito y luego hacerlo",
                "La oscuridad no puede expulsar a la oscuridad: sólo la luz puede hacer eso. El odio no puede expulsar al odio: sólo el amor puede hacer eso",
                "El mundo que hemos creado es un proceso de nuestro pensamiento. No se puede cambiar sin cambiar nuestra forma de pensar",
                "Vive como si fueras a morir mañana, aprende como si fueras a vivir para siempre",
                "La realidad no es lo que nos sucede, sino lo que hacemos con lo que nos sucede",
                "Si exagerásemos nuestras alegrías, como hacemos con nuestras penas, nuestros problemas perderían importancia",
                "Muchas veces es mejor olvidar lo que uno siente, y recordar lo que uno vale",
                "Lo que la mente de un hombre puede concebir y puede creer, también lo puede lograr",
                "La gente puede fingir durante unos días, pero no durante toda una vida",
                "No tienes que controlar tus pensamientos; solo tienes que dejar de permitirles que te controlen a ti",
        };

        womenRefletion = new String[]{"No es de quien te dice princesa, si no de quien te trata como una.",
                "En el proceso de la violencia es mas importante que recuerdes que los malos maltratos cada vez seran mas frecuentes, mas intentos y peligrosos.",
                "Las mujeres son la mitad de la sociedad que da a luz a la otra mitad, por lo que es comos si fueran la sociedad entera.",
                "La mujer es mucho mas que un genero, esta llena de desafios.",
                "Las mujeres inteligentes y fuertes no usan la venganza, solo esperan a que el tiempo pase para ver aquellos que le han hecho daño.",
                "Amiga, que brilles hoy mas que ayer, con el resplandor de tus buenas acciones.",
                "Bendita las mujeres que el diario caminar hacen de un grito de libertad. De amor y esperanza."};
    }
}
