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

    public Bicicleta cadastrar(Bicicleta bicicleta){

        if(bicicleta.getModelo().equals("") || bicicleta.getAno().equals("") || bicicleta.getMarca().equals("")){
            return bicicleta;
        }

        return bicicletaRep.save(bicicleta);
    }

    public List<Bicicleta> listarBicicletas(){
        //List<Bicicleta> listaBicicletas  =  ;
        return bicicletaRep.findAll();
                //listaBicicletas;
    }

    public Bicicleta bicicletaFindId( int id){
        return bicicletaRep.findById(id);
    }


    public Bicicleta alterarBicicleta(Bicicleta bike, int id){

        if(bike.getNumero() > 0 ||
                bike.getMarca() == ""
                ||bike.getModelo() == ""
                ||bike.getAno() == ""
                ){
            return bicicletaRep.findById(id);
        }

        Bicicleta bc = bicicletaRep.findById(id);

        bc.setMarca(bike.getMarca());
        bc.setModelo(bike.getModelo());
        bc.setAno(bike.getAno());
        bc.setNumero(bike.getNumero());

        return bicicletaRep.save(bc);
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