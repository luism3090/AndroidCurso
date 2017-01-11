package com.example.luismolina.a31_pintar_fondo_dibujar_lineas;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout layout1 = (RelativeLayout)findViewById(R.id.layout1);
        Lienzo fondo = new Lienzo(this);
        layout1.addView(fondo);

    }

    class Lienzo extends View
    {

        public Lienzo (Context context)
        {
            super(context);
            Log.d("context",String.valueOf(context));
        }

        protected void onDraw(Canvas canvas)
        {
            canvas.drawRGB(255,255,0);

            int ancho = canvas.getWidth();

            Paint pincel1 = new Paint();

            Log.d("canvas",String.valueOf(canvas));
            Log.d("ancho",String.valueOf(ancho));
            Log.d("Paint",String.valueOf(pincel1));


            pincel1.setARGB(255,0,0,0);
            canvas.drawLine(0,30,ancho,30,pincel1);
            pincel1.setStrokeWidth(4);
            canvas.drawLine(0,60,ancho,60,pincel1);
        }

    }

}
