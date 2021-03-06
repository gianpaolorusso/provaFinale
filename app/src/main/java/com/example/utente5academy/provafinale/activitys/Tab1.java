package com.example.utente5academy.provafinale.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.utente5academy.provafinale.R;
import com.example.utente5academy.provafinale.adapter.AdapterPacksList;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Utente on 14/12/2017.
 */

public class Tab1 extends Activity {
    private RecyclerView recyclerView;

    private String username;
    private ArrayList<String> pacchi;
    private String tipo;
    private AsyncHttpClient client;
    private AdapterPacksList adapter;
    private Context context;
    private ArrayList<String> codici;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tab1);
        client = new AsyncHttpClient();
        codici = new ArrayList<String>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        username = getIntent().getStringExtra("username");
        SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
        tipo = getIntent().getStringExtra("tipo");
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", username);
        editor.putString("tipo", tipo);
        editor.commit();
        editor.apply();
        pacchi = new ArrayList<String>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new AdapterPacksList(getBaseContext(), pacchi, preferences);
        recyclerView.setAdapter(adapter);
        getCodicePacchi();
    }

    public void getCodicePacchi() {
        String url = "https://provafinale-f0d57.firebaseio.com/Users/" + tipo + "/" + username + "/pacchi.json";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                JSONObject jsonObject = null;
                String objReceived = new String(responseBody);
                try {
                    jsonObject = new JSONObject(objReceived);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (jsonObject != null) {
                    Iterator<String> key = jsonObject.keys();
                    if (jsonObject.length() == 1) {
                        try {
                            String codice = (String) jsonObject.get(key.next());
                            codici.add(codice);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        while (key.hasNext()) {
                            String codice = null;
                            try {
                                codice = (String) jsonObject.get(key.next());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            codici.add(codice);
                        }
                    }
                }
                for (int i = 0; i < codici.size(); i++) {
                    getPacchiInAttesa(codici.get(i));
                }
                if(pacchi.size()<1)
                {
                    Toast.makeText(getBaseContext(),"Nessun pacco in attesa",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable
                    error) {

            }
        });

    }

    public void getPacchiInAttesa(final String codice) {
        String url = "https://provafinale-f0d57.firebaseio.com/Pacchi/" + codice + ".json";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                JSONObject jsonObject = null;
                String objReceived = new String(responseBody);
                try {
                    jsonObject = new JSONObject(objReceived);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (jsonObject != null) {
                    try {
                        if (jsonObject.getString("stato").equals("attesa"))
                            pacchi.add(codice);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable
                    error) {

            }
        });
    }


}
