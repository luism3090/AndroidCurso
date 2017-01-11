package com.example.luismolina.a25_reproduccion_audio_archivo_tarjeta_sd;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnReproducir;
    TextView tv1,tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReproducir = (Button)findViewById(R.id.btnReproducir);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);

        tv1.setVisibility(View.INVISIBLE);
        tv2.setVisibility(View.INVISIBLE);

        btnReproducir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri datos = Uri.parse(Environment.getExternalStorageDirectory().getPath()+"/stressed.mp3");
                //tv1.setText(String.valueOf(datos));
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(),datos);
                //tv2.setText(String.valueOf(mp));
                mp.start();

            }
        });

    }
}
