package com.api.equipamento.controller;

import com.api.equipamento.model.Bicicleta;
import com.api.equipamento.model.IdsEquipamentos;
import com.api.equipamento.repositori.RepBicicleta;
import com.api.equipamento.service.BicicletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.api.equipamento.model.Erro;
import java.util.List;

@RestController
public class BicicletaController {

    @Autowired
    private RepBicicleta bicicleta;

    @Autowired
    private BicicletaService service;

    @Autowired
    private Erro mensage;

    @GetMapping("/")
    public String teste(){
        return "olá, Paula <3";
    }
    @PostMapping("/bicicleta")
    public ResponseEntity<Bicicleta> postBicicleta(@RequestBody Bicicleta bike1){
        return new ResponseEntity<>(service.cadastrar(bike1), HttpStatus.OK);
    }

    //alterAar mais tarde para retornar uma mensagem
    @GetMapping("/bicicleta")
    public ResponseEntity<List<Bicicleta>> getBicicleta(){
        List<Bicicleta> listarBicicletas = service.listarBicicletas();
        return new ResponseEntity<>(listarBicicletas, HttpStatus.OK);
    }

    @GetMapping("/bicicleta/{id}")
    public ResponseEntity<Bicicleta> getBicicleta(@PathVariable int id){
        Bicicleta bicicletaFindId = service.bicicletaFindId(id);
        return new ResponseEntity<>(bicicletaFindId, HttpStatus.OK);
    }

    @PutMapping("/bicicleta/{id}")
    public ResponseEntity<Bicicleta> putBicicleta(@RequestBody Bicicleta bike, @PathVariable int id){
        return new ResponseEntity<>(service.alterarBicicleta(bike, id), HttpStatus.CREATED);
    }

    @DeleteMapping("/bicicleta/{id}")
    public ResponseEntity<Erro> deleteBicicletaId(@PathVariable int id){

        mensage = service.excluirBicicleta(id);

        return new ResponseEntity<>(mensage, HttpStatus.OK);
    }

    @PostMapping("/bicicleta/integrarNaRede")
    public ResponseEntity<String> integrarNaRede(@RequestBody IdsEquipamentos dados){
        service.integrarNaRede(dados);
        return new ResponseEntity<>("Dados cadastrados", HttpStatus.OK);
    }
    @PostMapping("/bicicleta/retriarDaRede")
    public ResponseEntity<String> retirarDaRede(@RequestBody IdsEquipamentos dados){
        service.retirarDaRede(dados);
        return new ResponseEntity<>("Dados cadastrados", HttpStatus.OK);
    }

    //corrigir dps, o objetivo é alterar a ação
    @PutMapping("/bicicleta/{id}/status/{acao}")
    public Bicicleta putStatusBicicleta(@RequestBody String novoStatus, @PathVariable int id, @PathVariable String acao){
        Bicicleta bc = bicicleta.findById(id);
        return bicicleta.save(bc);
    }


}