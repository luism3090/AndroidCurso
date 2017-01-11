package com.example.luismolina.practica3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class FirstActivity extends AppCompatActivity {

    TextView tv3;
    ImageView imagen1,imagen2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_first);

        tv3 = (TextView)findViewById(R.id.tv3);
        imagen1 = (ImageView)findViewById(R.id.img1);
        imagen2 = (ImageView)findViewById(R.id.img2);

        //Toast.makeText(this,String.valueOf(savedInstanceState),Toast.LENGTH_SHORT).show();

        if(savedInstanceState!=null) {
            tv3.setText(String.valueOf(savedInstanceState));
        }


        Picasso.with(this).load("http://www.desarrolloweb.com/archivoimg/general/2824.jpg").into(imagen1);

        //imagen1.setImageResource(R.drawable.ic_account_box);
        imagen2.setImageResource(R.drawable.foto);


    }
}
