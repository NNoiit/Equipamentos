package com.api.equipamento.controller;


import com.api.equipamento.model.Bicicleta;
import com.api.equipamento.model.Tranca;
import com.api.equipamento.repositori.RepTranca;
import com.api.equipamento.repositori.Repository;
import com.api.equipamento.service.EquipamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EquipamentoController {

    @Autowired
    private Repository bicicleta;

    @Autowired
    private RepTranca tranca;

    @Autowired
    private EquipamentoService service;

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
    public void deleteBicicletaId(@PathVariable int id){

        service.excluirBicicleta(id);
    }

    @PutMapping("/bicicleta/{id}/status/{acao}")
    public Bicicleta putStatusBicicleta(@RequestBody String novoStatus, @PathVariable int id, @PathVariable String acao){

        Bicicleta bc = bicicleta.findById(id);

        //bc.status status = bc.status.OCUPADO;
        //bc.setStatus(novoStatus);

        return bicicleta.save(bc);
    }

    //////////////////////////////////////////////////////

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