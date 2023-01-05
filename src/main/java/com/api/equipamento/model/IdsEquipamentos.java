package com.api.equipamento.model;

import org.springframework.stereotype.Component;

@Component
public class IdsEquipamentos {

    private int idTotem;

    private int idTranca;

    private int idBicicleta;

    public int getIdTotem() {
        return idTotem;
    }

    public int getIdTranca() {
        return idTranca;
    }

    public int getIdBicicleta(){
        return idBicicleta;
    }
}
