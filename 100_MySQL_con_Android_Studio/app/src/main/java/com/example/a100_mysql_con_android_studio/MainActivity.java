package com.example.a100_mysql_con_android_studio;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    private EditText txt_codigo,txt_producto,txt_precio,txt_fabricante;
    private Button btn_guardar,btn_buscar,btn_modificar,btn_eliminar;
    private ProgressDialog dialog;

    //ProgressDialog dialog = new ProgressDialog(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_codigo = (EditText)findViewById(R.id.txt_codigo);
        txt_producto = (EditText)findViewById(R.id.txt_producto);
        txt_precio = (EditText)findViewById(R.id.txt_precio);
        txt_fabricante = (EditText)findViewById(R.id.txt_fabricante);
        btn_guardar = (Button)findViewById(R.id.btn_guardar);
        btn_buscar = (Button)findViewById(R.id.btn_buscar);
        btn_modificar = (Button)findViewById(R.id.btn_modificar);
        btn_eliminar = (Button)findViewById(R.id.btn_eliminar);


        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean error = validarCampos();

                if(error==false){

                    mostrarAlertaEspera();
                    validaCodigoProductoGuardar("https://practicaproductos2.000webhostapp.com/validaCodigoProductoGuardar.php");
                    //guardarProductos("https://practicaproductos2.000webhostapp.com");
                }

                //ejecutarServicio("http://192.168.137.1:80/android_productos/registrar_producto.php");
                //ejecutarServicio("https://www.google.com");
            }
        });

        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String codigo = txt_codigo.getText().toString();

                if(codigo.equals("")){

                    Modals nuevaModal = new Modals("Mensaje","Ingrese el código del producto","OK",MainActivity.this);
                    nuevaModal.createModal();
                    txt_codigo.requestFocus();
                }
                else{
                    mostrarAlertaEspera();
                    buscarProducto("https://practicaproductos2.000webhostapp.com/buscarProducto.php");
                }
            }
        });

        btn_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String codigo = txt_codigo.getText().toString();

                boolean error = validarCampos();

                if(error==false){

                    mostrarAlertaEspera();
                    validaCodigoProductoModificar("https://practicaproductos2.000webhostapp.com/validaCodigoProductoModificar.php");
                }

            }
        });

        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String codigo = txt_codigo.getText().toString();

                if(codigo.equals("")){

                    Modals nuevaModal = new Modals("Mensaje","Ingrese el código del producto","OK",MainActivity.this);
                    nuevaModal.createModal();
                    txt_codigo.requestFocus();
                }
                else{
                    mostrarAlertaEspera();
                    validaCodigoProductoEliminar("https://practicaproductos2.000webhostapp.com/validaCodigoProductoEliminar.php");
                }

            }
        });




    }

    private void guardarProducto(String url){
       StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                ocultarAlertaEspera();
                try {

                    JSONObject responseJSON = new JSONObject(response);

                    String mensaje = responseJSON.getString("mensaje");
                    String error = responseJSON.getString("error");
                    String resultado = responseJSON.getString("resultado");

                    if (resultado.equals("OK")) {

                        Modals nuevaModal = new Modals("Mensaje", mensaje, "OK", MainActivity.this);
                        nuevaModal.createModal();

                        txt_codigo.setText("");
                        txt_producto.setText("");
                        txt_precio.setText("");
                        txt_fabricante.setText("");

                    }else{

                        Modals nuevaModal = new Modals("Mensaje", error, "OK", MainActivity.this);
                        nuevaModal.createModal();
                        txt_codigo.requestFocus();
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

                parametros.put("codigo",txt_codigo.getText().toString());
                parametros.put("producto",txt_producto.getText().toString());
                parametros.put("precio",txt_precio.getText().toString());
                parametros.put("fabricante",txt_fabricante.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void validaCodigoProductoGuardar(String url){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                ocultarAlertaEspera();
                try {

                    JSONObject responseJSON = new JSONObject(response);

                    String mensaje = responseJSON.getString("mensaje");
                    String error = responseJSON.getString("error");
                    String resultado = responseJSON.getString("resultado");

                    if (resultado.equals("OK")) {

                        guardarProducto("https://practicaproductos2.000webhostapp.com/guardarProducto.php");

                    } else if (resultado.equals("WARNING")) {

                       Modals nuevaModal = new Modals("Mensaje", mensaje, "OK", MainActivity.this);
                        nuevaModal.createModal();
                        txt_codigo.requestFocus();

                    }else{

                        Modals nuevaModal = new Modals("Mensaje", error, "OK", MainActivity.this);
                        nuevaModal.createModal();
                        txt_codigo.requestFocus();
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

                parametros.put("codigo",txt_codigo.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void buscarProducto(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                ocultarAlertaEspera();
                try {

                    JSONObject responseJSON = new JSONObject(response);

                    String error = responseJSON.getString("error");
                    String resultado = responseJSON.getString("resultado");


                    if (resultado.equals("OK")) {

                        JSONArray datosproductoArray = responseJSON.getJSONArray("mensaje");
                        JSONObject productoArray = datosproductoArray.getJSONObject(0);
                        String codigo = productoArray.getString("codigo");
                        String producto = productoArray.getString("producto");
                        String precio = productoArray.getString("precio");
                        //double precio_d =  Double.parseDouble(precio);
                        String fabricante = productoArray.getString("fabricante");

                        txt_producto.setText(producto);
                        txt_precio.setText(precio);
                        txt_fabricante.setText(fabricante);


                    }
                    else if (resultado.equals("WARNING")) {

                        String mensaje = responseJSON.getString("mensaje");

                        Modals nuevaModal = new Modals("Mensaje", mensaje, "OK", MainActivity.this);
                        nuevaModal.createModal();

                        txt_producto.setText("");
                        txt_precio.setText("");
                        txt_fabricante.setText("");
                    }
                    else{

                        Modals nuevaModal = new Modals("Mensaje", error, "OK", MainActivity.this);
                        nuevaModal.createModal();
                        txt_codigo.requestFocus();
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

                parametros.put("codigo",txt_codigo.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void modificarProducto(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                ocultarAlertaEspera();
                try {

                    JSONObject responseJSON = new JSONObject(response);

                    String mensaje = responseJSON.getString("mensaje");
                    String error = responseJSON.getString("error");
                    String resultado = responseJSON.getString("resultado");

                    if (resultado.equals("OK")) {

                        Modals nuevaModal = new Modals("Mensaje", mensaje, "OK", MainActivity.this);
                        nuevaModal.createModal();


                    }else{

                        Modals nuevaModal = new Modals("Mensaje", error, "OK", MainActivity.this);
                        nuevaModal.createModal();
                        txt_codigo.requestFocus();
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

                parametros.put("codigo",txt_codigo.getText().toString());
                parametros.put("producto",txt_producto.getText().toString());
                parametros.put("precio",txt_precio.getText().toString());
                parametros.put("fabricante",txt_fabricante.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void validaCodigoProductoModificar(String url){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                ocultarAlertaEspera();
                try {

                    JSONObject responseJSON = new JSONObject(response);

                    String mensaje = responseJSON.getString("mensaje");
                    String error = responseJSON.getString("error");
                    String resultado = responseJSON.getString("resultado");

                    if (resultado.equals("OK")) {

                        modificarProducto("https://practicaproductos2.000webhostapp.com/modificarProducto.php");

                    } else if (resultado.equals("WARNING")) {

                        Modals nuevaModal = new Modals("Mensaje", mensaje, "OK", MainActivity.this);
                        nuevaModal.createModal();
                        txt_codigo.requestFocus();

                    }else{

                        Modals nuevaModal = new Modals("Mensaje", error, "OK", MainActivity.this);
                        nuevaModal.createModal();
                        txt_codigo.requestFocus();
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

                parametros.put("codigo",txt_codigo.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void eliminarProducto(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                ocultarAlertaEspera();
                try {

                    JSONObject responseJSON = new JSONObject(response);

                    String mensaje = responseJSON.getString("mensaje");
                    String error = responseJSON.getString("error");
                    String resultado = responseJSON.getString("resultado");

                    if (resultado.equals("OK")) {

                        Modals nuevaModal = new Modals("Mensaje", mensaje, "OK", MainActivity.this);
                        nuevaModal.createModal();

                        txt_codigo.setText("");
                        txt_producto.setText("");
                        txt_precio.setText("");
                        txt_fabricante.setText("");


                    }else{

                        Modals nuevaModal = new Modals("Mensaje", error, "OK", MainActivity.this);
                        nuevaModal.createModal();
                        txt_codigo.requestFocus();
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

                parametros.put("codigo",txt_codigo.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void validaCodigoProductoEliminar(String url){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                ocultarAlertaEspera();
                try {

                    JSONObject responseJSON = new JSONObject(response);

                    String mensaje = responseJSON.getString("mensaje");
                    String error = responseJSON.getString("error");
                    String resultado = responseJSON.getString("resultado");

                    if (resultado.equals("OK")) {

                        eliminarProducto("https://practicaproductos2.000webhostapp.com/eliminarProducto.php");

                    } else if (resultado.equals("WARNING")) {

                        Modals nuevaModal = new Modals("Mensaje", mensaje, "OK", MainActivity.this);
                        nuevaModal.createModal();
                        txt_codigo.requestFocus();

                    }else{

                        Modals nuevaModal = new Modals("Mensaje", error, "OK", MainActivity.this);
                        nuevaModal.createModal();
                        txt_codigo.requestFocus();
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

                parametros.put("codigo",txt_codigo.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private boolean validarCampos(){

        boolean error = false;

        String codigo = txt_codigo.getText().toString();
        String producto = txt_producto.getText().toString();
        String precio = txt_precio.getText().toString();
        String fabricante = txt_fabricante.getText().toString();

        if(codigo.equals("")){
            error=true;
            Modals nuevaModal = new Modals("Mensaje","Ingrese el código del producto","OK",MainActivity.this);
            nuevaModal.createModal();
            txt_codigo.requestFocus();
        }
        else{
            if(producto.equals("")) {
                error=true;
                Modals nuevaModal = new Modals("Mensaje","Ingrese el nombre del producto","OK",MainActivity.this);
                nuevaModal.createModal();
                txt_producto.requestFocus();
            }
            else {
                if (precio.equals("")) {
                    error = true;
                    Modals nuevaModal = new Modals("Mensaje", "Ingrese el precio del producto", "OK", MainActivity.this);
                    nuevaModal.createModal();
                    txt_precio.requestFocus();
                }
                else{
                    if (fabricante.equals("")) {
                        error = true;
                        Modals nuevaModal = new Modals("Mensaje", "Ingrese el fabricante del producto", "OK", MainActivity.this);
                        nuevaModal.createModal();
                        txt_fabricante.requestFocus();
                    }
                }
            }
        }


        return error;

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
