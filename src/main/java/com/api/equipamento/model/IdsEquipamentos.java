package com.api.equipamento.model;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IdsEquipamentos {

    private UUID idTotem;

    private UUID idTranca;

    private UUID idBicicleta;

    public UUID getIdTotem() {
        return idTotem;
    }

    public UUID getIdTranca() {
        return idTranca;
    }

    public UUID getIdBicicleta(){
        return idBicicleta;
    }
}
