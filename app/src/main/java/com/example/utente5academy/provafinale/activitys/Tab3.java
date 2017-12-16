package com.example.utente5academy.provafinale.activitys;

import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.example.utente5academy.provafinale.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class Tab3 extends FragmentActivity implements OnMapReadyCallback {
    private String codice;
    private TextView indirizzo;
    private TextView giorno;
    private TextView destinatario;
    private TextView dimensione;
    private TextView cod;
    private AsyncHttpClient client;
    private SupportMapFragment mappa;
    private Geocoder geocoder;
    private List<Address> via = null;
    private Double latitudine = 0.0;
    private Double longitudine = 0.0;
    private Address adress = null;
    private LatLng coordinate = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab3);
        mappa = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        client = new AsyncHttpClient();
        SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
        codice = preferences.getString("codicepacco", "vuoto");
        cod = (TextView) findViewById(R.id.cod);
        cod.setText(codice);
        dimensione = (TextView) findViewById(R.id.dim);
        indirizzo = (TextView) findViewById(R.id.indirizzo);
        giorno = (TextView) findViewById(R.id.giorno);
        destinatario = (TextView) findViewById(R.id.destinatario);
        if (codice != "vuoto") {
            mappa.getMapAsync(this);

        }
    }


    @Override
    public void onMapReady(final GoogleMap googleMap) {

        final String url = "https://provafinale-f0d57.firebaseio.com/Pacchi/.json";
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
                            while (key.hasNext()) {
                                String obj = key.next();
                                JSONObject json = null;
                                try {
                                    json = (JSONObject) jsonObject.get(obj);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    giorno.setText(json.getString("dataconsegna"));
                                    destinatario.setText(json.getString("destinatario"));
                                    indirizzo.setText(json.getString("indirizzo"));
                                    dimensione.setText(json.getString("dimensione"));
                                    geocoder = new Geocoder(getBaseContext());
                                    via = geocoder.getFromLocationName(json.getString("indirizzo"), 1);
                                    adress = via.get(0);
                                    latitudine = adress.getLatitude();
                                    longitudine = adress.getLongitude();
                                    coordinate = new LatLng(latitudine, longitudine);
                                    googleMap.addMarker(new MarkerOptions().position(coordinate));
                                    CameraUpdate position = CameraUpdateFactory.newLatLngZoom(coordinate, 6);
                                    googleMap.animateCamera(position);
                                    googleMap.moveCamera(position);
                                } catch (
                                        IOException e)

                                {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable
                            error) {

                    }
                }
        );


    }
}
