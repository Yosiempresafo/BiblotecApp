package com.example.ldp;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ldp.db";
    private static final int DATABASE_VERSION = 1;

    //Libro
    public static final String TABLE_LIBRO = "libro";
    public static final String LIBRO_ID = "id_libro";
    public static final String LIBRO_NOMBRE = "nombre";
    public static final String LIBRO_TIPO = "tipo";
    public static final String LIBRO_EDITORIAL = "editorial";
    public static final String LIBRO_ANIO = "anio";
    public static final String LIBRO_AUTOR = "autor";
    public static final String LIBRO_ESTADO = "estado";
    public static final String LIBRO_PRESTAMO = "prestamo";
    public static final String LIBRO_DEVOLUCION = "devolucion";
    public static final String LIBRO_STOCK = "stock";

    //Autor
    public static final String TABLE_AUTOR = "autor";
    public static final String AUTOR_ID = "id_autor";
    public static final String AUTOR_NOMBRE = "nombre";
    public static final String AUTOR_AP_PAT = "ap_pat";
    public static final String AUTOR_AP_MAT = "ap_mat";
    public static final String AUTOR_NACIONALIDAD = "nacionalidad";
    public static final String AUTOR_FECHA_NAC = "fecha_nac";

    private static final String LIBRO_CREATE_TABLE = "create table "
            + TABLE_LIBRO + "(" + LIBRO_ID
            + " integer primary key autoincrement,"
            + LIBRO_NOMBRE + " text not null,"
            + LIBRO_TIPO + " text not null,"
            + LIBRO_EDITORIAL + " text not null,"
            + LIBRO_ANIO + " text not null,"
            + LIBRO_AUTOR + " text not null,"
            + LIBRO_ESTADO + " text not null,"
            + LIBRO_PRESTAMO + " text not null,"
            + LIBRO_DEVOLUCION + " text not null,"
            + LIBRO_STOCK + " text not null"
            + ");";

    private static final String AUTOR_CREATE_TABLE = "create table "
            + TABLE_AUTOR + "("
            + AUTOR_ID + " integer primary key autoincrement,"
            + AUTOR_NOMBRE + " text not null,"
            + AUTOR_AP_PAT + " text not null,"
            + AUTOR_AP_MAT + " text not null,"
            + AUTOR_NACIONALIDAD + " text not null,"
            + AUTOR_FECHA_NAC + " text not null"
            + ");";


    private static final String AUTOR_INSERT =
            "INSERT or replace INTO " + TABLE_AUTOR + "(" + AUTOR_NOMBRE + "," + AUTOR_AP_PAT + "," + AUTOR_AP_MAT + "," + AUTOR_NACIONALIDAD + "," + AUTOR_FECHA_NAC + ")"
                    + " VALUES('Gabriel', 'Garcia', 'Marquez', 'Colombiana', '1968-01-01')";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(LIBRO_CREATE_TABLE);
        database.execSQL(AUTOR_CREATE_TABLE);
        //database.execSQL("INSERT INTO libro (nombre) VALUES ('papuu') ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIBRO);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_AUTOR);
        onCreate(db);
    }
}
