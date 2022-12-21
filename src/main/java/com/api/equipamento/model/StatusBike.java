package com.api.equipamento.model;

import jakarta.persistence.Enumerated;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

public enum StatusBike{
    LIVRE("livre"),
    OCUPADO("ocupado"),
    NOVA("nova"), 
    APOSENTADA("aposentada"),
    EM_REPARO("em reparo");

    private final String descricao;

    StatusBike(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }
}