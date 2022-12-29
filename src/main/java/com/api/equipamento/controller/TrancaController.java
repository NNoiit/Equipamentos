package com.api.equipamento.controller;

import com.api.equipamento.model.Mensage;
import com.api.equipamento.model.Tranca;
import com.api.equipamento.repositori.RepTranca;
import com.api.equipamento.service.TrancaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TrancaController {

    @Autowired
    private RepTranca tranca;

    @Autowired
    private TrancaService service;

    @Autowired
    private Mensage mensage;


    @PostMapping("/tranca")
    public ResponseEntity<?> postTranca(@RequestBody Tranca trc){
        try {
            mensage.setMensage("Tranca cadastrada");
            service.cadastrarTranca(trc);
            return new ResponseEntity<>(mensage, HttpStatus.OK);
        }catch (Exception e){
            mensage.setMensage("Tranca não cadastrada");
            return new ResponseEntity<>(mensage.getMensage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/tranca")
    public ResponseEntity<?> getTranca(){
        return new ResponseEntity<>(service.listarTrancas(), HttpStatus.OK);
    }

    @GetMapping("/tranca/{id}")
    public ResponseEntity<?> getTranca(@PathVariable int id){

        if(service.trancaFindId(id).equals(null)){
            mensage.setMensage("Tranca não encontrada");
            return new ResponseEntity<>(mensage, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(service.trancaFindId(id), HttpStatus.OK);
        }
    }

    @PutMapping("/tranca/{id}")
    public ResponseEntity<?> putTranca(@RequestBody Tranca trc, @PathVariable int id){
        Tranca tranca1 = service.alterarTranca(trc, id);
        if(tranca1.equals(null)){
            mensage.setMensage("Alteração malssucedida");
            return new ResponseEntity<>(mensage, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(tranca1, HttpStatus.CREATED);
        }

    }

    @DeleteMapping("/tranca/{id}")
    public void deleteTrancaId(@PathVariable int id){
        service.excluirTranca(id);
    }

}