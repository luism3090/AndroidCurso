package com.luisame.a12_un_segundo_activity_pasar_parametros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class Actividad2 extends AppCompatActivity {

    private WebView webView1;
    private Button btnFinalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);

        webView1 = (WebView)findViewById(R.id.webView1);
        btnFinalizar = (Button)findViewById(R.id.btnFinalizar);

        Bundle bundle = getIntent().getExtras();
        String dato = bundle.getString("direccion");

        webView1.loadUrl("http://"+dato);

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
