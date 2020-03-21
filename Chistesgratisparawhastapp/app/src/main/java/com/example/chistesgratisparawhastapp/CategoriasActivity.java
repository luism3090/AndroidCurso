package com.example.chistesgratisparawhastapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Space;
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

public class CategoriasActivity extends AppCompatActivity {

    ProgressDialog dialog;

    TTSManager ttsManager = null;

    SharedPreferences mipreferencia_user, mipreferencia_TotalRows, mipreferencia_categoria;

//    ImageView image_home1,image_home2,image_categorias1,image_categorias2,image_favoritos1,image_favoritos2,image_nuevos1,image_nuevos2;

    ScrollView sv_main;
    int x=0;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        getSupportActionBar().setTitle("Categorias");

        sv_main = (ScrollView)findViewById(R.id.scrol);

        String id_usuario = getIntent().getStringExtra("id_usuario");

        final ImageView image_home1 = (ImageView)findViewById(R.id.image_home1);
        final ImageView image_home2 = (ImageView)findViewById(R.id.image_home2);
        final ImageView image_favoritos1 = (ImageView)findViewById(R.id.image_favoritos1);
        final ImageView image_busqueda1 = (ImageView)findViewById(R.id.image_busqueda1);

        mipreferencia_categoria = getSharedPreferences("datos_categoria", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor0  = mipreferencia_categoria.edit();
        obj_editor0.putString("id_categoria","");
        obj_editor0.commit();

        mipreferencia_user = getSharedPreferences("datos_usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor1  = mipreferencia_user.edit();
        obj_editor1.putString("id_usuario",id_usuario);
        obj_editor1.commit();


        mostrarAlertaEspera();
        obtenerCategorias("https://practicaproductos.000webhostapp.com/chistesgratiswhatsApp/obtener_categorias.php");

        image_home1.setOnClickListener(new View.OnClickListener() {
                @Override
                        public void onClick(View v) {
                                Intent inicio = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(inicio);
                        }
        });

        image_favoritos1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent favoritos = new Intent(getApplicationContext(),FavoritosActivity.class);

                favoritos.putExtra("id_usuario",mipreferencia_user.getString("id_usuario",""));

                startActivity(favoritos);

            }
        });

        image_busqueda1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent nuevosChistes = new Intent(getApplicationContext(), BusquedaChistesActivity.class);

                nuevosChistes.putExtra("id_usuario",mipreferencia_user.getString("id_usuario",""));

                startActivity(nuevosChistes);

            }
        });



    }




    private void obtenerCategorias(String url){

        com.android.volley.toolbox.StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                final LinearLayout layout_categorias = (LinearLayout)findViewById(R.id.layout_categorias);

                TTSManager ttsManager = new TTSManager();
                ttsManager.init(getApplicationContext());

                    ocultarAlertaEspera();

                try {

                    JSONObject responseJSON = new JSONObject(response);

                    String mensaje = responseJSON.getString("mensaje");
                    String error = responseJSON.getString("error");
                    String resultado = responseJSON.getString("resultado");

                    if (resultado.equals("OK")) {

                        JSONArray datosArray = responseJSON.getJSONArray("mensaje");

                        for (int i = 0; i < datosArray.length(); i++) {

                            JSONObject datosRow = datosArray.getJSONObject(i);
                            String categoria = datosRow.getString("categoria");
                            String id_categoria = datosRow.getString("id_categoria");
                            int id_categoria_db = Integer.parseInt(id_categoria);
                            String fecha = datosRow.getString("fecha");


                            // --------------------------------- Creando en Text View para colocar el texto del chiste ---------------------------------

                            Button botonCategoria = new Button(getApplicationContext());
                            botonCategoria.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                            botonCategoria.setText(categoria);
                            botonCategoria.setBackgroundColor(Color.rgb(0,0,0));
                            botonCategoria.setTextColor(Color.rgb(255,255,255));
                            botonCategoria.setMinHeight(100);
                            //botonCategoria.setGravity(Gravity.CENTER);
                            botonCategoria.setTextSize(24);
                            //botonCategoria.setPadding(30,0,30,0);
                            botonCategoria.setId(id_categoria_db);  //
                            layout_categorias.addView(botonCategoria);
                            botonCategoria.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //Toast.makeText(getApplicationContext(),String.valueOf(view.getId()+" Texto"),Toast.LENGTH_SHORT).show();

                                    String id_categoria = String.valueOf(view.getId());

                                    Button BotonCategoria = (Button) findViewById(view.getId());
                                    String textoCategoria = BotonCategoria.getText().toString();

                                    Intent chistesCategoria = new Intent(getApplicationContext(),ChistesCategoriaActivity.class);

                                    chistesCategoria.putExtra("id_categoria",id_categoria);
                                    chistesCategoria.putExtra("titulo_categoria",textoCategoria);
                                    chistesCategoria.putExtra("id_usuario",mipreferencia_user.getString("id_usuario",""));

                                    startActivity(chistesCategoria);


                                }
                            });

                            // --------------------------------------- Creando el espacio entre categorias ---------------------------------

                            Space espacioEntreChiste = new Space(getApplicationContext());
                            //Space espacioEntreChiste = new Space((Context) context);
                            espacioEntreChiste.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            espacioEntreChiste.setMinimumHeight(50);
                            layout_categorias.addView(espacioEntreChiste);


                        }


                        //Toast.makeText(getApplicationContext(), mipreferencia_TotalRows.getString("totalRows","")+"_c", Toast.LENGTH_LONG).show();

                        // Modals nuevaModal = new Modals("Mensaje", mipreferencia_TotalRows.getString("totalRows","")+"b", "Ok", MainActivity.this);
                        // nuevaModal.createModal();

                    }else{

                        Modals nuevaModal = new Modals("Mensaje", mensaje, "Ok", CategoriasActivity.this);
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
                //Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                ocultarAlertaEspera();
                Toast.makeText(getApplicationContext(), "Error al conectarse a internet", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();

                parametros.put("a","");

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void mostrarAlertaEspera(){
        dialog = new ProgressDialog(CategoriasActivity.this);
        dialog.setMessage("Espere por favor...");
        dialog.setCancelable(false);
        dialog.show();
    }

    private void ocultarAlertaEspera(){
        if (dialog.isShowing())
            dialog.dismiss();
    }






}
