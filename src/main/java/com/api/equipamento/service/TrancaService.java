package com.api.equipamento.service;

import com.api.equipamento.model.Mensage;
import com.api.equipamento.model.Status;
import com.api.equipamento.repositori.RepTranca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.api.equipamento.model.Tranca;

import java.util.List;

@Service
public class TrancaService{
    @Autowired
    private Mensage mensage;


    @Autowired
    private RepTranca tranca;

    public Tranca cadastrarTranca(Tranca trc){

        try {
            if(trc.getLocalizacao().equals("")
                    || trc.getAnoDeFabricacao().equals("")
                    || trc.getModelo().equals("")
                    ){
                return trc;
            }

            return tranca.save(trc);
        }catch (Exception e ){
            return trc;
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
                || trc.getModelo().equals("")
                || trc.status.getDescricao().equals(""))
        {
            return null;
        }else if(tranca.countById(id) == 1){
            Tranca trcA = tranca.findById(id);

            trcA.setNumero(trc.getNumero());
            trcA.setLocalizacao(trc.getLocalizacao());
            trcA.setAnoDeFabricacao(trc.getAnoDeFabricacao());
            trcA.setModelo(trc.getModelo());
            trcA.setStatus(trc.getStatus());
            return tranca.save(trcA);
        } else {
            return null;
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