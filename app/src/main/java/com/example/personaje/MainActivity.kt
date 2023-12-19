package com.example.personaje


import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog


class MainActivity : AppCompatActivity() {
    //creamos variables para todos los elementos de nuestro layout que queramos poder manejar aqui
    private lateinit var imagen: ImageView
    private lateinit var textoNombre: EditText
    private lateinit var cualidadRaza: Spinner
    private lateinit var cualidadClase: Spinner
    private lateinit var cualidadEV: Spinner
    private lateinit var boton: Button

    /*
    fun comprobarSpinners(cualidadClase:Spinner ,cualidadRaza:Spinner,cualidadEV:Spinner,boton:Button){
        //el boton solo se activa si cambiamos el valor vacio por uno que si sea valido
        if(cualidadRaza.selectedItemPosition!=0 && cualidadClase.selectedItemPosition!=0 && cualidadEV.selectedItemPosition!=0){
            boton.isEnabled= true
        }
    }
    */

    //se crea la vista enlanzandola con el layout que le pongamos desde el R layout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Guardamos las referencias a las ids del layout en las variables creadas anteriormente
        imagen = findViewById(R.id.imageView)
        textoNombre = findViewById(R.id.editTextNombre)
        cualidadRaza = findViewById(R.id.spinner1)
        cualidadClase = findViewById(R.id.spinner2)
        cualidadEV = findViewById(R.id.spinner3)
        boton = findViewById(R.id.botonCambioActividad)
        //boton.isEnabled=false
        var Raza:Personaje.Raza? = null
        var Clase:Personaje.Clase? = null
        var EstadoVital:Personaje.EstadoVital? = null

/*
        //tres listeners uno para cada spinner (quizas setOnItemSelectedListener )
        cualidadRaza.setOnClickListener {
            comprobarSpinners(cualidadClase,cualidadRaza,cualidadEV,boton)
        }
        cualidadClase.setOnClickListener {
            comprobarSpinners(cualidadClase,cualidadRaza,cualidadEV,boton)
        }
        cualidadEV.setOnClickListener {
            comprobarSpinners(cualidadClase,cualidadRaza,cualidadEV,boton)
        }
*/

        //creamos una accion cuando clickemos el boton
        boton.setOnClickListener{
            for (i in Personaje.Raza.values()){
                if(cualidadRaza.selectedItemPosition!=0){
                    if(cualidadRaza.selectedItem.toString()==i.toString()){
                        Raza=i
                    }
                }
            }
            for (j in Personaje.Clase.values()){
                if(cualidadClase.selectedItemPosition!=0){
                    if(cualidadClase.selectedItem.toString()==j.toString()){
                        Clase=j
                    }
                }
            }
            for (h in Personaje.EstadoVital.values()){
                if(cualidadEV.selectedItemPosition!=0){
                    if(cualidadEV.selectedItem.toString()==h.toString()){
                        EstadoVital=h
                    }
                }
            }
            if(Raza!=null && Clase!=null && EstadoVital!=null && textoNombre.text.toString().isNotBlank()){
                val personaje1= Personaje(textoNombre.text.toString(), Raza!!, Clase!!, EstadoVital!!)
                //soltamos una alerta para avisar que si vuelve a pulsar el boton continuaremos con los ajustes puestos (y ponemos el tostring del personaje para ver lo que llevamos)
                AlertDialog.Builder(this).apply{
                    setTitle("¿Desea continuar con estos parametros para su personaje?")
                    setMessage(personaje1.toString())
                    //un boton para si y otro para no con sus respectivas consecuencias
                    setPositiveButton("Si"){ _: DialogInterface, _: Int ->
                        //Creamos un intent para pasar a info a la nueva actividad donde se muestra el resultado
                        val intent = Intent(this@MainActivity,ResultadoPersonaje::class.java)
                        //añadimos el objeto Personaje (parseado o como sea) al intent
                        intent.putExtra("personaje1",personaje1)
                        //iniciamos la nueva actividad y ya en ella mostramos la foto y el objeto y mostramos todisimo con el tostring
                        startActivity(intent)
                    }
                    setNegativeButton("No"){ _: DialogInterface, _: Int ->
                        //Creamos un intent vacio a la misma actividad
                        val intent = Intent(this@MainActivity,MainActivity::class.java)
                        //iniciamos la misma actividad
                        startActivity(intent)
                    }.show()
                }
            }else{
                AlertDialog.Builder(this).apply {
                    setTitle("Te falta algun dato por completar en tu personaje...")
                    setMessage("Rellenalo y vuelve a intentarlo")
                    //un boton para OK
                    setPositiveButton("Ok", null) //la otra opcion es ponerle un null para sustituir al listener y que no haga nada
                } .show()
            }


        }
    }
}