package com.api.equipamento.controller;

import com.api.equipamento.model.Bicicleta;
import com.api.equipamento.repositori.RepBicicleta;
import com.api.equipamento.service.BicicletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.api.equipamento.model.Mensage;

@RestController
public class BicicletaController {

    @Autowired
    private RepBicicleta bicicleta;

    @Autowired
    private BicicletaService service;

    @Autowired
    private Mensage mensage;

    @GetMapping("/testGet/{id}")
    public int getBicicletas(@PathVariable int id){
        return bicicleta.countById(id);
    }

    @PostMapping("/bicicleta")
    public ResponseEntity<?> postBicicleta(@RequestBody Bicicleta bike){
        return service.cadastrar(bike);
    }

    @GetMapping("/bicicleta")
    public ResponseEntity<?> getBicicleta(){
        return service.listarBicicletas();
    }

    @GetMapping("/bicicleta/{id}")
    public ResponseEntity<?> getBicicleta(@PathVariable int id){

        return service.bicicletaFindId(id);
    }

    @PutMapping("/bicicleta/{id}")
    public ResponseEntity<?> putBicicleta(@RequestBody Bicicleta bike, @PathVariable int id){
        return service.alterarBicicleta(bike, id);
    }

    @DeleteMapping("/bicicleta/{id}")
    public ResponseEntity<?> deleteBicicletaId(@PathVariable int id){

        mensage  = service.excluirBicicleta(id);

        return new ResponseEntity<>(mensage, HttpStatus.OK);
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