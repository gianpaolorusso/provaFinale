package com.example.utente5academy.provafinale.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.naming.Context;

/**
 * Created by Utente on 16/12/2017.
 */

public class AdapterPacksList extends RecyclerView.Adapter<AdapterPacksList.ViewHolder> {
    private Context context;
    private ArrayList<String> lista;
    public AdapterPacksList(Context cx, ArrayList<String> list){
        this.lista=list;
        this.context=cx;
    }
    @Override
    public AdapterPacksList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(AdapterPacksList.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
