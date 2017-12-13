package com.example.ldp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NuevoLibro extends AppCompatActivity {
    Button agregar;
    EditText nombre_libro, tipo, editorial, anio, autor, estado, prestamo, devolucion, stock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_libro);
        agregar = (Button) findViewById(R.id.agregar);

        nombre_libro = (EditText) findViewById(R.id.nombre);
        tipo = (EditText) findViewById(R.id.tipo);
        editorial = (EditText) findViewById(R.id.editorial);
        anio = (EditText) findViewById(R.id.anio);
        autor = (EditText) findViewById(R.id.autor);
        estado = (EditText) findViewById(R.id.estado);
        prestamo = (EditText) findViewById(R.id.prestamo);
        devolucion = (EditText) findViewById(R.id.devolucion);
        stock = (EditText) findViewById(R.id.stock);

        //add
    }


    public void agregarLibro(View v){
        if(!nombre_libro.equals("")){
            Libro libro = new Libro(getBaseContext());
            libro.open();
            libro.crearLibro(nombre_libro.getText().toString(), tipo.getText().toString(), editorial.getText().toString(), anio.getText().toString(), autor.getText().toString(), estado.getText().toString(), prestamo.getText().toString(), devolucion.getText().toString(), stock.getText().toString());
            nombre_libro.setText("");
            Toast.makeText(getBaseContext(), "Se agregó un nuevo libro.",Toast.LENGTH_LONG).show();
            Intent i = new Intent(NuevoLibro.this, Administrar.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
        }
        else {
            Toast.makeText(getBaseContext(), "Por favor llena todos los campos.",Toast.LENGTH_LONG).show();
        }
    }

}
