package com.example.a7_imagebutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private String frases [] = { "",
                                "Todo lo que siempre has querido esta del otro lado del miedo",
                                "El mas grande problema de este mundo es que la gente no tiene hambre de ir por más",
                                "Como pretendes obtener resultados distintos si te la pasas haciendo siempre lo mismo",
                                "Aun no eres quien vas a llegar a ser",
                                "Si no tienes suficiente talento, lo puedes compenzar trabajando más",
                                "La incomodidad es una de las pocas opciones que tenemos para poder dar ese cambio",
                                "Si no trabajas por tus sueños alguien te contratará para que trabajes en los suyos",
                                "El dinero sirve para salir de tu zona de confort, con el mayor confort posible",
                                "Tus ingresos pueden crecer unicamente hasta donde crezcas tu",
                                "La zona de confort es ese lugar donde nadie crece, quieres darte ahi por mucho tiempo"
                                };




    private String chistes [] = {   "",
                                    "Papá quiero una mascota... Ya tuviste piojos el año pasado y los mataste ",
                                    "Entra una persona a comprar a la tienda: – Hola, quiero un desodorante. – OK, enseguida se lo traigo, \n" +
                                            "¿lo quiere de spray? – No, lo quiero de sobacos.",
                                    "Tengo una cocinera que es como un sol, lo quema todo.",
                                    "¿Sabes que mi primo anda en bicicleta desde los 5 años? Asu apoco ?, ya debe estar bien lejos",
                                    "¿A qué no sabes lo que le dice un pollito a otro pollito cuando se enoja? ¡Caldito seas!",
                                    "¿Cómo se llama el hermano vegetariano de Bruce Lee? \n" +
                                            "Broco Lee",
                                    "Dios por favor permite que este calor derrita la grasa que hay en mi cuerpo",
                                    "Mi hijo en su nuevo trabajo se siente como pez en el agua\n" +
                                            "¿Qué hace?\n" +
                                            "Nada!!",
                                    "Un niño le dijo a su papá \n" +
                                            "papá papá vinieron a preguntar si \n" +
                                            "¿Aquí vendian un burro ?\n" +
                                            "y ¿qué les dijiste ?\n" +
                                            "que no estabas",
                                    "Si mi perro me dice lava la ropa\n" +
                                            "de todos modos no lo hare \n" +
                                            "porque?  porque los perros no hablan"
                                };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }


    public void Frases(View view){

        int numFrases = (int) (Math.random() * 10 + 1);

        Toast.makeText(this,frases[numFrases],Toast.LENGTH_LONG).show();

    }

    public void Chistes(View view){

        int numChistes = (int) (Math.random() * 10 + 1);

        Toast.makeText(this,chistes[numChistes],Toast.LENGTH_LONG).show();

    }


}
