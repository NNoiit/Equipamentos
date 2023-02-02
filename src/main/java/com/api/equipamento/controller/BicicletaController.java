package com.api.equipamento.controller;

import com.api.equipamento.model.Bicicleta;
import com.api.equipamento.model.IdsEquipamentos;
import com.api.equipamento.model.Status;
import com.api.equipamento.service.BicicletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.api.equipamento.model.Erro;
import java.util.List;
import java.util.UUID;

@RestController
public class BicicletaController {
    @Autowired
    private BicicletaService bicicletaService;

    @Autowired
    private Erro mensage;

    @GetMapping("/")
    public String teste(){
        return "Bem Vindos a Nossa Bike &#9773;";
    }
    @PostMapping("/bicicleta")
    public ResponseEntity<Bicicleta> postBicicleta(@RequestBody Bicicleta bike1){
        Bicicleta bicicletaNova = bicicletaService.cadastrar(bike1);
        if(bicicletaNova != null){
            return new ResponseEntity<>(bicicletaNova, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(bicicletaNova, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/bicicleta")
    public ResponseEntity<List<Bicicleta>> getBicicleta(){
        return new ResponseEntity<>(bicicletaService.listarBicicletas(), HttpStatus.OK);
    }

    @GetMapping("/bicicleta/{id}")
    public ResponseEntity<Bicicleta> getBicicleta(@PathVariable UUID id){
        Bicicleta bicicicletaBuscada = bicicletaService.bicicletaFindId(id);
        if(bicicicletaBuscada != null) {
            return new ResponseEntity<>(bicicicletaBuscada, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(bicicicletaBuscada, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/bicicleta/{id}")
    public ResponseEntity<Bicicleta> putBicicleta(@RequestBody Bicicleta bike, @PathVariable UUID id){
        Bicicleta bicicletaAalterada = bicicletaService.alterarBicicleta(bike, id);
        if(bicicletaAalterada != null) {
            return new ResponseEntity<>(bicicletaAalterada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(bicicletaAalterada, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/bicicleta/{id}")
    public ResponseEntity<Erro> deleteBicicletaId(@PathVariable UUID id){
        mensage = bicicletaService.excluirBicicleta(id);
        return new ResponseEntity<>(mensage, HttpStatus.OK);
    }

    @PostMapping("/bicicleta/integrarNaRede")
    public ResponseEntity<String> integrarNaRede(@RequestBody IdsEquipamentos dados){
        boolean resul = bicicletaService.integrarNaRede(dados);
        if(resul) {
            mensage.setMensage("Dados cadastrados na rede");
            return new ResponseEntity<>(mensage.getMensage(), HttpStatus.OK);
        } else {
            mensage.setCodigo("422");
            mensage.setMensage("Dados invalidos");
            return new ResponseEntity<>(mensage.getMensage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    @PostMapping("/bicicleta/retirarDaRede")
    public ResponseEntity<Erro> retirarDaRede(@RequestBody IdsEquipamentos dados){
        boolean resul = bicicletaService.retirarDaRede(dados);
        if(resul) {
            mensage.setMensage("Dados cadastrados com exito");
            return new ResponseEntity<>(mensage, HttpStatus.OK);
        } else {
            mensage.setCodigo("422");
            mensage.setMensage("Dados invalidos");
            return new ResponseEntity<>(mensage, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    //corrigir dps, o objetivo é alterar a ação
    @PutMapping("/bicicleta/{id}/status/{acao}")
    public ResponseEntity<Bicicleta> putStatusBicicleta(@PathVariable UUID id, @PathVariable Status acao){
        if(bicicletaService.alterarStatusBicicleta(id, acao).getMensage().equals("Ação bem sucedida")) {
            return new ResponseEntity<>(bicicletaService.bicicletaFindId(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(bicicletaService.bicicletaFindId(id), HttpStatus.NOT_FOUND);
        }
    }
}