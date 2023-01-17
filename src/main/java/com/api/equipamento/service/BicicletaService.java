package com.api.equipamento.service;

import com.api.equipamento.model.*;
import com.api.equipamento.repositori.RepRede;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.equipamento.repositori.RepBicicleta;
import java.util.List;
import java.util.UUID;

@Service
public class BicicletaService{
    @Autowired
    private Erro mensage;

    @Autowired
    private RepBicicleta bicicletaRep;

    @Autowired
    private RepRede repRede;

    @Autowired
    private StatusService statusService;

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

    public Bicicleta bicicletaFindId(UUID id){
        if(bicicletaRep.countByUuid(id) == 0){
            return null;
        }else {
            return bicicletaRep.findByUuid(id);
        }
    }

    public Bicicleta alterarBicicleta(Bicicleta bike, UUID id){

        if(bike.getMarca().equals("") || bike.getModelo().equals("") || bike.getAno().equals("")
                ){
            return null;
        }else {
            Bicicleta bc = bicicletaRep.findByUuid(id);
            bc.setMarca(bike.getMarca());
            bc.setModelo(bike.getModelo());
            bc.setAno(bike.getAno());
            bc.setNumero(bike.getNumero());

            return bicicletaRep.save(bc);
        }
    }

    public Erro excluirBicicleta(UUID id){
        
        if(bicicletaRep.countByUuid(id)==0){
            mensage.setMensage("Não encontrado");
            return mensage;
        }

        Bicicleta bc = bicicletaRep.findByUuid(id);
        bicicletaRep.delete(bc);
        mensage.setMensage("Dados removidos");

        return mensage;
    }

    public Erro alterarStatusBicicleta(UUID idBicicleta, Status status) {
        if (bicicletaRep.countByUuid(idBicicleta) == 1) {
            Bicicleta bicicleta1 = bicicletaRep.findByUuid(idBicicleta);
            bicicleta1.setStatusBike(status);
            mensage.setMensage("Ação bem sucedida");
            return mensage;
        } else {
            mensage.setMensage("Não encontrado");
            return mensage;
        }
    }

    public boolean integrarNaRede(IdsEquipamentos dados){
        List<Rede> listaTotens = repRede.findAll();

        for (int i = 0; listaTotens.size() > i; i++) {
            Rede totem = listaTotens.get(i);
            List<UUID> listaTranca = totem.getIdTranca();
            for (int j = 0; listaTranca.size() > j; j++) {
                if (listaTranca.get(j) == dados.getIdTranca()) {
                    //busca a tranca e salva o id da bicicleta na tranca
                    statusService.inserirBicicletaTranca(dados.getIdTranca(), dados.getIdBicicleta());
                    //salva o id da bicicleta no totem
                    List<UUID> listaBicicleta = totem.getIdBicicleta();
                    listaBicicleta.add(dados.getIdBicicleta());
                    repRede.save(totem);
                    return true;
                }
            }
        }

        return false;
    }

    public boolean retirarDaRede(IdsEquipamentos dados){
        List<Rede> listaTotens = repRede.findAll();

        for (int i = 0; listaTotens.size() > i; i++) {
            Rede totem = listaTotens.get(i);
            List<UUID> listaTranca = totem.getIdTranca();
            for (int j = 0; listaTranca.size() > j; j++) {
                if (listaTranca.get(j) == dados.getIdTranca()) {
                    List<UUID> listaBicicleta = totem.getIdBicicleta();
                    for (int h = 0; listaBicicleta.size() > h; h++) {
                        if(listaBicicleta.get(h) == dados.getIdBicicleta()){
                            listaBicicleta.remove(listaBicicleta.get(h));
                            repRede.save(totem);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}