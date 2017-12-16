package com.example.utente5academy.provafinale.adapter;

import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.utente5academy.provafinale.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Utente on 16/12/2017.
 */

public class AdapterPacksList extends RecyclerView.Adapter<AdapterPacksList.ViewHolder> {
    private android.content.Context context;
    private ArrayList<String> lista;
    private SharedPreferences preferences;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    public AdapterPacksList(android.content.Context cx, ArrayList<String> list,SharedPreferences preferences) {
        this.lista = list;
        this.context = cx;
        this.preferences=preferences;
    }

    @Override
    public AdapterPacksList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_pack_courrier, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterPacksList.ViewHolder holder, int position) {
        final String pacco=lista.get(position);
        holder.button.setText(pacco);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeStatus(pacco);

            }
        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public Button button;

        public ViewHolder(View itemView) {
            super(itemView);
            button=(Button)itemView.findViewById(R.id.but);
        }
    }
    public void changeStatus(String codice){
        database = FirebaseDatabase.getInstance();
        String url="https://provafinale-f0d57.firebaseio.com/Pacchi/"+codice;
        reference = database.getReferenceFromUrl(url);
        reference.child("stato").setValue("consegna");
    }



}
