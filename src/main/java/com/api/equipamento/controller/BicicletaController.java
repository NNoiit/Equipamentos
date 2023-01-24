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
            mensage.setMensage("Bicicleta criada");
            return new ResponseEntity<>(bicicletaNova, HttpStatus.OK);
        }else {
            mensage.setMensage("Dados invalidos");
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
            mensage.setMensage("Não encontrado");
            mensage.setCodigo("NOT FOUND");
            mensagensDoSistema(mensage);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/bicicleta/{id}")
    public ResponseEntity<Bicicleta> putBicicleta(@RequestBody Bicicleta bike, @PathVariable UUID id){

        if(bicicletaService.alterarBicicleta(bike, id) != null) {
            return new ResponseEntity<>(bicicletaService.alterarBicicleta(bike, id), HttpStatus.OK);
        } else {
            mensage.setMensage("Não encotrado");
            mensage.setCodigo("NOT FOUD");
            mensagensDoSistema(mensage);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/bicicleta/{id}")
    public ResponseEntity<Erro> deleteBicicletaId(@PathVariable UUID id){
        return new ResponseEntity<>(bicicletaService.excluirBicicleta(id), HttpStatus.OK);
    }

    @PostMapping("/bicicleta/integrarNaRede")
    public ResponseEntity<Erro> integrarNaRede(@RequestBody IdsEquipamentos dados){
        boolean resul = bicicletaService.integrarNaRede(dados);
        if(resul) {
            mensage.setMensage("Dados cadastrados");
            return new ResponseEntity<>(mensage, HttpStatus.OK);
        } else {
            mensage.setCodigo("422");
            mensage.setMensage("Dados invalidos");
            return new ResponseEntity<>(mensage, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    @PostMapping("/bicicleta/retriarDaRede")
    public ResponseEntity<Erro> retirarDaRede(@RequestBody IdsEquipamentos dados){
        boolean resul = bicicletaService.retirarDaRede(dados);
        if(resul) {
            mensage.setMensage("Dados cadastrados");
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
            mensage.setMensage("Não encontrado");
            mensage.setCodigo("NOT FOUND");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    private void mensagensDoSistema(Erro mensage){
        System.out.println(mensage);
    }
}