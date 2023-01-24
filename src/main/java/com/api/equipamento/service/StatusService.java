package com.api.equipamento.service;

import com.api.equipamento.model.Acao;
import com.api.equipamento.model.Status;
import com.api.equipamento.model.Tranca;
import com.api.equipamento.repositori.RepTranca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StatusService {
    @Autowired
    private RepTranca repTranca;

    public StatusService(RepTranca repTranca) {
        this.repTranca = repTranca;
    }

    public void alterarStatusTranca(UUID idTranca, Acao trancaDestranca){
        Tranca tranca1 = repTranca.findByUuid(idTranca);
        if(trancaDestranca.getDescricao().equals("trancar") || trancaDestranca.getDescricao().equals("TRANCAR")){
            tranca1.setStatus(Status.EM_USO);
            repTranca.save(tranca1);
        } else{
            tranca1.setStatus(Status.DISPONIVEL);
            repTranca.save(tranca1);
        }

    }

    public void inserirBicicletaTranca(UUID idTranca, UUID idBicicleta) {
        Tranca tranca1 = repTranca.findByUuid(idTranca);
        if(tranca1!= null && tranca1.getStatus() == Status.DISPONIVEL) {
            tranca1.setBicicleta(idBicicleta);
            tranca1.setStatus(Status.EM_USO);
            repTranca.save(tranca1);
        }
    }
}
