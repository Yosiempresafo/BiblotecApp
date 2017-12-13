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

public class VerLibro extends AppCompatActivity {
    Button agregar, eliminar;
    //EditText precio, descripcion, marca, stock, proveedor;
    TextView nombre, tipo, editorial, anio, autor, estado, prestamo, devolucion, stock;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_libro);
        agregar = (Button) findViewById(R.id.agregar);
        eliminar = (Button) findViewById(R.id.eliminar);

        nombre = (TextView) findViewById(R.id.nombre);
        tipo = (TextView) findViewById(R.id.tipo);
        editorial = (TextView) findViewById(R.id.editorial);
        anio = (TextView) findViewById(R.id.anio);
        autor = (TextView) findViewById(R.id.autor);
        estado = (TextView) findViewById(R.id.estado);
        prestamo = (TextView) findViewById(R.id.prestamo);
        devolucion = (TextView) findViewById(R.id.devolucion);
        stock = (TextView) findViewById(R.id.stock);

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

    public void prestamo(View v) {

        //Producto producto = new Producto(getBaseContext());
        //producto.open();
        //producto.actualizarProducto(id, nombre.getText().toString(), precio.getText().toString(), descripcion.getText().toString(), marca.getText().toString(), stock.getText().toString(), descripcion.getText().toString());
        Toast.makeText(getBaseContext(), "Préstamo completado exitosamente.", Toast.LENGTH_LONG).show();
        Intent i = new Intent(VerLibro.this, Prestamo.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

}
