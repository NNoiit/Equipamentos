package com.api.equipamento.service;

import com.api.equipamento.repositori.RepTranca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.equipamento.model.Tranca;
import java.util.List;

@Service
public class TrancaService{
    @Autowired
    private RepTranca tranca;

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
}