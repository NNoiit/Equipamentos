package com.api.equipamento.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
public class Rede {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private UUID idTotem;
    private List<UUID> idTranca = new ArrayList<UUID>();
    private List<UUID> idBicicleta = new ArrayList<UUID>();

    public UUID getIdTotem() {
        return idTotem;
    }

    public void setIdTotem(UUID idTotem) {
        this.idTotem = idTotem;
    }

    public List<UUID> getIdTranca() {
        return idTranca;
    }

    public void setIdTranca(List<UUID> idTranca){
        this.idTranca = idTranca;
    }
    public List<UUID> getIdBicicleta() {
        return idBicicleta;
    }

    public void setIdBicicleta(List<UUID> idBicicleta) {
        this.idBicicleta = idBicicleta;
    }

    public void setId(int id){
        this.id = id;
    }
}
