package com.example.luismolina.a29_capturar_audio_clase_mediarecorder;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity   implements MediaPlayer.OnCompletionListener{

    Button btnGrabar, btnDetener, btnReproducir;
    TextView tvEstado;
    MediaRecorder recorder;
    MediaPlayer player;
    File archivo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGrabar =  (Button)findViewById(R.id.btnGrabar);
        btnDetener =  (Button)findViewById(R.id.btnDetener);
        btnReproducir =  (Button)findViewById(R.id.btnReproducir);
        tvEstado =  (TextView)findViewById(R.id.tvEstado);

        btnDetener.setEnabled(false);
        btnReproducir.setEnabled(false);

        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                recorder = new MediaRecorder();
                recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

                File path = new File(Environment.getExternalStorageDirectory().getPath());
                try
                {
                    archivo = File.createTempFile("temporal",".3gp",path);
                }
                catch (IOException e) {}

                recorder.setOutputFile(archivo.getAbsolutePath());

                try
                {
                    recorder.prepare();
                }
                catch (IOException e) {}
                recorder.start();
                tvEstado.setText("Grabando");
                btnGrabar.setEnabled(false);
                btnDetener.setEnabled(true);
                btnReproducir.setEnabled(false);

            }
        });

        btnDetener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    recorder.stop();
                    recorder.release();
                    player = new MediaPlayer();
                    player.setOnCompletionListener( MainActivity.this);
                    try
                    {
                        player.setDataSource(archivo.getAbsolutePath());
                    }
                    catch (IOException e) {
                    }

                    try {
                        player.prepare();
                    } catch (IOException e) {
                    }
                    btnGrabar.setEnabled(true);
                    btnDetener.setEnabled(false);
                    btnReproducir.setEnabled(true);
                    tvEstado.setText("Listo para reproducir");



                
            }
        });

        btnReproducir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                player.start();
                btnGrabar.setEnabled(false);
                btnReproducir.setEnabled(false);
                btnDetener.setEnabled(false);
                tvEstado.setText("Reproduciendo");



            }
        });


    }


    public void onCompletion(MediaPlayer mp) {
        btnGrabar.setEnabled(true);
        btnDetener.setEnabled(false);
        btnReproducir.setEnabled(true);
        tvEstado.setText("Listo");
    }

}
