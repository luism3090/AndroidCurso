package com.example.chistesgratisparawhastapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.DoubleBuffer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    ProgressDialog dialog;
    TTSManager ttsManager = null;

    private TextToSpeech tts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //LinearLayout layout_chistes = (LinearLayout)findViewById(R.id.layout_chistes);
        //LinearLayout layout_chistes = (LinearLayout)findViewById(R.id.layout_chistes);

        mostrarAlertaEspera();
        obtenerChistes("https://practicaproductos.000webhostapp.com/chistesgratiswhatsApp/obtener_chistes.php");


    }

    private void obtenerChistes(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                LinearLayout layout_chistes = (LinearLayout)findViewById(R.id.layout_chistes);
                ttsManager = new TTSManager();
                ttsManager.init(getApplicationContext());

                //ConstraintLayout layout_principal = (ConstraintLayout)findViewById(R.id.layout_principal);
                //LinearLayout layout_acciones_chiste = (LinearLayout)findViewById(R.id.layout_acciones_chiste);



                ocultarAlertaEspera();
                try {

                    JSONObject responseJSON = new JSONObject(response);

                    String mensaje = responseJSON.getString("mensaje");
                    String error = responseJSON.getString("error");
                    String resultado = responseJSON.getString("resultado");

                    if (resultado.equals("OK")) {

                        JSONArray datosChistesArray = responseJSON.getJSONArray("mensaje");

                        for (int i = 0; i < datosChistesArray.length(); i++) {

                            JSONObject chistesArray = datosChistesArray.getJSONObject(i);
                            String chiste = chistesArray.getString("chiste");


                            // --------------------------------------- Creando el espacio entre chistes ---------------------------------

                            Space espacioEntreChiste = new Space(getApplicationContext());
                            //Space espacioEntreChiste = new Space((Context) context);
                            espacioEntreChiste.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            espacioEntreChiste.setMinimumHeight(150);
                            layout_chistes.addView(espacioEntreChiste);

                            // --------------------------------- Creando en Text View para colocar el texto del chiste ---------------------------------

                            TextView textViewChiste = new TextView(getApplicationContext());
                            textViewChiste.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            textViewChiste.setText(chiste);
                            textViewChiste.setBackgroundColor(Color.rgb(0,0,0));
                            textViewChiste.setTextColor(Color.rgb(255,255,255));
                            textViewChiste.setMinHeight(700);
                            textViewChiste.setGravity(Gravity.CENTER);
                            textViewChiste.setTextSize(24);
                            textViewChiste.setPadding(30,0,30,0);
                            textViewChiste.setId(i);
                            layout_chistes.addView(textViewChiste);
                            textViewChiste.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Toast.makeText(getApplicationContext(),String.valueOf(view.getId()+" Texto"),Toast.LENGTH_SHORT).show();
                                }
                            });



                            // --------------------------------- Creando un table layout como contenedor para colocar los botones de redes sociales ---------------------------------

                            LinearLayout contenedor = new LinearLayout(getApplicationContext());
                            contenedor.setLayoutParams(new LinearLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                            contenedor.setOrientation(LinearLayout.HORIZONTAL);
                            contenedor.setPadding(0,-30,0,0);
                            contenedor.setGravity(Gravity.LEFT);
                            layout_chistes.addView(contenedor);


                            // --------------------------------- Creando el boton de Whatsapp -------------------------------------

                            ImageButton botonWhastapp = new ImageButton(getApplicationContext());
                            //botonWhastapp.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            botonWhastapp.setLayoutParams(new ActionBar.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                            botonWhastapp.setImageResource(R.mipmap.icono_whatsapp);
                            botonWhastapp.setBackgroundColor(Color.TRANSPARENT);
                            botonWhastapp.setPadding(0,26,0,0);
                            botonWhastapp.setId(i);
                            //botonWhastapp.setMinimumWidth(50);
                            contenedor.addView(botonWhastapp);
                            //layout_chistes.addView(botonWhastapp);
                            //rel_layout_acciones.addView(botonWhastapp);
                            botonWhastapp.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    TextView textViewChiste = (TextView) findViewById(view.getId());
                                    String textoChiste = textViewChiste.getText().toString();

                                    Intent sendIntent = new Intent();
                                    sendIntent.setAction(Intent.ACTION_SEND);
                                    sendIntent.putExtra(Intent.EXTRA_TEXT, String.valueOf(textoChiste));
                                    sendIntent.setType("text/plain");
                                    sendIntent.setPackage("com.whatsapp");
                                    try {
                                        startActivity(sendIntent);
                                    }
                                    catch (android.content.ActivityNotFoundException ex) {
                                        Toast.makeText(getApplicationContext(),"Por favor instala WhatsApp", Toast.LENGTH_LONG).show();
                                    }


                                }
                            });


                            // --------------------------------- Creando el boton de Facebook ---------------------------------

                            ImageButton botonFacebook = new ImageButton(getApplicationContext());
                            //botonFacebook.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            botonFacebook.setLayoutParams(new ActionBar.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                            botonFacebook.setImageResource(R.mipmap.icono_facebook);
                            botonFacebook.setBackgroundColor(Color.TRANSPARENT);
                            botonFacebook.setPadding(12,25,0,0);
                            //botonFacebook.setMaxHeight(55);
                            botonFacebook.setId(i);
                            contenedor.addView(botonFacebook);
                            //layout_chistes.addView(botonFacebook);
                            //rel_layout_acciones.addView(botonFacebook);
                            botonFacebook.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    TextView textViewChiste = (TextView) findViewById(view.getId());
                                    String textoChiste = textViewChiste.getText().toString();

                                    Intent sendIntent = new Intent();
                                    sendIntent.setAction(Intent.ACTION_SEND);
                                    sendIntent.putExtra(Intent.EXTRA_TEXT, String.valueOf(textoChiste));
                                    sendIntent.setType("text/plain");
                                    //sendIntent.setPackage("com.facebook.katana");
                                    sendIntent.setPackage("com.facebook.orca");
                                    try {
                                        startActivity(sendIntent);
                                    }
                                    catch (android.content.ActivityNotFoundException ex) {
                                        Toast.makeText(getApplicationContext(),"Por favor instala Facebook Messenger", Toast.LENGTH_LONG).show();
                                    }

                                }
                            });



                            ImageButton botonCopiar = new ImageButton(getApplicationContext());
                            botonCopiar.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            botonCopiar.setImageResource(R.mipmap.icono_copiar);
                            botonCopiar.setBackgroundColor(Color.TRANSPARENT);
                            botonCopiar.setPadding(22,32,0,0);
                            botonCopiar.setId(i);
                            contenedor.addView(botonCopiar);
                            botonCopiar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    TextView textViewChiste = (TextView) findViewById(view.getId());
                                    String textoChiste = textViewChiste.getText().toString();

                                    ClipboardManager copiarTexto = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                    ClipData clip = ClipData.newPlainText("text",  textoChiste);
                                    copiarTexto.setPrimaryClip(clip);

                                    Toast.makeText(getApplicationContext(),"El texto del chiste se ha copiado",Toast.LENGTH_SHORT).show();
                                }
                            });



                            ImageButton botonCompartir = new ImageButton(getApplicationContext());
                            botonCompartir.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            botonCompartir.setImageResource(R.mipmap.icono_compartir);
                            botonCompartir.setBackgroundColor(Color.TRANSPARENT);
                            botonCompartir.setPadding(30,32,0,0);
                            botonCompartir.setId(i);
                            contenedor.addView(botonCompartir);
                            botonCompartir.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    TextView textViewChiste = (TextView) findViewById(view.getId());
                                    String textoChiste = textViewChiste.getText().toString();

                                    Intent sendIntent = new Intent();
                                    sendIntent.setAction(Intent.ACTION_SEND);
                                    sendIntent.putExtra(Intent.EXTRA_TEXT, String.valueOf(textoChiste));
                                    sendIntent.setType("text/plain");
                                    startActivity(sendIntent);
                                }
                            });


                            /*
                            ImageButton botonCorazonFavoritos = new ImageButton(getApplicationContext());
                            botonCorazonFavoritos.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                            botonCorazonFavoritos.setImageResource(R.mipmap.icono_corazon_favoritos);
                            botonCorazonFavoritos.setBackgroundColor(Color.TRANSPARENT);
                            botonCorazonFavoritos.setPadding(38,26,0,0);
                            botonCorazonFavoritos.setId(i);
                            botonCorazonFavoritos.setVisibility(View.INVISIBLE);
                            contenedor.addView(botonCorazonFavoritos);
                            botonCorazonFavoritos.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    TextView textViewChiste = (TextView) findViewById(view.getId());
                                    String textoChiste = textViewChiste.getText().toString();

                                    view.setVisibility(View.INVISIBLE);
                                    ImageButton botonCorazon = (ImageButton) findViewById(view.getId());
                                    botonCorazon.setVisibility(View.VISIBLE);

                                }
                            });

                            */




                            ImageButton botonCorazon = new ImageButton(getApplicationContext());
                            botonCorazon.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                            botonCorazon.setImageResource(R.mipmap.icono_corazon);
                            botonCorazon.setBackgroundColor(Color.TRANSPARENT);
                            botonCorazon.setPadding(38,26,0,0);
                            botonCorazon.setId(i);
                            contenedor.addView(botonCorazon);

                            botonCorazon.setOnClickListener(new View.OnClickListener() {
                                @Override

                                public void onClick(View view) {
                                    TextView textViewChiste = (TextView) findViewById(view.getId());
                                    String textoChiste = textViewChiste.getText().toString();

                                    view.setBackgroundColor(Color.RED);


                                }
                            });




                            ImageButton botonAudio = new ImageButton(getApplicationContext());
                            botonAudio.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                            botonAudio.setImageResource(R.mipmap.icono_altavoz2);
                            botonAudio.setBackgroundColor(Color.TRANSPARENT);
                            botonAudio.setPadding(43,26,0,0);
                            botonAudio.setId(i);
                            contenedor.addView(botonAudio);
                            botonAudio.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    TextView textViewChiste = (TextView) findViewById(view.getId());
                                    String textoChiste = textViewChiste.getText().toString();

                                    ttsManager.initQueue(String.valueOf(textoChiste));

                                }

                            });



                        }

                    }else{

                        Modals nuevaModal = new Modals("Mensaje", error, mensaje, MainActivity.this);
                        nuevaModal.createModal();

                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "cayo en el catch", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();

                parametros.put("","");

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



    private void mostrarAlertaEspera(){
        dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Espere por favor...");
        dialog.setCancelable(false);
        dialog.show();
    }

    private void ocultarAlertaEspera(){
        if (dialog.isShowing())
            dialog.dismiss();
    }

    public static int generateViewId() {
        for (; ; ) {
            final int result = sNextGeneratedId.get();

            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.

            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }


}
