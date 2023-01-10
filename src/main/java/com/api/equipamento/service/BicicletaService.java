package com.api.equipamento.service;

import com.api.equipamento.model.*;
import com.api.equipamento.repositori.RepRede;
import com.api.equipamento.repositori.RepTranca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.equipamento.repositori.RepBicicleta;
import java.util.List;

@Service
public class BicicletaService{
    @Autowired
    private Mensage mensage;

    @Autowired
    private RepBicicleta bicicletaRep;

    @Autowired
    private RepRede repRede;

    @Autowired
    private RepTranca repTranca;
    @Autowired
    private RedeService serviceRede;

    public Bicicleta cadastrar(Bicicleta bicicleta){

        if(bicicleta.getModelo().equals("") || bicicleta.getAno().equals("") || bicicleta.getMarca().equals("")
                ){
            return null;
        }else {
            return bicicletaRep.save(bicicleta);
        }
    }

    public List<Bicicleta> listarBicicletas(){
        return bicicletaRep.findAll();
    }

    public Bicicleta bicicletaFindId( int id){
        if(bicicletaRep.countById(id) == 0){
            return null;
        }else {
            return bicicletaRep.findById(id);
        }
    }

    public Bicicleta alterarBicicleta(Bicicleta bike, int id){

        if(bike.getMarca().equals("") || bike.getModelo().equals("") || bike.getAno().equals("")
                ){
            return null;
        }else {
            Bicicleta bc = bicicletaRep.findById(id);
            bc.setMarca(bike.getMarca());
            bc.setModelo(bike.getModelo());
            bc.setAno(bike.getAno());
            bc.setNumero(bike.getNumero());

            return bicicletaRep.save(bc);
        }
    }

    public Mensage excluirBicicleta(int id){
        
        if(bicicletaRep.countById(id)==0){
            mensage.setMensage("Não encontrado");
            return mensage;
        }

        Bicicleta bc = bicicletaRep.findById(id);
        bicicletaRep.delete(bc);
        mensage.setMensage("Excluido");

        return mensage;
    }

    //Em contrução
    public void integrarNaRede(IdsEquipamentos dados){
        // TODO
        List<Rede> listaTotens = repRede.findAll();

        for (int i = 0; listaTotens.size() > i; i++) {
            Rede totem = listaTotens.get(i);
            List<Integer> listaTranca = totem.getIdTranca();
            for (int j = 0; listaTranca.size() > j; j++) {
                if (listaTranca.get(j) == dados.getIdTranca()) {
                    //busca a tranca e salva o id da bicicleta na tranca
                    Tranca trancaNova = repTranca.findById(dados.getIdTranca());
                    trancaNova.setBicicleta(dados.getIdBicicleta());
                    repTranca.save(trancaNova);
                    //salva o id da bicicleta no totem
                    List<Integer> listaBicicleta = totem.getIdBicicleta();
                    listaBicicleta.add(dados.getIdBicicleta());
                    repRede.save(totem);
                    return;
                }
            }
        }

    }

    public void retirarDaRede(IdsEquipamentos dados){
        // TODO
    }


}