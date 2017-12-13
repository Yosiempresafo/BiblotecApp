package com.example.ldp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditarLibro extends AppCompatActivity {
    Button agregar, eliminar;
    EditText nombre, tipo, editorial, anio, autor, estado, prestamo, devolucion, stock;;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_libro);
        agregar = (Button) findViewById(R.id.actualizar);
        eliminar = (Button) findViewById(R.id.eliminar);

        nombre = (EditText) findViewById(R.id.nombre);
        tipo = (EditText) findViewById(R.id.tipo);
        editorial = (EditText) findViewById(R.id.editorial);
        anio = (EditText) findViewById(R.id.anio);
        autor = (EditText) findViewById(R.id.autor);
        estado = (EditText) findViewById(R.id.estado);
        prestamo = (EditText) findViewById(R.id.prestamo);
        devolucion = (EditText) findViewById(R.id.devolucion);
        stock = (EditText) findViewById(R.id.stock);

        Intent i = getIntent();
        id = i.getLongExtra("id",0);
        nombre.setText(i.getStringExtra("nombre"));
        tipo.setText(i.getStringExtra("tipo"));
        editorial.setText(i.getStringExtra("editorial"));
        anio.setText(i.getStringExtra("anio"));
        autor.setText(i.getStringExtra("autor"));
        estado.setText(i.getStringExtra("estado"));
        prestamo.setText(i.getStringExtra("prestamo"));
        devolucion.setText(i.getStringExtra("devolucion"));
        stock.setText(i.getStringExtra("stock"));
    }


    public void actualizarLibro(View v) {
        if(!nombre.equals("")){
            Libro libro = new Libro(getBaseContext());
            libro.open();
            libro.actualizarLibro(id, nombre.getText().toString(), tipo.getText().toString(), editorial.getText().toString(), anio.getText().toString(), autor.getText().toString(), estado.getText().toString(), prestamo.getText().toString(), devolucion.getText().toString(), stock.getText().toString());
            Toast.makeText(getBaseContext(), "El libro ha sido actualizado." , Toast.LENGTH_LONG).show();
            Intent i = new Intent(EditarLibro.this, Administrar.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
        }
        else {
            Toast.makeText(getBaseContext(), "Por favor llena todos los campos.",Toast.LENGTH_LONG).show();
        }
    }


    public void eliminarLibro(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(EditarLibro.this);
        builder.setTitle("Confirmación ");
        builder.setMessage("¿Está seguro de que desea eliminar el libro?");


        builder.setPositiveButton("SÍ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Libro libro = new Libro(getBaseContext());
                libro.open();
                libro.borrarLibro(id);
                finish();
                dialog.dismiss();
                Toast.makeText(getBaseContext(), "Libro eliminado.", Toast.LENGTH_LONG).show();
                Intent i = new Intent(EditarLibro.this, Administrar.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
