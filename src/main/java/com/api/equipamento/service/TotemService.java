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
    @Autowired
    private RedeService serviceRede;

    public List<Totem> listaTotem(){
        return repTotem.findAll();
    }

    public Totem cadastrarTotem(Totem totem){
        Totem totem1 = repTotem.save(totem);
        serviceRede.criarRedeId(totem1.getId());
        return totem1;
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

    //Metodo criado para zerar os totens presentes no BD
    /*public void removerRedes(){
        serviceRede.removerDaRede();
    }*/
}
