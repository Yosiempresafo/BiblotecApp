package com.example.ldp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class Prestamo extends ListActivity {
    Libro datos;
    //Autor datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestamo);
        datos = new Libro(this);
        datos.open();

        final List<Libro> values = datos.getAll();
        ArrayAdapter<Libro> adapter = new ArrayAdapter<Libro>(this, android.R.layout.simple_expandable_list_item_1,values);
        //final List<Autor> values = datos.getAll();
        //ArrayAdapter<Autor> adapter = new ArrayAdapter<Autor>(this, android.R.layout.simple_expandable_list_item_1,values);

        setListAdapter(adapter);

        ListView listView = getListView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Prestamo.this, VerLibro.class);
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


    public void goToPrincipal(View v){
        Toast.makeText(this, "Prestamo completado.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, principal.class);
        startActivity(intent);
        finish();
    }
}
