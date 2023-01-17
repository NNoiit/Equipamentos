package com.api.equipamento.service;

import com.api.equipamento.model.Bicicleta;
import com.api.equipamento.model.Rede;
import com.api.equipamento.model.Tranca;
import com.api.equipamento.repositori.RepBicicleta;
import com.api.equipamento.repositori.RepRede;
import com.api.equipamento.repositori.RepTotem;
import com.api.equipamento.repositori.RepTranca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.api.equipamento.model.Totem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TotemService {
    @Autowired
    private RepTotem repTotem;
    @Autowired
    @Qualifier("Rede")
    private RepRede repRede;
    @Autowired
    @Qualifier("Tranca")
    private RepTranca repTranca;
    @Autowired
    @Qualifier("bicicletas")
    private RepBicicleta repBicicleta;

    public List<Totem> listaTotem(){
        return repTotem.findAll();
    }

    public Totem cadastrarTotem(Totem totem){
        if(!totem.getLocalizacao().equals("")) {
            return repTotem.save(totem);
        }
        return null;
    }

    public Totem mostrarTotem(UUID id) {
        return repTotem.findByUuid(id);
    }

    public Totem alterarTotem(Totem totemNovo, UUID id){
        if(repTotem.findByUuid(id) != null ) {
            Totem totemAlterado = repTotem.findByUuid(id);
            totemAlterado.setLocalizacao(totemNovo.getLocalizacao());
            return repTotem.save(totemAlterado);
        }
        return null;
    }

    public boolean excluirTotem(UUID id) {
        if(repTotem.findByUuid(id) != null){
            repTotem.delete(repTotem.findByUuid(id));
            return true;
        }
        return false;
    }


    public List<Tranca> listaTrancaTotem(UUID idTotem) {
        if(repRede.findByIdTotem(idTotem) != null) {
            Rede totem1 = repRede.findByIdTotem(idTotem);
            //lista contendo os ids das trancas no totem
            List<UUID> listaIdsTrancas = totem1.getIdTranca();
            //lista contendo as trancas no totem
            List<Tranca> listaTrancas = new ArrayList<>();
            for (int i = 0; listaIdsTrancas.size() > i; i++) {
                listaTrancas.add(repTranca.findByUuid(listaIdsTrancas.get(i)));
            }
            return listaTrancas;
        }
        return new ArrayList<>();
    }

    public List<Bicicleta> listaBicicletaTotem(UUID idTotem) {
        if(repRede.findByIdTotem(idTotem) != null) {
            Rede totem1 = repRede.findByIdTotem(idTotem);
            //lista contendo os ids das bicicletas presentes no totem
            List<UUID> listaIdsBicicletas = totem1.getIdBicicleta();
            //lista contendo as bicicletas no totem
            List<Bicicleta> listaBicicletas = new ArrayList<>();

            for (int i = 0; listaIdsBicicletas.size() > i; i++) {
                listaBicicletas.add(repBicicleta.findByUuid(listaIdsBicicletas.get(i)));
            }
            return listaBicicletas;
        }
        return new ArrayList<>();
    }
}
