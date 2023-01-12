package com.api.equipamento.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Tranca {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int numero;
    @NotBlank
    private String localizacao;
    @NotBlank
    private String anoDeFabricacao;
    @NotBlank
    private String modelo;
   @GeneratedValue(strategy = GenerationType.AUTO)
    private int bicicletaId;

    public Status status;
    public Status getStatus(){
        return status;
    }

    public void setStatus(Status status){
        this.status = status;
    }


    public int getId(){
        return id;
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

    public int getBicicleta() {
        return bicicletaId;
    }

    public void setBicicleta(int bicicleta) {
        this.bicicletaId = bicicleta;
    }
}
