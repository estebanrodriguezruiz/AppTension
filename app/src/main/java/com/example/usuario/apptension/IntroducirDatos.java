package com.example.usuario.apptension;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.apptension.Objetos.Paciente;
import com.example.usuario.apptension.Objetos.PacienteList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class IntroducirDatos extends AppCompatActivity {

    Button Btnenviar;
    private TextView lblMensaje;
    private Spinner cmbOpciones;
    EditText editName;
    EditText editLast_Name;
    EditText editSegSoc;
    EditText editsistole;
    EditText editdiastole;
    DatabaseReference databaseReference;
    List<Paciente> pacienteList;
    ListView listViewPacientes;
    public static String pacienteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introducir_datos);
        pacienteList = new ArrayList<Paciente>();
        databaseReference = FirebaseDatabase.getInstance().getReference("pacientes");
        Btnenviar = (Button) findViewById(R.id.Btnenviar);
        editName = (EditText) findViewById(R.id.txt_name);
        editLast_Name = (EditText) findViewById(R.id.txt_last_name);
        editSegSoc = (EditText) findViewById(R.id.txt_segsoc);
        editdiastole = (EditText) findViewById(R.id.txt_diastole);
        editsistole = (EditText) findViewById(R.id.txt_sistole);
        listViewPacientes= (ListView) findViewById(R.id.listViewPacientes);
        final String[] datos = new String[]{"Introducir Paciente","Principal","Filtrar Pacientes"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);

        cmbOpciones = (Spinner)findViewById(R.id.CmbOpciones3);

        adaptador.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        cmbOpciones.setAdapter(adaptador);
        cmbOpciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {

                switch(position){


                    case 0:
                        break;


                    case 1:
                        Intent introduce=new Intent(IntroducirDatos.this,MainActivity.class);
                        startActivity(introduce);
                        break;

                    case 2:
                        Intent buscador_segsoc=new Intent(IntroducirDatos.this,MostrarPorSegSoc.class);
                        startActivity(buscador_segsoc);
                        break;




                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
                lblMensaje.setText("");
            }
        });


        Btnenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editName.getText().toString();
                String last_name = editLast_Name.getText().toString();
                String segsoc = editSegSoc.getText().toString();
                String diastole = editdiastole.getText().toString();
                String sistole = editsistole.getText().toString();
                if (TextUtils.isEmpty(pacienteId)) {
                    //save
                    String id = databaseReference.push().getKey();

                    Paciente paciente = new Paciente(name, last_name, segsoc, sistole, diastole, id);
                    databaseReference.child(id).setValue(paciente);
                    Toast.makeText(IntroducirDatos.this, "Paciente creado con éxito", Toast.LENGTH_SHORT).show();
                } else {
                    //update
                    databaseReference.child(pacienteId).child("name").setValue(name);
                    databaseReference.child(pacienteId).child("last name").setValue(last_name);
                    databaseReference.child(pacienteId).child("num_segsoc").setValue(segsoc);
                    databaseReference.child(pacienteId).child("diastole").setValue(diastole);
                    databaseReference.child(pacienteId).child("sistole").setValue(sistole);

                    Toast.makeText(IntroducirDatos.this, "Paciente Modificado con éxito", Toast.LENGTH_SHORT).show();
                }
                editName.setText("");
                editSegSoc.setText("");
                editdiastole.setText("");
                editLast_Name.setText("");
                editsistole.setText("");


            }
        });
    }
    protected void onStart(){
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pacienteList.clear();
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    Paciente paciente= postSnapshot.getValue(Paciente.class);
                    pacienteList.add(paciente);
                }
                PacienteList pacienteAdapter= new PacienteList(IntroducirDatos.this,pacienteList,databaseReference,editName,editLast_Name,editSegSoc,editsistole,editdiastole);
                listViewPacientes.setAdapter(pacienteAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

