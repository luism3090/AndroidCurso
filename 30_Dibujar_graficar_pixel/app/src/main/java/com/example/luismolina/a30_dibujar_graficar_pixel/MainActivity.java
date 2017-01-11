package com.example.luismolina.a30_dibujar_graficar_pixel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout layout1 = (RelativeLayout)findViewById(R.id.layout1);
        Lienzo fondo = new Lienzo(this);
        layout1.addView(fondo);


    }

    class Lienzo extends View
    {
        public Lienzo(Context context)
        {
            super(context);
        }

        protected  void onDraw(Canvas canvas)
        {
            int ancho = canvas.getWidth();
            int alto = canvas.getHeight();
            Paint pincel1 = new Paint();
            pincel1.setARGB(255,0,0,0);
            canvas.drawPoint(ancho/2,alto/2,pincel1);
        }

    }



}
