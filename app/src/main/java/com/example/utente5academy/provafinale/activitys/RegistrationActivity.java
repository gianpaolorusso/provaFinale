package com.example.utente5academy.provafinale.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.utente5academy.provafinale.R;
import com.example.utente5academy.provafinale.MainActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {
    private EditText eUsername;
    private EditText ePassword;
    private Button bRegistra;
    private String URLDB;
    private Spinner sTipo;
    private FirebaseDatabase dbFirebase;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        this.setTitle("REGISTRAZIONE");
        eUsername = (EditText) findViewById(R.id.edUsername);
        ePassword = (EditText) findViewById(R.id.testoPassword);
        bRegistra = (Button) findViewById(R.id.bRegistra);
        sTipo = (Spinner) findViewById(R.id.spinner);
        dbFirebase = FirebaseDatabase.getInstance();
        reference = dbFirebase.getReferenceFromUrl("https://provafinale-f0d57.firebaseio.com/Users");


        String[] array = {"Utente", "Corriere"};
        ArrayAdapter adapter = new ArrayAdapter(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, array);
        sTipo.setAdapter(adapter);
        bRegistra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eUsername.getText().toString().equals("")) {
                    Toast.makeText(getBaseContext(), "Inserire l'Username", Toast.LENGTH_SHORT).show();
                }
                if (ePassword.getText().toString().equals("")) {
                    Toast.makeText(getBaseContext(), "Inserire la Password", Toast.LENGTH_SHORT).show();
                } else {
                    bRegistra.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String tipo = sTipo.getSelectedItem().toString();

                            String User = eUsername.getText().toString().toUpperCase();
                            String password = ePassword.getText().toString();
                            reference.child(tipo).child(User).child("Password").setValue(password);
                            Toast.makeText(getBaseContext(), "Utente registrato", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(RegistrationActivity.this, MainActivity.class);
                            startActivity(i);
                        }
                    });
                }
            }
        });

    }
}
