package com.example.usuario.apptension.Objetos;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.example.usuario.apptension.IntroducirDatos;
import com.example.usuario.apptension.R;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

/**
 * Created by USUARIO on 28/12/2017.
 */

public class PacienteList extends ArrayAdapter<Paciente>{
    private Activity context;
    private List<Paciente> pacientes;
    DatabaseReference databaseReference;
    EditText editName;
    EditText editLast_Name;
    EditText editSegSoc;
    EditText editsistole;
    EditText editdiastole;

    public PacienteList(@NonNull Activity context, List<Paciente> pacientes, DatabaseReference databaseReference, EditText editName,EditText editLast_Name,EditText editSegSoc,EditText editsistole,EditText editdiastole) {
        super(context, R.layout.activity_introducir_datos,pacientes);
        this.context=context;
        this.pacientes=pacientes;
        this.databaseReference=databaseReference;
        this.editName=editName;
        this.editLast_Name=editLast_Name;
        this.editSegSoc=editSegSoc;
        this.editsistole=editsistole;
        this.editdiastole=editdiastole;
    }

    public View getView(int pos, View view, ViewGroup parent){
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem= inflater.inflate(R.layout.activity_introducir_datos,null,true);
        TextView txtName=(TextView) listViewItem.findViewById(R.id.txt_name);
        TextView txtlastname=(TextView) listViewItem.findViewById(R.id.txt_last_name);
        TextView txtsegsoc=(TextView) listViewItem.findViewById(R.id.txt_segsoc);
        TextView txtsistole=(TextView) listViewItem.findViewById(R.id.txt_sistole);
        TextView txtdiastole=(TextView) listViewItem.findViewById(R.id.txt_diastole);
        Button btnDelete=(Button) listViewItem.findViewById(R.id.Btndelete);
        Button btnUpdate=(Button) listViewItem.findViewById(R.id.Btnupdate);
        final Paciente paciente = pacientes.get(pos);
        txtName.setText(paciente.getNombre());
        txtlastname.setText(paciente.getApellido());
        txtsegsoc.setText(paciente.getNum_segsoc());
        txtsistole.setText(paciente.getSistole());
        txtdiastole.setText(paciente.getDiastole());

        btnDelete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                databaseReference.child(paciente.getId()).removeValue();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    editName.setText(paciente.getNombre());
                    editLast_Name.setText(paciente.getApellido());
                    editSegSoc.setText(paciente.getNum_segsoc());
                    editsistole.setText(paciente.getSistole());
                    editdiastole.setText(paciente.getDiastole());
                    IntroducirDatos.pacienteId=paciente.getId();
            }

        });
        return listViewItem;

    }


}
