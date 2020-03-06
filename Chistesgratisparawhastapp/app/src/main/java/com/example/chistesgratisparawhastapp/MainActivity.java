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
    SharedPreferences mipreferencia_user;
    SharedPreferences mipreferencia_user_favoritos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //LinearLayout layout_chistes = (LinearLayout)findViewById(R.id.layout_chistes);

        mipreferencia_user = getSharedPreferences("datos_usuario", Context.MODE_PRIVATE);
        String id_usuario = mipreferencia_user.getString("id_usuario","");
        //Toast.makeText(getApplicationContext(),id_usuario,Toast.LENGTH_LONG).show();

        mostrarAlertaEspera();
        if(id_usuario.equals("")){
            generarIdUsuario("https://practicaproductos.000webhostapp.com/chistesgratiswhatsApp/generar_id_usuario.php");
        }
        else{
            obtenerChistes("https://practicaproductos.000webhostapp.com/chistesgratiswhatsApp/obtener_chistes.php");
        }



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
                            String id_boton_favorito_rojo = chistesArray.getString("id_boton_favorito_rojo");
                            String id_boton_favorito_normal = chistesArray.getString("id_boton_favorito_normal");


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
                                    //Toast.makeText(getApplicationContext(),String.valueOf(view.getId()+" Texto"),Toast.LENGTH_SHORT).show();
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



                            ImageButton botonCorazonFavoritos = new ImageButton(getApplicationContext());
                            botonCorazonFavoritos.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                            botonCorazonFavoritos.setImageResource(R.mipmap.icono_corazon_favoritos);
                            botonCorazonFavoritos.setBackgroundColor(Color.TRANSPARENT);
                            botonCorazonFavoritos.setPadding(38,26,0,0);
                            botonCorazonFavoritos.setId(1000000+i+1);
                            if(id_boton_favorito_rojo.equals("null")){
                                botonCorazonFavoritos.setVisibility(View.GONE);
                            }
                            else{
                                botonCorazonFavoritos.setVisibility(View.VISIBLE);
                            }
                            contenedor.addView(botonCorazonFavoritos);
                            botonCorazonFavoritos.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {


                                    // OBTENIENDO EL ID DEL ELEMENTO QUE SE LE DIO CLICK Y OCULTARLO

                                    view.setVisibility(View.GONE);  // ocultando el elemento al que se le dio click
                                    int val = view.getId(); // obteniendo el id del elemento al que se le dio click


                                    // HACIENDO VISIBLE EL CORAZON SIN RELLENO

                                    int val2 = val + 1000000;  // obteniendo el id del boton de corazon sin relleno rojo
                                    ImageButton botonCorazonSinRelleno = (ImageButton) findViewById(val2);
                                    botonCorazonSinRelleno.setVisibility(View.VISIBLE);

                                    // OBTENIENDO EL ID DEL TEXVIEW DEL CHISTE PARA LLEVARLO A LA TABLA DE FAVORITOS
                                    int id_chiste = val - 1000000 - 1;
                                    //TextView textViewChiste = (TextView) findViewById(id_chiste);
                                    //String textoChiste = textViewChiste.getText().toString();
                                    //Toast.makeText(getApplicationContext(),textoChiste,Toast.LENGTH_LONG).show();

                                    eliminarChisteFavorito((id_chiste+1),mipreferencia_user.getString("id_usuario",""),view.getId(),val2,"https://practicaproductos.000webhostapp.com/chistesgratiswhatsApp/eliminar_chiste_favorito.php");

                                }
                            });


                            ImageButton botonCorazon = new ImageButton(getApplicationContext());
                            botonCorazon.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                            botonCorazon.setImageResource(R.mipmap.icono_corazon);
                            botonCorazon.setBackgroundColor(Color.TRANSPARENT);
                            botonCorazon.setPadding(38,26,0,0);
                            botonCorazon.setId(2000000+i+1);
                            if(id_boton_favorito_normal.equals("null")){
                                botonCorazon.setVisibility(View.VISIBLE);
                            }
                            else{
                                botonCorazon.setVisibility(View.GONE);
                            }
                            contenedor.addView(botonCorazon);
                            botonCorazon.setOnClickListener(new View.OnClickListener() {
                                @Override

                                public void onClick(View view) {

                                    // OBTENIENDO EL ID DEL ELEMENTO QUE SE LE DIO CLICK Y OCULTARLO
                                    view.setVisibility(View.GONE);
                                    int val = view.getId();

                                    //Toast.makeText(getApplicationContext(),String.valueOf(val2),Toast.LENGTH_SHORT).show();

                                    // VOLVIENDO VISIBLE EL ELEMENTO DE CORAZON ROJO PARA MOSTRARLO
                                    int val2 = val - 1000000;
                                    ImageButton botonCorazonRojo = (ImageButton) findViewById(val2);
                                    botonCorazonRojo.setVisibility(View.VISIBLE);

                                    // OBTENIENDO EL ID DEL TEXVIEW DEL CHISTE PARA LLEVARLO A LA TABLA DE FAVORITOS
                                    int id_chiste = val - 2000000 - 1;
                                    //TextView textViewChiste = (TextView) findViewById(id_chiste);
                                    //String textoChiste = textViewChiste.getText().toString();
                                    //Toast.makeText(getApplicationContext(),textoChiste,Toast.LENGTH_LONG).show();
                                    //Toast.makeText(getApplicationContext(),String.valueOf(id_chiste),Toast.LENGTH_LONG).show();

                                    guardarChisteFavorito((id_chiste+1),mipreferencia_user.getString("id_usuario",""),view.getId(),val2,"https://practicaproductos.000webhostapp.com/chistesgratiswhatsApp/guardar_chiste_favorito.php");

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

                        Modals nuevaModal = new Modals("Mensaje", mensaje, "Ok", MainActivity.this);
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

                String id_usuario = mipreferencia_user.getString("id_usuario","");

                parametros.put("id_usuario",id_usuario);

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

    private void generarIdUsuario(String url){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                //ocultarAlertaEspera();
                try {

                    JSONObject responseJSON = new JSONObject(response);

                    String mensaje = responseJSON.getString("mensaje");
                    String error = responseJSON.getString("error");
                    String resultado = responseJSON.getString("resultado");

                    if (resultado.equals("OK")) {

                        mipreferencia_user = getSharedPreferences("datos_usuario", Context.MODE_PRIVATE);
                        SharedPreferences.Editor obj_editor  = mipreferencia_user.edit();
                        obj_editor.putString("id_usuario",mensaje);
                        obj_editor.commit();

                        //Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();

                        obtenerChistes("https://practicaproductos.000webhostapp.com/chistesgratiswhatsApp/obtener_chistes.php");

                    } else if (resultado.equals("WARNING")) {

                        Modals nuevaModal = new Modals("Mensaje", mensaje, "OK", MainActivity.this);
                        nuevaModal.createModal();


                    }else{

                        Modals nuevaModal = new Modals("Mensaje", error, "OK", MainActivity.this);
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

                String id_usuario = mipreferencia_user.getString("id_usuario","");

                parametros.put("id_usuario",id_usuario);

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


    private void guardarChisteFavorito(final int id_chiste, final String id_usuario, final int id_boton_favorito_normal, final int id_boton_favorito_rojo, String url){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                //ocultarAlertaEspera();
                try {

                    JSONObject responseJSON = new JSONObject(response);

                    String mensaje = responseJSON.getString("mensaje");
                    String error = responseJSON.getString("error");
                    String resultado = responseJSON.getString("resultado");

                    if (resultado.equals("OK")) {

                        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();

                    }
                    else{

                        Modals nuevaModal = new Modals("Mensaje", error, "OK", MainActivity.this);
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

                parametros.put("id_chiste", String.valueOf(id_chiste));
                parametros.put("id_usuario",id_usuario);
                parametros.put("id_boton_favorito_normal", String.valueOf(id_boton_favorito_normal));
                parametros.put("id_boton_favorito_rojo", String.valueOf(id_boton_favorito_rojo));

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void eliminarChisteFavorito(final int id_chiste, final String id_usuario, final int id_boton_favorito_normal, final int id_boton_favorito_rojo, String url)
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                //ocultarAlertaEspera();
                try {

                    JSONObject responseJSON = new JSONObject(response);

                    String mensaje = responseJSON.getString("mensaje");
                    String error = responseJSON.getString("error");
                    String resultado = responseJSON.getString("resultado");

                    if (resultado.equals("OK")) {

                        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();

                    }
                    else{

                        Modals nuevaModal = new Modals("Mensaje", error, "OK", MainActivity.this);
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

                parametros.put("id_chiste", String.valueOf(id_chiste));
                parametros.put("id_usuario",id_usuario);
                parametros.put("id_boton_favorito_normal", String.valueOf(id_boton_favorito_normal));
                parametros.put("id_boton_favorito_rojo", String.valueOf(id_boton_favorito_rojo));

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


}
