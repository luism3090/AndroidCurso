package com.example.webview_navegador_web;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Main2Activity extends AppCompatActivity {

    WebView wv_pagina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        wv_pagina = (WebView)findViewById(R.id.wv_pagina);

        String url = getIntent().getStringExtra("url");

        wv_pagina.setWebViewClient(new WebViewClient());
        wv_pagina.loadUrl(url);
        //wv_pagina.loadUrl("http://www.google.com");


    }

    public void Regresar(View v)
    {
        Intent regresar = new Intent(this,MainActivity.class);

        startActivity(regresar);
    }
}
