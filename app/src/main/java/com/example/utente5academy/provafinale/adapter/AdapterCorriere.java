package com.example.utente5academy.provafinale.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.utente5academy.provafinale.R;

import com.example.utente5academy.provafinale.classe.Pacco;

import java.util.ArrayList;

/**
 * Created by Utente on 14/12/2017.
 */

public class AdapterCorriere extends RecyclerView.Adapter<AdapterCorriere.ViewHolder> {

    private Context context;
    private ArrayList<Pacco> lista;

    public AdapterCorriere(Context cx, ArrayList<Pacco> list) {
        this.context = cx;
        this.lista = list;
    }


    @Override
    public AdapterCorriere.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_recyclerview, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterCorriere.ViewHolder holder, int position) {
        Pacco pacco=lista.get(position);
        holder.indirizzo.setText(pacco.getIndirizzoConsegna());
        holder.destinatario.setText(pacco.getDestinatario());
        holder.codice.setText(pacco.getCodice());
        holder.dimensione.setText(pacco.getDimensione());
        holder.deposito.setText(pacco.getDeposito());



    }

    @Override
    public int getItemCount() {
        return this.lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CardView card;
        public TextView codice;
        public TextView dimensione;
        public TextView indirizzo;
        public TextView deposito;
        public TextView destinatario;

        public ViewHolder(View itemView) {
            super(itemView);
            deposito=(TextView)itemView.findViewById(R.id.deposito);
            dimensione=(TextView)itemView.findViewById(R.id.dimensione);
            card = (CardView) itemView.findViewById(R.id.card);
            codice=(TextView)itemView.findViewById(R.id.codicepacco);
            destinatario=(TextView)itemView.findViewById(R.id.destinatario);
            indirizzo=(TextView) itemView.findViewById(R.id.indirizzo);
        }
    }
}
