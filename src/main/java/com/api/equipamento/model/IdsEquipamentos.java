package com.api.equipamento.model;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IdsEquipamentos {

    private UUID totem;

    private UUID tranca;

    private UUID bicicleta;

    public UUID getTotem() {
        return totem;
    }

    public UUID getTranca() {
        return tranca;
    }

    public UUID getBicicleta(){
        return bicicleta;
    }
}
