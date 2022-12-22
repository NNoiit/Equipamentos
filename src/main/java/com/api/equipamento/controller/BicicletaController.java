package com.api.equipamento.controller;

import com.api.equipamento.model.Bicicleta;
import com.api.equipamento.repositori.RepBicicleta;
import com.api.equipamento.service.BicicletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.api.equipamento.model.Mensage;

import java.util.List;

@RestController
public class BicicletaController {

    @Autowired
    private RepBicicleta bicicleta;

    @Autowired
    private BicicletaService service;

    @Autowired
    private Mensage mensage;

    @GetMapping("/")
    public String teste(){
        return "olá paula";
    }
    @PostMapping("/bicicleta")
    public ResponseEntity<?> postBicicleta(@RequestBody Bicicleta bike1){

        return new ResponseEntity<>(service.cadastrar(bike1), HttpStatus.OK);
    }

    //alterAar mais tarde para retornar uma mensagem
    @GetMapping("/bicicleta")
    public ResponseEntity<?> getBicicleta(){
        List<Bicicleta> listarBicicletas = service.listarBicicletas();
        return new ResponseEntity<>(listarBicicletas, HttpStatus.OK);
    }

    @GetMapping("/bicicleta/{id}")
    public ResponseEntity<?> getBicicleta(@PathVariable int id){
        Bicicleta bicicletaFindId = service.bicicletaFindId(id);
        return new ResponseEntity<>(bicicletaFindId, HttpStatus.OK);
    }

    @PutMapping("/bicicleta/{id}")
    public ResponseEntity<?> putBicicleta(@RequestBody Bicicleta bike, @PathVariable int id){
        return service.alterarBicicleta(bike, id);
    }

    @DeleteMapping("/bicicleta/{id}")
    public ResponseEntity<?> deleteBicicletaId(@PathVariable int id){

        mensage = service.excluirBicicleta(id);

        return new ResponseEntity<Mensage>(mensage, HttpStatus.OK);
    }

    //corrigir dps, o objetivo é alterar a ação
    @PutMapping("/bicicleta/{id}/status/{acao}")
    public Bicicleta putStatusBicicleta(@RequestBody String novoStatus, @PathVariable int id, @PathVariable String acao){

        Bicicleta bc = bicicleta.findById(id);

        //bc.status status = bc.status.OCUPADO;
        //bc.setStatus(novoStatus);

        return bicicleta.save(bc);
    }


}