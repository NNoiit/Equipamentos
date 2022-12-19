package com.api.equipamento.service;

import com.api.equipamento.model.Bicicleta;
import com.api.equipamento.model.Mensage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.api.equipamento.repositori.RepBicicleta;

import java.util.List;

@Service
public class BicicletaService{
    @Autowired
    private Mensage mensage;

    @Autowired
    private RepBicicleta bicicletaRep;

    public Mensage cadastrar(Bicicleta bc){

        if(bc.getModelo().equals("")){
            mensage.setMensage("Dados Inválidos");
            return mensage;
        }

        mensage.setMensage("Dados cadastrados");
        return mensage;
    }

    public List<Bicicleta> listarBicicletas(){
        List<Bicicleta> listaBicicletas  =  bicicletaRep.findAll();
        return listaBicicletas;
    }

    public ResponseEntity<?> bicicletaFindId( int id){
        if(bicicletaRep.countById(id) == 0){
            mensage.setMensage("Bicicleta não encontrada");
            return new ResponseEntity<>(mensage, HttpStatus.NOT_FOUND);
        }

        mensage.setMensage("Bicicleta encontrada");
        return new ResponseEntity<>(bicicletaRep.findById(id), HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<?> alterarBicicleta(Bicicleta bike, int id){

        if(bicicletaRep.countById(id) == 0){
            return bicicletaFindId(id);
        }

        if(bike.getNumero() > 0 ||
                bike.getMarca() == ""
                ||bike.getModelo() == ""
                ||bike.getAno() == ""
                ){

            mensage.setMensage("Dados Inválidos");
            return new ResponseEntity<>(bicicletaRep.findById(id), HttpStatus.BAD_REQUEST);
        }

        Bicicleta bc = bicicletaRep.findById(id);

        bc.setMarca(bike.getMarca());
        bc.setModelo(bike.getModelo());
        bc.setAno(bike.getAno());
        bc.setNumero(bike.getNumero());

        mensage.setMensage("Dados atualizados");
        return new ResponseEntity<>(bicicletaRep.save(bc), HttpStatus.CREATED);
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
}