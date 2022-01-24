package com.example.demo3;


public class place {

    int id, etage, numParking,reservation, categorie, typevoiture  ;
    String disponibilite;



    public void setId(int id) {
        this.id = id;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }

    public void setNumParking(int numParking) {
        this.numParking = numParking;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    public void setReservation(int reservation) {
        this.reservation = reservation;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public void setTypevoiture(int typevoiture) {
        this.typevoiture = typevoiture;
    }

    public int getId() {
        return id;
    }

    public int getEtage() {
        return etage;
    }

    public int getNumParking() {
        return numParking;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public int getReservation() {
        return reservation;
    }

    public int getCategorie() {
        return categorie;
    }

    public int getTypevoiture() {
        return typevoiture;
    }

    public place(int id, int etage, int numParking, String disponibilite, int reservation,int categorie, int typevoiture ) {
        this.id = id;
        this.etage = etage;
        this.numParking = numParking;
        this.disponibilite = disponibilite;
        this.reservation = reservation;
        this.categorie = categorie;
        this.typevoiture = typevoiture;
    }
}

