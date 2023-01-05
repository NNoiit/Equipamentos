package com.api.equipamento.service;

import com.api.equipamento.model.IdsEquipamentos;
import com.api.equipamento.model.Rede;
import com.api.equipamento.repositori.RepRede;
import com.api.equipamento.repositori.RepTranca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.equipamento.model.Tranca;
import java.util.List;

@Service
public class TrancaService{
    @Autowired
    private RepTranca tranca;

    @Autowired
    private RepRede repRede;
    public Tranca cadastrarTranca(Tranca trc){

        if(trc.getLocalizacao().equals("") || trc.getAnoDeFabricacao().equals("") || trc.getModelo().equals("")){
            return null;
        } else {
            return tranca.save(trc);
        }
    }

    public List<Tranca> listarTrancas(){
        return tranca.findAll();
    }

    public Tranca trancaFindId( int id){
        if (tranca.countById(id)==1){
            return tranca.findById(id);
        }
        return null;
    }


    public Tranca alterarTranca(Tranca trc, int id){

        if(trc.getLocalizacao().equals("")
                || trc.getAnoDeFabricacao().equals("")
                || trc.getModelo().equals("") || tranca.countById(id) == 0)
        {
            return null;
        }else{
            Tranca trcA = tranca.findById(id);

            trcA.setNumero(trc.getNumero());
            trcA.setLocalizacao(trc.getLocalizacao());
            trcA.setAnoDeFabricacao(trc.getAnoDeFabricacao());
            trcA.setModelo(trc.getModelo());
            trcA.setStatus(trc.getStatus());
            return tranca.save(trcA);
        }
    }

    public void excluirTranca(int id){

        if(tranca.countById(id) == 0) {
            trancaFindId(id);
        }
        Tranca trc = tranca.findById(id);

        tranca.delete(trc);
    }

    public void adicionaTrancaRede(IdsEquipamentos rede){
        int id = rede.getIdTotem();

        Rede totem = repRede.findById(id);
        List<Integer> listaTranca = totem.getIdTranca();
        listaTranca.add(rede.getIdTranca());

        totem.setIdTranca(listaTranca);

        repRede.save(totem);
    }

    public void removerTrancaRede(IdsEquipamentos rede){
        int id = rede.getIdTotem();

        Rede totem = repRede.findById(id);
        List<Integer> listaTranca = totem.getIdTranca();

        for (int i = 0; listaTranca.size()>i; i++) {
            if(listaTranca.get(i) == rede.getIdTranca()){
                listaTranca.remove(listaTranca.get(i));
            }
        }

        repRede.save(totem);
    }

    //Metodo criado para verificar as Redes/Totens presentes no BD
    public List<Rede> listaRede() {
        return repRede.findAll();
    }
}