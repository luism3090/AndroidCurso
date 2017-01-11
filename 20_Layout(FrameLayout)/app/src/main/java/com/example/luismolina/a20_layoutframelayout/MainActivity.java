package com.example.luismolina.a20_layoutframelayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imgLlamar;
    private Button btnLlamar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgLlamar = (ImageView)findViewById(R.id.imgLLamar);
        btnLlamar = (Button) findViewById(R.id.btnLlamar);


        imgLlamar.setVisibility(View.INVISIBLE);

        btnLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLlamar.setVisibility(View.INVISIBLE);
                imgLlamar.setVisibility(View.VISIBLE);
            }
        });


    }
}
