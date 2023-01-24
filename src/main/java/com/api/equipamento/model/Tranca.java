package com.api.equipamento.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Entity
public class Tranca {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private int numero;
    @NotBlank
    private String localizacao;
    @NotBlank
    private String anoDeFabricacao;
    @NotBlank
    private String modelo;
   @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID bicicleta;

    public Status status;
    public Status getStatus(){
        return status;
    }

    public void setStatus(Status status){
        this.status = status;
    }


    public UUID getUuid(){
        return uuid;
    }
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getAnoDeFabricacao() {
        return anoDeFabricacao;
    }

    public void setAnoDeFabricacao(String anoDeFabricacao) {
        this.anoDeFabricacao = anoDeFabricacao;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public UUID getBicicleta() {
        return bicicleta;
    }

    public void setBicicleta(UUID bicicleta) {
        this.bicicleta = bicicleta;
    }
}
