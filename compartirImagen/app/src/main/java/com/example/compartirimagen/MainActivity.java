package com.example.compartirimagen;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {


    TextView tv_hola;
    LinearLayout miLayout;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv_hola = (TextView)findViewById(R.id.tv_hola);

        LinearLayout miLayout = (LinearLayout)findViewById(R.id.miLayout);


/***** COMPARTIR IMAGEN *****/


        //String algo = String.valueOf(icono.getTag());

        //String url = String.valueOf(Uri.parse("android.resource://" + getPackageName()+ "/mimap/"+R.mipmap.ic_launcher));

       // Modals nuevaModal = new Modals("Mensaje",algo+"____"+url , "Ok", MainActivity.this);
        //nuevaModal.createModal();

    }



    @SuppressLint("ResourceType")
    public void compartirImagen(View view){

        TextView tv_hola = (TextView)findViewById(R.id.tv_hola);
        LinearLayout miLayout = (LinearLayout)findViewById(R.id.miLayout);


        tv_hola.setDrawingCacheEnabled(true);
        tv_hola.buildDrawingCache();  // Creando un Bitmap del Texview el chiste

        //Bitmap b1 = tv_hola.getDrawingCache();

        //imagen.setImageBitmap(imagen.getDrawable());

        ImageView imagenAcompartir = new ImageView(getApplicationContext());   // crear la imagen desde codigo
        imagenAcompartir.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)); // crear la imagen desde codigo
        imagenAcompartir.setId(5000);
        imagenAcompartir.setTag("a");
        imagenAcompartir.setImageBitmap(tv_hola.getDrawingCache());

        //imagenAcompartir.setImageBitmap(imagenAcompartir);

        //miLayout.addView(imagenAcompartir);

        Uri url = saveImageExternal(tv_hola.getDrawingCache());

       // Modals nuevaModal = new Modals("Mensaje",String.valueOf(url) , "Ok", MainActivity.this);
       // nuevaModal.createModal();


        if(Build.VERSION.SDK_INT>=24){
            try{
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
                Uri.fromFile(new File(String.valueOf(url)));
            }catch(Exception e){
                e.printStackTrace();
            }
        }


        Intent sendIntent1 = new Intent();
        sendIntent1.setAction(Intent.ACTION_SEND);
        //Uri phototUri = Uri.parse(imageUri);
        sendIntent1.setData(url);
        sendIntent1.setType("image/*");
        sendIntent1.putExtra(Intent.EXTRA_STREAM, url);
        //sendIntent1.putExtra(Intent.EXTRA_STREAM, getResources().getIdentifier("com.my.app:drawable/"+parts[1], null, null));


        try {
            startActivity(sendIntent1);
        }
        catch (ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(),"Ocurrió un problema al compartir la imagen", Toast.LENGTH_LONG).show();
        }



    }


    private Uri saveImageExternal(Bitmap image) {
        //TODO - Should be processed in another thread

        Uri uri = null;
        try {
            File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "imagencompartir.png");
            FileOutputStream stream = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.PNG, 90, stream);
            stream.close();
            uri = Uri.fromFile(file);
        } catch (IOException e) {
            //Log.d(TAG, "IOException while trying to write file for sharing: " + e.getMessage());
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
        return uri;
    }
    
}
