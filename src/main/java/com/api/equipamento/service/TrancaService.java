package com.api.equipamento.service;

import com.api.equipamento.model.*;
import com.api.equipamento.repositori.RepBicicleta;
import com.api.equipamento.repositori.RepRede;
import com.api.equipamento.repositori.RepTotem;
import com.api.equipamento.repositori.RepTranca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TrancaService{
    @Autowired
    private RepTranca repTranca;
    @Autowired
    private BicicletaService bicicletaService;
    @Autowired
    private RepRede repRede;
    @Autowired
    private RepBicicleta repBicicleta;
    @Autowired
    @Qualifier("Totem")
    private RepTotem repTotem;

    public Tranca cadastrarTranca(Tranca trc){

        if(trc.getLocalizacao().equals("") || trc.getAnoDeFabricacao().equals("") || trc.getModelo().equals("")){
            return null;
        } else {
            return repTranca.save(trc);
        }
    }

    public List<Tranca> listarTrancas(){
        return repTranca.findAll();
    }

    public Tranca trancaFindId(UUID id){
        if (repTranca.countByUuid(id)==1){
            return repTranca.findByUuid(id);
        }
        return null;
    }


    public Tranca alterarTranca(Tranca trc, UUID id){

        if(trc.getLocalizacao().equals("")
                || trc.getAnoDeFabricacao().equals("")
                || trc.getModelo().equals("") || repTranca.countByUuid(id) == 0)
        {
            return null;
        }else{
            Tranca trcA = repTranca.findByUuid(id);
            trcA.setNumero(trc.getNumero());
            trcA.setLocalizacao(trc.getLocalizacao());
            trcA.setAnoDeFabricacao(trc.getAnoDeFabricacao());
            trcA.setModelo(trc.getModelo());
            trcA.setStatus(trc.getStatus());
            return repTranca.save(trcA);
        }
    }

    public boolean excluirTranca(UUID id){

        if(repTranca.countByUuid(id) == 0) {
            return false;
        }
        Tranca trc = repTranca.findByUuid(id);
        repTranca.delete(trc);
        return true;
    }

    public Boolean trancarTranca(UUID idTranca, UUID idBicicleta){
        Tranca tranca1 = repTranca.findByUuid(idTranca);

        if(tranca1.getStatus() == Status.LIVRE){
            tranca1.setBicicleta(idBicicleta);
            tranca1.setStatus(Status.OCUPADO);
            bicicletaService.alterarStatusBicicleta(idBicicleta, Status.LIVRE);
            return true;
        }
        return false;
    }
    public Boolean destrancarTranca(UUID idTranca, UUID idBicicleta){
        Tranca tranca1 = repTranca.findByUuid(idTranca);

         if(tranca1.getBicicleta() == idBicicleta){
             tranca1.setBicicleta(null);
             tranca1.setStatus(Status.LIVRE);
             return true;
         }
        return false;
    }

    public Boolean adicionaTrancaRede(IdsEquipamentos idsParaRede){

        if(repTotem.findById(idsParaRede.getIdTotem())!= null && repTranca.countByUuid(idsParaRede.getIdTranca()) > 0) {
            Rede totem;
            if(repRede.findByIdTotem(idsParaRede.getIdTotem()) != null){
                totem =  repRede.findByIdTotem(idsParaRede.getIdTotem());
            } else {
                totem = new Rede();
                totem.setIdTotem(idsParaRede.getIdTotem());
            }

            List<UUID> listaTranca = totem.getIdTranca();
            listaTranca.add(idsParaRede.getIdTranca());
            totem.setIdTranca(listaTranca);

            repRede.save(totem);
            return true;
        }
        return false;
    }

    public boolean removerTrancaRede(IdsEquipamentos idsParaRede){
        UUID id = idsParaRede.getIdTotem();
        if(repRede.findByIdTotem(id) != null) {
            Rede totem = repRede.findByIdTotem(id);
            List<UUID> listaTranca = totem.getIdTranca();

            for (int i = 0; listaTranca.size() > i; i++) {
                if (listaTranca.get(i) == idsParaRede.getIdTranca()) {
                    listaTranca.remove(listaTranca.get(i));
                }
            }
            repRede.save(totem);
            return true;
        }else {
            return false;
        }
    }

    public Bicicleta getBicicleta(UUID idTranca) {
        Tranca tranca1 = repTranca.findByUuid(idTranca);
        if(tranca1.getStatus() == Status.OCUPADO){
            return repBicicleta.findByUuid(tranca1.getBicicleta());
        }else {
            return null;
        }
    }
}