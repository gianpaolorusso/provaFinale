package com.example.utente5academy.provafinale.classe;

/**
 * Created by Utente on 14/12/2017.
 */

public class Pacco {
    private String dataConsegna; private String destinatario;
    private String deposito;
    private String dimensione;
    private String indirizzoConsegna;
    private  String codice;

    public String getDataConsegna() {
        return dataConsegna;
    }

    public void setDataConsegna(String dataConsegna) {
        this.dataConsegna = dataConsegna;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getDeposito() {
        return deposito;
    }

    public void setDeposito(String deposito) {
        this.deposito = deposito;
    }

    public String getDimensione() {
        return dimensione;
    }

    public void setDimensione(String dimensione) {
        this.dimensione = dimensione;
    }

    public String getIndirizzoConsegna() {
        return indirizzoConsegna;
    }

    public void setIndirizzoConsegna(String indirizzoConsegna) {
        this.indirizzoConsegna = indirizzoConsegna;
    }


    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }
}
