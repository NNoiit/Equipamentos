package com.api.equipamento.model;

import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class Erro {

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

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
