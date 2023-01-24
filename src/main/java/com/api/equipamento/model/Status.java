package com.api.equipamento.model;

public enum Status {
    DISPONIVEL("disponivel"),
    EM_USO("em uso"),
    NOVA("nova"), 
    APOSENTADA("aposentada"),
    EM_REPARO("em reparo"),
    REPARO_SOLICITADO("reparo solicitado");

    private final String descricao;

    Status(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }
}