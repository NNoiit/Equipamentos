package com.api.equipamento.controller;

import com.api.equipamento.model.Bicicleta;
import com.api.equipamento.model.Tranca;
import com.api.equipamento.repositori.RepTranca;
import com.api.equipamento.repositori.RepBicicleta;
import com.api.equipamento.service.TrancaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TrancaController {

    @Autowired
    private RepTranca tranca;

    @Autowired
    private TrancaService service;


    @PostMapping("/tranca")
    public ResponseEntity<?> postTranca(@RequestBody Tranca trc){
        return service.cadastrarTranca(trc);
    }

    @GetMapping("/tranca")
    public ResponseEntity<?> getTranca(){
        return service.listarTrancas();
    }

    @GetMapping("/tranca/{id}")
    public ResponseEntity<?> getTranca(@PathVariable int id){
        return service.trancaFindId(id);
    }

    @PutMapping("/tranca/{id}")
    public ResponseEntity<?> putTranca(@RequestBody Tranca trc, @PathVariable int id){
        return service.alterarTranca(trc, id);
    }

    @DeleteMapping("/tranca/{id}")
    public void deleteTrancaId(@PathVariable int id){
        service.excluirTranca(id);
    }


}