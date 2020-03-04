package com.example.chistesgratisparawhastapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
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

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ProgressDialog dialog;

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

                            Space espacioEntreChiste = new Space(getApplicationContext());
                            espacioEntreChiste.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            espacioEntreChiste.setMinimumHeight(150);

                            TextView textViewChiste = new TextView(getApplicationContext());
                            textViewChiste.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            textViewChiste.setText(chiste);
                            textViewChiste.setBackgroundColor(Color.rgb(0,0,0));
                            textViewChiste.setTextColor(Color.rgb(255,255,255));
                            textViewChiste.setMinHeight(700);
                            textViewChiste.setGravity(Gravity.CENTER);
                            textViewChiste.setTextSize(24);
                            textViewChiste.setPadding(30,0,30,0);

                            RelativeLayout rel_layout_acciones = new RelativeLayout(getApplicationContext());
                            rel_layout_acciones.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                            ImageButton botonWhastapp = new ImageButton(getApplicationContext());
                            botonWhastapp.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                            botonWhastapp.setImageResource(R.mipmap.icono_whatsapp);
                            botonWhastapp.setBackgroundColor(Color.TRANSPARENT);
                            botonWhastapp.setPadding(0,0,0,0);
                            botonWhastapp.setId(i);

                            ImageButton botonFacebook = new ImageButton(getApplicationContext());
                            botonWhastapp.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                            botonFacebook.setImageResource(R.mipmap.icono_facebook);
                            botonFacebook.setBackgroundColor(Color.TRANSPARENT);
                            botonFacebook.setPadding(150,-5,0,0);
                            botonFacebook.setMaxHeight(55);
                            botonFacebook.setId(i);

                            ImageButton botonCopiar = new ImageButton(getApplicationContext());
                            botonWhastapp.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                            botonCopiar.setImageResource(R.mipmap.icono_copiar);
                            botonCopiar.setBackgroundColor(Color.TRANSPARENT);
                            botonCopiar.setPadding(302,5,0,0);
                            botonCopiar.setId(i);

                            ImageButton botonCompartir = new ImageButton(getApplicationContext());
                            botonCompartir.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                            botonCompartir.setImageResource(R.mipmap.icono_compartir);
                            botonCompartir.setBackgroundColor(Color.TRANSPARENT);
                            botonCompartir.setPadding(442,5,0,0);
                            botonCompartir.setId(i);

                            ImageButton botonCorazon = new ImageButton(getApplicationContext());
                            botonCompartir.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                            botonCorazon.setImageResource(R.mipmap.icono_corazon);
                            botonCorazon.setBackgroundColor(Color.TRANSPARENT);
                            botonCorazon.setPadding(602,5,0,0);
                            botonCorazon.setId(i);

                            ImageButton botonVerMas = new ImageButton(getApplicationContext());
                            botonVerMas.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                            botonVerMas.setImageResource(R.mipmap.icono_vermas);
                            botonVerMas.setBackgroundColor(Color.TRANSPARENT);
                            botonVerMas.setPadding(602,5,0,0);
                            botonVerMas.setId(i);

                            layout_chistes.addView(textViewChiste);
                            layout_chistes.addView(rel_layout_acciones);
                            rel_layout_acciones.addView(botonWhastapp);
                            rel_layout_acciones.addView(botonFacebook);
                            rel_layout_acciones.addView(botonCopiar);
                            rel_layout_acciones.addView(botonCompartir);
                            rel_layout_acciones.addView(botonCorazon);
                            rel_layout_acciones.addView(botonVerMas);
                            layout_chistes.addView(espacioEntreChiste);

                        }


                        /*


                        txt_codigo.setText("");
                        txt_producto.setText("");
                        txt_precio.setText("");
                        txt_fabricante.setText("");
                        */


                    }else{
                        /*
                        Modals nuevaModal = new Modals("Mensaje", error, "OK", MainActivity.this);
                        nuevaModal.createModal();
                        txt_codigo.requestFocus();
                        */
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
    
    
}
