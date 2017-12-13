package com.example.ldp;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class Autor {
    //Atributos modificables del elemento en la BD
    public long id;
    public String nombre;
    public String ap_pat;
    public String ap_mat;
    public String nacionalidad;
    public String fecha_nac;
    private SQLiteDatabase database; //Objeto útil para manipulación de tablas en la BD
    private Database dbHelper; //Objeto de clase creada por nosotros para manipulación de la BD
    //Arreglo de cadenas que almacena toda una fila de una tabla (datos de un contacto)
    private String[] allColumns = {
            Database.AUTOR_ID,
            Database.AUTOR_NOMBRE,
            Database.AUTOR_AP_PAT,
            Database.AUTOR_AP_MAT,
            Database.AUTOR_NACIONALIDAD,
            Database.AUTOR_FECHA_NAC
    };

    public long getId() {
        return id;
    }

    //Método que desplegará nombres en la lista de los contactos.
    @Override
    public String toString() {
        return nombre + " ";
    }

    //Se instancía un objeto de la clase que estamos creando a partir de este contexto
    public Autor (Context context) {
        dbHelper = new Database(context);
    }

    //Método que nos permite abrir la base de datos para su escritura
    public void open() throws SQLException {
        //Se asigna la base de datos modificable a la variable database
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    //Método que nos devuelve una lista con todos los contactos
    public List<Autor> getAll() {
        //Creamos un objeto de tipo lista que contiene contactos
        List<Autor> autors = new ArrayList<Autor>();

        //Creamos un cursor sobre la tabla de contactos
        Cursor cursor = database.query(Database.TABLE_AUTOR,
                allColumns, null, null, null, null, null);

        //Nos desplazamos por la tabla y agregamos los elementos a la lista
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Autor autor = cursorToAutor(cursor);
            autors.add(autor);
            cursor.moveToNext();
        }
        //Debemos cerrar el cursor
        cursor.close();
        return autors;
    }

    //Método que nos devuelve los datos de un contacto utilizando un cursor
    private Autor cursorToAutor(Cursor cursor) {
        Autor autor = new Autor(null);
        //Obtenemos los valores a partir de su número de columna
        autor.id = cursor.getLong(0);
        autor.nombre = cursor.getString(1);
        autor.ap_pat = cursor.getString(2);
        autor.ap_mat = cursor.getString(3);
        autor.nacionalidad = cursor.getString(4);
        autor.fecha_nac = cursor.getString(5);
        return autor;
    }
}
