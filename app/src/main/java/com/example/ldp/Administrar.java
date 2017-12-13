package com.example.ldp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.List;

public class Administrar extends ListActivity {
    //Producto datos;
    Libro datosLibros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar);

        datosLibros = new Libro(this);
        datosLibros.open();

        final List<Libro> values = datosLibros.getAll();
        ArrayAdapter<Libro> adapter = new ArrayAdapter<Libro>(this, android.R.layout.simple_expandable_list_item_1,values);
        setListAdapter(adapter);
        ListView listView = getListView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Administrar.this, EditarLibro.class);
                i.putExtra("id",values.get(position).id);
                i.putExtra("nombre",values.get(position).nombre);
                i.putExtra("tipo",values.get(position).tipo);
                i.putExtra("editorial",values.get(position).editorial);
                i.putExtra("anio",values.get(position).anio);
                i.putExtra("autor",values.get(position).autor);
                i.putExtra("estado",values.get(position).estado);
                i.putExtra("prestamo",values.get(position).prestamo);
                i.putExtra("devolucion",values.get(position).devolucion);
                i.putExtra("stock",values.get(position).stock);
                startActivity(i);
            }
        });
    }

    public void goToNuevoLibro(View v){
        Intent i = new Intent(Administrar.this, NuevoLibro.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

    public void goToNuevoAutor(View v){
        Intent i = new Intent(Administrar.this, NuevoLibro.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }


}
