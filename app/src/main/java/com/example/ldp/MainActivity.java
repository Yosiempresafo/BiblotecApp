package com.example.ldp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String usuario, pass;
    EditText ETUsuario;
    EditText ETPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ETUsuario = (EditText)findViewById(R.id.ETUsuario);
        ETPass = (EditText)findViewById(R.id.ETPass);
    }

    public void goToComprar(View v){
        Intent intent = new Intent(this, Prestamo.class);
        startActivity(intent);
    }

    public void goToAdministrar(View v){
        usuario = ETUsuario.getText().toString();
        pass = ETPass.getText().toString();
        if (usuario.equals("admin") && pass.equals("root")) {
            Intent intent = new Intent(this, Administrar.class);
            startActivity(intent);
        }
        else {
            ETUsuario.setText("");
            ETPass.setText("");
            Toast.makeText(this, "Usuario o contraseña incorrecto.", Toast.LENGTH_LONG).show();
        }
    }
}
