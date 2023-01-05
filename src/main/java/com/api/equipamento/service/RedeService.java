package com.api.equipamento.service;

import com.api.equipamento.model.Rede;
import com.api.equipamento.repositori.RepRede;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedeService {

    @Autowired
    private RepRede repRede;

    public Rede criarRedeId(int id){
        Rede novaRede = new Rede();
        novaRede.setId(id);
        novaRede.setIdTotem(id);
        return repRede.save(novaRede);
    }

    public void removerDaRede(){
        repRede.deleteAll();
    }
}
