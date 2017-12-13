package com.example.ldp;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class Libro {
    //Atributos modificables del elemento en la BD
    public long id;
    public String nombre, tipo, editorial, anio, autor, estado, prestamo, devolucion, stock;


    private SQLiteDatabase database; //Objeto útil para manipulación de tablas en la BD
    private Database dbHelper; //Objeto de clase creada por nosotros para manipulación de la BD
    //Arreglo de cadenas que almacena toda una fila de una tabla (datos de un contacto)
    public String[] allColumns = {
            Database.LIBRO_ID,
            Database.LIBRO_NOMBRE,
            Database.LIBRO_TIPO,
            Database.LIBRO_EDITORIAL,
            Database.LIBRO_ANIO,
            Database.LIBRO_AUTOR,
            Database.LIBRO_ESTADO,
            Database.LIBRO_PRESTAMO,
            Database.LIBRO_DEVOLUCION,
            Database.LIBRO_STOCK
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
    public Libro (Context context) {
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

    //Método que nos permite crear un contacto a partir de lo que le pasemos en NewActivity
    public Libro crearLibro(String nombre, String tipo, String editorial, String anio, String autor, String estado, String prestamo, String devolucion, String stock) {
        //ContentValues nos permite almacenar un set de valores
        ContentValues values = new ContentValues();
        //Le decimos a values qué valor se agregará en qué columna
        values.put(Database.LIBRO_NOMBRE, nombre);
        values.put(Database.LIBRO_TIPO, tipo);
        values.put(Database.LIBRO_EDITORIAL, editorial);
        values.put(Database.LIBRO_ANIO, anio);
        values.put(Database.LIBRO_AUTOR, autor);
        values.put(Database.LIBRO_ESTADO, estado);
        values.put(Database.LIBRO_PRESTAMO, prestamo);
        values.put(Database.LIBRO_DEVOLUCION, devolucion);
        values.put(Database.LIBRO_STOCK, Integer.parseInt(stock));
        //Con el método insert agregamos los valores a la BD
        //Además, el método "insert" regresa el id, por eso lo almacenamos
        long insertId = database.insert(Database.TABLE_LIBRO, null, values);
        //Creamos un cursor para desplazarnos por las filas de la tabla con "query" con el ID dado
        Cursor cursor = database.query(Database.TABLE_LIBRO,
                allColumns, Database.LIBRO_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Libro nuevoLibro = cursorToLibro(cursor);
        //Cerramos el cursor
        cursor.close();
        return nuevoLibro;
    }

    //Método para actualizar contactos a partir de los datos pasados por EditActivity
    public void actualizarLibro(long id, String nombre, String tipo, String editorial, String anio, String autor, String estado, String prestamo, String devolucion, String stock) {
        ContentValues values = new ContentValues();
        values.put(Database.LIBRO_NOMBRE, nombre);
        values.put(Database.LIBRO_TIPO, tipo);
        values.put(Database.LIBRO_EDITORIAL, editorial);
        values.put(Database.LIBRO_ANIO, anio);
        values.put(Database.LIBRO_AUTOR, autor);
        values.put(Database.LIBRO_ESTADO, estado);
        values.put(Database.LIBRO_PRESTAMO, prestamo);
        values.put(Database.LIBRO_DEVOLUCION, devolucion);
        values.put(Database.LIBRO_STOCK, Integer.parseInt(stock));
        String where = Database.LIBRO_ID + "=?";
        String[] whereArgs = new String[] {String.valueOf(id)};

        database.update(Database.TABLE_LIBRO, values, where, whereArgs);
    }

    public void borrarLibro(long id) {
        database.delete(Database.TABLE_LIBRO, Database.LIBRO_ID
                + " = " + id, null);
    }

    //Método que nos devuelve una lista con todos los contactos
    public List<Libro> getAll() {
        //Creamos un objeto de tipo lista que contiene contactos
        List<Libro> libros = new ArrayList<Libro>();

        //Creamos un cursor sobre la tabla de contactos
        Cursor cursor = database.query(Database.TABLE_LIBRO,
                allColumns, null, null, null, null, null);

        //Nos desplazamos por la tabla y agregamos los elementos a la lista
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Libro libro = cursorToLibro(cursor);
            libros.add(libro);
            cursor.moveToNext();
        }
        //Debemos cerrar el cursor
        cursor.close();
        return libros;
    }

    //Método que nos devuelve los datos de un contacto utilizando un cursor
    private Libro cursorToLibro(Cursor cursor) {
        Libro libro = new Libro(null);
        //Obtenemos los valores a partir de su número de columna
        libro.id = cursor.getLong(0);
        libro.nombre = cursor.getString(1);
        libro.tipo = cursor.getString(2);
        libro.editorial = cursor.getString(3);
        libro.anio = cursor.getString(4);
        libro.autor = cursor.getString(5);
        libro.estado = cursor.getString(6);
        libro.prestamo = cursor.getString(7);
        libro.devolucion = cursor.getString(8);
        libro.stock = cursor.getString(9);
        return libro;
    }
}
