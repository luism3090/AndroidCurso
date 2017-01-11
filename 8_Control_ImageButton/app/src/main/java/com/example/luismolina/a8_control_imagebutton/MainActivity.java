package com.example.luismolina.a8_control_imagebutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageButton imgbLlamar;
    private TextView tvInformacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgbLlamar = (ImageButton)findViewById(R.id.imgbLlamar);
        tvInformacion = (TextView)findViewById(R.id.tvInformacion);

        imgbLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvInformacion.setText("Llamando");
            }
        });

    }
}
