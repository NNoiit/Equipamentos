package com.api.equipamento.model;

@Enum
public enum StatusBike{
    LIVRE("livre"),
    OCUPADO("ocupado"),
    NOVA("nova"), 
    APOSENTADA("aposentada")', 
    EM_REPARO("em reparo");

    private String descricao;

    StatusBike(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }
}