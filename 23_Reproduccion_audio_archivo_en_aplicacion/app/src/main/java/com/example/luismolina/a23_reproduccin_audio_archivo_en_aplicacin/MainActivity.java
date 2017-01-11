package com.example.luismolina.a23_reproduccin_audio_archivo_en_aplicacin;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public Button btnRude,btnStressedOut;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRude = (Button)findViewById(R.id.btnRude);
        btnStressedOut = (Button)findViewById(R.id.btnStressedOut);


        btnRude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(),R.raw.rude);
                mp.start();
            }
        });

        btnStressedOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(),R.raw.stressed);
                mp.start();

            }
        });

    }
}
