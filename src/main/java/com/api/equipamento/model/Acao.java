package com.api.equipamento.model;

public enum Acao {
    TRANCAR("trancar"),
    DESTRANCAR("destancar");

    private final String descricao;

    Acao(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }
}
