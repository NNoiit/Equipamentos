package com.api.equipamento.model;

public enum Status {
    LIVRE("livre"),
    OCUPADO("ocupado"),
    NOVA("nova"), 
    APOSENTADA("aposentada"),
    EM_REPARO("em reparo");

    private final String descricao;

    Status(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }
}