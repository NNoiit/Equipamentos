package com.api.equipamento.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
import java.util.UUID;


@Component
public class Erro {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private String codigo;
    private String mensage;

    public String getMensage(){
        return mensage;
    }
    public void setMensage(String mensage){
        this.mensage = mensage;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
