package com.example.usuario.apptension;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.usuario.apptension.Objetos.Paciente;
import com.example.usuario.apptension.Objetos.PacienteList;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;



public class MostrarPorSegSoc extends AppCompatActivity {
    private EditText editBusqueda;
    private ListView listbusqueda;
    private Spinner cmbOpciones;
    private TextView lblMensaje;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseREference;

    private List<Paciente> pacienteList= new ArrayList<Paciente>();
    private ArrayAdapter<Paciente> arrayAdapterPaciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_por_seg_soc);
        editBusqueda = (EditText) findViewById(R.id.txt_seguridad_social_busqueda);
        listbusqueda = (ListView) findViewById(R.id.Mostrardatos);

        inicializarFirebase();
        eventoEdit();
        final String[] datos = new String[]{"Filtrar Pacientes","Principal","Introducir Paciente"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);

        cmbOpciones = (Spinner)findViewById(R.id.CmbOpciones2);

        adaptador.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        cmbOpciones.setAdapter(adaptador);
        cmbOpciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {

                switch(position){


                    case 0:

                        break;


                    case 1:
                        Intent principal=new Intent(MostrarPorSegSoc.this,MainActivity.class);
                        startActivity(principal);

                        break;

                    case 2:
                        Intent introduce=new Intent(MostrarPorSegSoc.this,IntroducirDatos.class);
                        startActivity(introduce);

                        break;




                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
                lblMensaje.setText("");
            }
        });
    }



    private void eventoEdit() {
        editBusqueda.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String segsoc= editBusqueda.getText().toString().trim();
                comprobarSegsoc(segsoc);
            }
        });
    }

    private void comprobarSegsoc(String segsoc) {
        Query query;
        if(segsoc.equals("")){
            query=databaseREference.child("pacientes").orderByChild("num_segsoc");
        }else{
            query=databaseREference.child("pacientes").orderByChild("num_segsoc").startAt(segsoc).endAt(segsoc+"\uf8ff");
        }
        pacienteList.clear();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Paciente p = objSnapshot.getValue(Paciente.class);
                    pacienteList.add(p);
                }
                arrayAdapterPaciente=new ArrayAdapter<Paciente>(MostrarPorSegSoc.this, android.R.layout.simple_expandable_list_item_1,pacienteList);
                listbusqueda.setAdapter(arrayAdapterPaciente);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(MostrarPorSegSoc.this);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseREference=firebaseDatabase.getReference();
    }


    protected void onResume(){
        super.onResume();
        comprobarSegsoc("");

    }
}

