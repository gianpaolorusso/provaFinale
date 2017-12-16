package com.example.utente5academy.provafinale.activitys;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.utente5academy.provafinale.R;
import com.example.utente5academy.provafinale.adapter.AdapterCorriere;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import cz.msebera.android.httpclient.Header;

public class Tab2 extends Activity {
    private ArrayList<String> pacchi;
    private RecyclerView recyclerView;
    private AsyncHttpClient client;
    private AdapterCorriere adapter;
    private SharedPreferences preferences;
    private ArrayList<String> codici;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab2);
        preferences = getSharedPreferences("login", MODE_PRIVATE);
        recyclerView = (RecyclerView) findViewById(R.id.rec);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(manager);
        pacchi = new ArrayList<String>();
        codici = new ArrayList<String>();
        client = new AsyncHttpClient();
        adapter = new AdapterCorriere(getBaseContext(), pacchi, preferences);
        recyclerView.setAdapter(adapter);
        getCodicePacchi();
    }

    public void getCodicePacchi() {
        String username = preferences.getString("username", "vuoto");
        String tipo = preferences.getString("tipo", "vuoto");
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
                    getPacchiInConsegna(codici.get(i));


                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable
                    error) {

            }
        });

    }


    public void getPacchiInConsegna(final String codice) {
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
                        if (jsonObject.getString("stato").equals("consegna"))
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
