package com.api.equipamento.controller;

import com.api.equipamento.model.Bicicleta;
import com.api.equipamento.model.IdsEquipamentos;
import com.api.equipamento.model.Status;
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
        return "Bem Vindos a Nossa Bike &#9773;";
    }
    @PostMapping("/bicicleta")
    public ResponseEntity<?> postBicicleta(@RequestBody Bicicleta bike1){
        if(service.cadastrar(bike1) != null){
            return new ResponseEntity<>(service.cadastrar(bike1), HttpStatus.OK);
        }else {
            mensage.setMensage("Dados invalidos");
            return new ResponseEntity<>(mensage, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    //alterAar mais tarde para retornar uma mensagem
    @GetMapping("/bicicleta")
    public ResponseEntity<List<Bicicleta>> getBicicleta(){
        return new ResponseEntity<>(service.listarBicicletas(), HttpStatus.OK);
    }

    @GetMapping("/bicicleta/{id}")
    public ResponseEntity<?> getBicicleta(@PathVariable int id){
        if(service.bicicletaFindId(id) != null) {
            return new ResponseEntity<>(service.bicicletaFindId(id), HttpStatus.OK);
        } else{
            mensage.setMensage("Não encontrado");
            return new ResponseEntity<>(mensage, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/bicicleta/{id}")
    public ResponseEntity<?> putBicicleta(@RequestBody Bicicleta bike, @PathVariable int id){
        if(service.alterarBicicleta(bike, id) != null) {
            return new ResponseEntity<>(service.alterarBicicleta(bike, id), HttpStatus.OK);
        } else {
            mensage.setMensage("Não encotrado");
            return new ResponseEntity<>(mensage, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/bicicleta/{id}")
    public ResponseEntity<Erro> deleteBicicletaId(@PathVariable int id){
        return new ResponseEntity<>(service.excluirBicicleta(id), HttpStatus.OK);
    }

    @PostMapping("/bicicleta/integrarNaRede")
    public ResponseEntity<Erro> integrarNaRede(@RequestBody IdsEquipamentos dados){
        if(service.integrarNaRede(dados)) {
            mensage.setMensage("Dados cadastrados");
            return new ResponseEntity<>(mensage, HttpStatus.OK);
        } else {
            mensage.setMensage("Dados invalidos");
            return new ResponseEntity<>(mensage, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    @PostMapping("/bicicleta/retriarDaRede")
    public ResponseEntity<Erro> retirarDaRede(@RequestBody IdsEquipamentos dados){
        if(service.retirarDaRede(dados)) {
            mensage.setMensage("Dados cadastrados");
            return new ResponseEntity<>(mensage, HttpStatus.OK);
        } else {
            mensage.setMensage("Dados invalidos");
            return new ResponseEntity<>(mensage, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    //corrigir dps, o objetivo é alterar a ação
    @PutMapping("/bicicleta/{id}/status/{acao}")
    public ResponseEntity<Erro> putStatusBicicleta(@PathVariable int id, @PathVariable Status acao){
        if(service.alterarStatusBicicleta(id, acao).getMensage() == "Ação bem sucedida") {
            return new ResponseEntity<>(service.alterarStatusBicicleta(id, acao), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(service.alterarStatusBicicleta(id, acao), HttpStatus.NOT_FOUND);
        }
    }


}