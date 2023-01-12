package com.api.equipamento.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Rede {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int idTotem;
    private List<Integer> idTranca = new ArrayList<>();
    private List<Integer> idBicicleta = new ArrayList<>();

    public int getIdTotem() {
        return idTotem;
    }

    public void setIdTotem(int idTotem) {
        this.idTotem = idTotem;
    }

    public List<Integer> getIdTranca() {
        return idTranca;
    }

    public void setIdTranca(List<Integer> idTranca){
        this.idTranca = idTranca;
    }
    public List<Integer> getIdBicicleta() {
        return idBicicleta;
    }

    public void setIdBicicleta(List<Integer> idBicicleta) {
        this.idBicicleta = idBicicleta;
    }

    public void setId(int id){
        this.id = id;
    }
}
