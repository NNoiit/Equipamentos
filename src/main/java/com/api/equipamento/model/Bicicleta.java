package com.api.equipamento.model;

import jakarta.persistence.*;



@Entity
public class Bicicleta {
    @Id
    private int id;
    private String marca;
    private String modelo;
    private String ano;
    private int numero;

    public StatusBike status;

    public StatusBike getStatus(){
        return status;
    }

    public void setStatusBike(){
        this.status = status;
    }

    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

}
