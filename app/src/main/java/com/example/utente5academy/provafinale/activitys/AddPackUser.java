package com.example.utente5academy.provafinale.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.utente5academy.provafinale.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import cz.msebera.android.httpclient.Header;

public class AddPackUser extends Activity {
    private EditText destinatario;
    private Spinner spinnerCorrieri;
    private EditText destinazione;
    private Spinner spinnerMese;
    private Spinner spinnerGiorno;
    private Spinner spinnerAnno;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pack_user);
        spinnerCorrieri = (Spinner) findViewById(R.id.spinnerCorrieri);
        spinnerGiorno = (Spinner) findViewById(R.id.spinnerGiorno);
        spinnerMese = (Spinner) findViewById(R.id.spinnerMese);
        spinnerAnno = (Spinner) findViewById(R.id.spinnerAnno);
        publicSpinnerGiorno();
        publicSpinnerCorrieri();
        publicSpinnerMese();


    }

    private void publicSpinnerCorrieri() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://provafinale-f0d57.firebaseio.com/Users/Corriere.json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String stringaJson = new String(responseBody);
                JSONObject json = null;
                try {
                    json = new JSONObject(stringaJson);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Iterator chiavi = json.keys();
                ArrayList<String> corrieri = new ArrayList<String>();
                while (chiavi.hasNext()) {
                    String corriere = (String) chiavi.next();
                    corrieri.add(corriere);
                }
                ArrayAdapter adapter = new ArrayAdapter(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, corrieri);
                spinnerCorrieri.setAdapter(adapter);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public void publicSpinnerGiorno() {
        ArrayList<String> lista = new ArrayList<String>();
        for (int i = 0; i < 32; i++) {
            lista.add(String.valueOf(i));
        }
        ArrayAdapter adapter = new ArrayAdapter(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, lista);
        spinnerGiorno.setAdapter(adapter);
    }

    public void publicSpinnerMese() {
        String[] mesi = {"GENNAIO", "FEBBRAIO", "MARZO", "APRILE", "MAGGIO", "APRILE", "MAGGIO", "GIUGNO", "LUGLIO", "AGOSTO", "SETTEMBRE", "OTTOBRE", "NOVEMBRE", "DICEMBRE"};
        ArrayAdapter adapter = new ArrayAdapter(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, mesi);
        spinnerMese.setAdapter(adapter);

    }

}
