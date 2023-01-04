package com.api.equipamento.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;


@Entity
public class Rede {

    @Id
    private int Id;

    private int idTotem;
    private List<Integer> idTranca;
    private List<Integer> idBicicleta;


    public int getIdTotem() {
        return idTotem;
    }

    public void setIdTotem(int idTotem) {
        this.idTotem = idTotem;
    }

    public List<Integer> getIdTranca() {
        return idTranca;
    }

    public void setIdTranca(List<Integer> idTranca) {
        this.idTranca = idTranca;
    }

    public List<Integer> getIdBicicleta() {
        return idBicicleta;
    }

    public void setIdBicicleta(List<Integer> idBicicleta) {
        this.idBicicleta = idBicicleta;
    }

    public int getId() {
        return Id;
    }
}
