package com.api.equipamento.service;

import com.api.equipamento.model.Bicicleta;
import com.api.equipamento.model.Mensage;
import com.api.equipamento.repositori.RepTranca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.api.equipamento.repositori.RepBicicleta;
import com.api.equipamento.model.Tranca;

@Service
public class BicicletaService{
    @Autowired
    private Mensage mensage;

    @Autowired
    private RepBicicleta bicicleta;

    public ResponseEntity<?> cadastrar(Bicicleta bc){

        if(bc.getModelo().equals("")){
            mensage.setMensage("Dados Inválidos");
            return new ResponseEntity<>(mensage, HttpStatus.BAD_REQUEST);
        }


        mensage.setMensage("Dados cadastrados");
        return new ResponseEntity<>(bicicleta.save(bc), HttpStatus.CREATED);
    }

    public ResponseEntity<?> listarBicicletas(){
        return new ResponseEntity<>(bicicleta.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> bicicletaFindId( int id){
        if(bicicleta.countById(id) == 0){
            mensage.setMensage("Bicicleta não encontrada");
            return new ResponseEntity<>(mensage, HttpStatus.NOT_FOUND);
        }

        mensage.setMensage("Bicicleta encontrada");
        return new ResponseEntity<>(bicicleta.findById(id), HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<?> alterarBicicleta(Bicicleta bike, int id){

        if(bicicleta.countById(id) == 0){
            return bicicletaFindId(id);
        }

        if(bike.getNumero() > 0 ||
                bike.getMarca() == ""
                ||bike.getModelo() == ""
                ||bike.getAno() == ""
                ){

            mensage.setMensage("Dados Inválidos");
            return new ResponseEntity<>(bicicleta.findById(id), HttpStatus.BAD_REQUEST);
        }

        Bicicleta bc = bicicleta.findById(id);

        bc.setMarca(bike.getMarca());
        bc.setModelo(bike.getModelo());
        bc.setAno(bike.getAno());
        bc.setNumero(bike.getNumero());

        mensage.setMensage("Dados atualizados");
        return new ResponseEntity<>(bicicleta.save(bc), HttpStatus.CREATED);
    }

    public void excluirBicicleta(int id){

        if(bicicleta.countById(id)==0){
           bicicletaFindId(id);
        }

        Bicicleta bc = bicicleta.findById(id);

        bicicleta.delete(bc);

    }
}