package com.example.personaje


import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat

class ResultadoPersonaje : AppCompatActivity() {
    //creamos variables para todos los elementos de nuestro layout que queramos poder manejar aqui
    private lateinit var imagen: ImageView
    private lateinit var textoPersonaje: TextView
    private lateinit var boton1: Button
    private lateinit var boton2: Button
    private lateinit var drawableGuarro: Drawable

    //se crea la vista enlanzandola con el layout que le pongamos desde el R layout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado_personaje)

            //Guardamos las referencias a las ids del layout en las variables creadas anteriormente
            imagen = findViewById(R.id.imageView)
            textoPersonaje = findViewById(R.id.textView2)
            boton1 = findViewById(R.id.botonAnteriorActividad)
            boton2 = findViewById(R.id.botonProximaActividad)

            //recibimos los datos de personaje para guardarlos en las variables de arriba
            val personaje1 =intent.getSerializableExtra("personaje1") as Personaje
            //guardamos todos los datos de personaje en el textview de esta vista
            textoPersonaje.text=personaje1.toString()

            val UriParaImagen= "@drawable/"+personaje1.getRaza().toString().lowercase()+personaje1.getClase().toString().lowercase()+personaje1.getEstadoVital().toString().lowercase()
            imagen.setImageResource(resources.getIdentifier(UriParaImagen,null,packageName))


            //creamos una accion cuando clickemos el boton 1
            boton1.setOnClickListener{
            //Creamos un intent vacio a la anterior actividad
            val intent = Intent(this@ResultadoPersonaje,MainActivity::class.java)
            //iniciamos la anterior actividad
            startActivity(intent)
            }

            //creamos una accion cuando clickemos el boton 2
            boton2.setOnClickListener{
                //Creamos un intent para pasar laa info a la nueva actividad donde no se hace nada por ahora
                val intent = Intent(this@ResultadoPersonaje,VaciaActivity::class.java)
                //añadimos el objeto Personaje (parseado o como sea) al intent
                intent.putExtra("personaje1",personaje1)
                //iniciamos la actividad en blanco
                startActivity(intent)
            }

    }

}
