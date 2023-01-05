package com.api.equipamento.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mensage {
    private String mensage;

    private List<Mensage> listMensagens;

    public String getMensage(){
        return mensage;
    }
    public void setMensage(String mensage){
        this.mensage = mensage;
    }

}
