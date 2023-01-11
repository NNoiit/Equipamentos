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

    public Tranca trancaFindId( int id){
        if (repTranca.countById(id)==1){
            return repTranca.findById(id);
        }
        return null;
    }


    public Tranca alterarTranca(Tranca trc, int id){

        if(trc.getLocalizacao().equals("")
                || trc.getAnoDeFabricacao().equals("")
                || trc.getModelo().equals("") || repTranca.countById(id) == 0)
        {
            return null;
        }else{
            Tranca trcA = repTranca.findById(id);

            trcA.setNumero(trc.getNumero());
            trcA.setLocalizacao(trc.getLocalizacao());
            trcA.setAnoDeFabricacao(trc.getAnoDeFabricacao());
            trcA.setModelo(trc.getModelo());
            trcA.setStatus(trc.getStatus());
            return repTranca.save(trcA);
        }
    }

    public void excluirTranca(int id){

        if(repTranca.countById(id) == 0) {
            trancaFindId(id);
        }
        Tranca trc = repTranca.findById(id);

        repTranca.delete(trc);
    }

    public Boolean trancarTranca(int idTranca, int idBicicleta){
        Tranca tranca1 = repTranca.findById(idTranca);

        if(tranca1.getStatus() == Status.LIVRE){
            tranca1.setBicicleta(idBicicleta);
            tranca1.setStatus(Status.OCUPADO);
            bicicletaService.alterarStatusBicicleta(idBicicleta, Status.LIVRE);
            return true;
        }
        return false;
    }
    public Boolean destrancarTranca(int idTranca, int idBicicleta){
        Tranca tranca1 = repTranca.findById(idTranca);

         if(tranca1.getBicicleta() == idBicicleta){
             tranca1.setBicicleta(0);
             tranca1.setStatus(Status.LIVRE);
             return true;
         }
        return false;
    }

    public Boolean adicionaTrancaRede(IdsEquipamentos idsParaRede){

        if(repRede.findByIdTotem(idsParaRede.getIdTotem())!= null && repTranca.countById(idsParaRede.getIdTranca()) > 0) {
            Rede totem = repRede.findByIdTotem(idsParaRede.getIdTotem());
            List<Integer> listaTranca = totem.getIdTranca();
            listaTranca.add(idsParaRede.getIdTranca());
            totem.setIdTranca(listaTranca);

            repRede.save(totem);
            return true;
        }
        return false;
    }

    public void removerTrancaRede(IdsEquipamentos idsParaRede){
        int id = idsParaRede.getIdTotem();

        Rede totem = repRede.findByIdTotem(id);
        List<Integer> listaTranca = totem.getIdTranca();

        for (int i = 0; listaTranca.size()>i; i++) {
            if(listaTranca.get(i) == idsParaRede.getIdTranca()){
                listaTranca.remove(listaTranca.get(i));
            }
        }

        repRede.save(totem);
    }

    public Bicicleta getBicicleta(int idTranca) {
        Tranca tranca1 = repTranca.findById(idTranca);
        if(tranca1.getStatus() == Status.OCUPADO){
            return repBicicleta.findById(tranca1.getBicicleta());
        }
        return null;
    }
    //Metodo criado para verificar as Redes/Totens presentes no BD
    public List<Rede> listaRede() {
        return repRede.findAll();
    }

}