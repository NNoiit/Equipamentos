package com.api.equipamento.service;

import com.api.equipamento.repositori.RepTotem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.equipamento.model.Totem;
import java.util.List;

@Service
public class TotemService {

    @Autowired
    private RepTotem repTotem;

    public List<Totem> listaTotem(){
        return repTotem.findAll();
    }

    public Totem cadastrarTotem(Totem totem){
        return repTotem.save(totem);
    }

    public Totem mostrarTotem(int id) {
        return repTotem.findById(id);
    }

    public Totem alterarTotem(Totem totemNovo, int id){
        Totem totemAlterado = repTotem.findById(id);
        totemAlterado.setLocalizacao(totemNovo.getLocalizacao());
        return repTotem.save(totemAlterado);
    }

    public void excluirTotem(int id) {
        repTotem.delete(repTotem.findById(id));
    }
}
