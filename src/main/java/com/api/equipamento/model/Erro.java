package com.api.equipamento.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Erro {
    private String mensage;

    private List<Erro> listMensagens;

    public String getMensage(){
        return mensage;
    }
    public void setMensage(String mensage){
        this.mensage = mensage;
    }

}
