package com.example.usuario.apptension;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button comenzar;
    private TextView lblMensaje;
    private Spinner cmbOpciones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        comenzar=(Button)findViewById(R.id.BtnAceptar);
        lblMensaje=(TextView)findViewById(R.id.editText);

        comenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent comenzar= new Intent(MainActivity.this, IntroducirDatos.class);
                startActivity(comenzar);
            }
        });
        final String[] datos = new String[]{"Principal","Introducir Paciente","Filtrar Pacientes"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);

        cmbOpciones = (Spinner)findViewById(R.id.CmbOpciones);

        adaptador.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        cmbOpciones.setAdapter(adaptador);
        cmbOpciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {

                        switch(position){


                            case 0:
                                break;


                            case 1:
                                Intent introduce=new Intent(MainActivity.this,IntroducirDatos.class);
                                startActivity(introduce);
                                break;

                            case 2:
                                Intent buscador_segsoc=new Intent(MainActivity.this,MostrarPorSegSoc.class);
                                startActivity(buscador_segsoc);
                                break;




                        }
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        lblMensaje.setText("");
                    }
                });
    }


}
