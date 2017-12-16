package com.example.utente5academy.provafinale.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.utente5academy.provafinale.R;

import java.util.ArrayList;

/**
 * Created by Utente on 14/12/2017.
 */

public class AdapterCorriere extends RecyclerView.Adapter<AdapterCorriere.ViewHolder> {

    private Context context;
    private ArrayList<String> lista;
    private SharedPreferences pref;

    public AdapterCorriere(Context cx, ArrayList<String> list, SharedPreferences preferences) {
        this.context = cx;
        this.lista = list;
        this.pref = preferences;
    }


    @Override
    public AdapterCorriere.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_recyclerview, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterCorriere.ViewHolder holder, int position) {
       final String pacco = lista.get(position);

        holder.btn.setText(pacco);
        final SharedPreferences.Editor editor = pref.edit();
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("codicepacco", pacco);
                editor.commit();
                editor.apply();

            }
        });


    }

    @Override
    public int getItemCount() {
        return this.lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public Button btn;

        public ViewHolder(View itemView) {
            super(itemView);
            btn = (Button) itemView.findViewById(R.id.button);
        }
    }
}
