package com.example.utente5academy.provafinale.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.utente5academy.provafinale.R;
import com.example.utente5academy.provafinale.adapter.AdapterPacksList;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import javax.naming.Context;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Utente on 14/12/2017.
 */

public class Tab1 extends Activity {
    private RecyclerView recyclerView;
    private String username;
    private ArrayList<String> lista;
    private String tipo;
    private AsyncHttpClient client;
    private AdapterPacksList adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tab1);
        client = new AsyncHttpClient();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        username = getIntent().getStringExtra("username");
        tipo = getIntent().getStringExtra("tipo");
        lista = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new AdapterPacksList((Context) getBaseContext(),lista);
        recyclerView.setAdapter(adapter);
        getPacchiCorriere(username, tipo);


    }


    public void getPacchiCorriere(String username, String tipo) {
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
                            lista.add(codice);
                            adapter.notifyDataSetChanged();
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
                            lista.add(codice);
                        }
                        adapter.notifyDataSetChanged();
                    }


                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable
                    error) {

            }
        });
    }

}
