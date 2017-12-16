package com.example.utente5academy.provafinale.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.example.utente5academy.provafinale.R;

public class AddPackUser extends Activity {
    private EditText destinatario;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pack_user);


    }
}
