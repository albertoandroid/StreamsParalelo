package com.androiddesdecero.streamparalelo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    /*
    El Stream paralelo es una forma de ejecutar las operaciones del Stream
    a través de varios hilos.
    Vamos a ver un ejemplo en el que ejecutamos un Stream con una lista de
    10 elementos en el que tienen que esperar un segundo por elemento más la operación.
    Como vemos esto va a tardar aproximadamente 10 segundos.
    Pero si queremos reducir el tiempo, nos ofrecen los Stream Paralelo que se encarga de
    crear varios hilos y lanzar la operación en paralelo reduciendo el tiempo.
     */

    private List<Integer> numeros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        llenarListaNumeros();
        probarStreamParalelo();
        probarStream();
    }

    public void llenarListaNumeros(){
        numeros= new ArrayList<>();
        for(int i=0; i<10; i++){
            numeros.add(i);
        }
    }

    public void probarStream(){
        long numero1=System.currentTimeMillis();
        numeros.stream().forEach(e-> sumar1(e));
        long numero2=System.currentTimeMillis();
        Log.d("TAG1", "Normal: " + (numero2-numero1) + "");
    }

    public void probarStreamParalelo(){
        long numero1=System.currentTimeMillis();
        numeros.parallelStream().forEach(e-> sumar1(e));
        long numero2=System.currentTimeMillis();
        Log.d("TAG1", "Paralelo: " + (numero2-numero1) + "");
    }

    public int sumar1(int numero){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return numero+1;
    }
}
