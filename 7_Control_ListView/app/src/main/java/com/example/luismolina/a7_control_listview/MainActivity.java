package com.example.luismolina.a7_control_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvInformacion;
    private ListView lvPaises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String [] paises = { "Argentina", "Chile", "Paraguay", "Bolivia",
                "Peru", "Ecuador", "Brasil", "Colombia", "Venezuela", "Uruguay" };

        final String [] habitantes  = { "40000000", "17000000", "6500000",
                "10000000", "30000000", "14000000", "183000000", "44000000",
                "29000000", "3500000" };

        tvInformacion = (TextView)findViewById(R.id.tvInformacion);
        lvPaises = (ListView)findViewById(R.id.lvPaises);

        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,paises);
        lvPaises.setAdapter(adapter);

        lvPaises.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvInformacion.setText("La población de " + (CharSequence) lvPaises.getItemAtPosition(position)+ " es " + habitantes[position]);
            }
        });







    }
}
