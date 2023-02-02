package com.api.equipamento.controller;

import com.api.equipamento.model.*;
import com.api.equipamento.service.StatusService;
import com.api.equipamento.service.TrancaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class TrancaController {
    @Autowired
    private TrancaService trancaService;

    @Autowired
    private StatusService statusService;
    @Autowired
    private Erro mensage;

    @PostMapping("/tranca")
    public ResponseEntity<Tranca> postTranca(@RequestBody Tranca trc){
        Tranca trancaNova = trancaService.cadastrarTranca(trc);
        if(trancaNova != null){
            mensage.setMensage("Tranca cadastrada");
            return new ResponseEntity<>(trancaNova, HttpStatus.OK);
        }else {
            mensage.setMensage("Tranca não cadastrada");
            return new ResponseEntity<>(trancaNova, HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

    @GetMapping("/tranca")
    public ResponseEntity<List<Tranca>> getTranca(){
        return new ResponseEntity<>(trancaService.listarTrancas(), HttpStatus.OK);
    }

    @GetMapping("/tranca/{id}")
    public ResponseEntity<Tranca> getTranca(@PathVariable UUID id){
        Tranca trancaEncontrada = trancaService.trancaFindId(id);
        if(trancaEncontrada == null){
            mensage.setMensage("Tranca não encontrada");
            return new ResponseEntity<>(trancaEncontrada, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(trancaEncontrada, HttpStatus.OK);
        }
    }

    @PutMapping("/tranca/{id}")
    public ResponseEntity<Tranca> putTranca(@RequestBody Tranca novaTranca, @PathVariable UUID id){
        Tranca tranca = trancaService.alterarTranca(novaTranca, id);
        if(tranca == null){
            mensage.setMensage("Alteração malssucedida");
            return new ResponseEntity<>(tranca, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(tranca, HttpStatus.CREATED);
        }

    }

    @DeleteMapping("/tranca/{id}")
    public ResponseEntity<Erro> deleteTrancaId(@PathVariable UUID id){
        boolean resul = trancaService.excluirTranca(id);
        if(resul){
            mensage.setMensage("Tranca removida");
            return new ResponseEntity<>(mensage, HttpStatus.OK);
        } else {
            mensage.setMensage("Não encontrado");
            return new ResponseEntity<>(mensage, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PostMapping("/tranca/{id}/trancar")
    public ResponseEntity<Erro> trancarTranca(@PathVariable UUID id, @RequestBody IdsEquipamentos idBicicleta){
        boolean resul = trancaService.trancarTranca(id, idBicicleta.getBicicleta());
        if(resul) {
            mensage.setMensage("Ação bem sucedida");
            return new ResponseEntity<>(mensage, HttpStatus.OK);
        }else {
            mensage.setMensage("Dado não encontrado");
            return new ResponseEntity<>(mensage, HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/tranca/{id}/destrancar")
    public ResponseEntity<Erro> destrancarTranca(@PathVariable UUID id, @RequestBody IdsEquipamentos idBicicleta){
        boolean resul = trancaService.destrancarTranca(id, idBicicleta.getBicicleta());
        if(resul){
            mensage.setMensage("Ação bem sucedida");
            return new ResponseEntity<>(mensage, HttpStatus.OK);
        }else {
            mensage.setMensage("Não encontrado");
            return new ResponseEntity<>(mensage, HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/tranca/{id}/status/{acao}")
    public ResponseEntity<String> alterarStatusTranca(@PathVariable UUID id, @PathVariable Acao acao){
        statusService.alterarStatusTranca(id, acao);
        return new ResponseEntity<>("alterou", HttpStatus.OK);
    }

    @GetMapping("/tranca/{id}/bicicleta")
    public ResponseEntity<Bicicleta> bicicletaTranca(@PathVariable UUID id){
        Bicicleta uuidBicicleta = trancaService.getBicicleta(id);
        if(uuidBicicleta != null) {
            return new ResponseEntity<>(uuidBicicleta, HttpStatus.OK);
        } else {
            mensage.setMensage("Bicicleta não encontrada");
            return new ResponseEntity<>(uuidBicicleta, HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/tranca/integrarNaRede")
    public ResponseEntity<Erro> integrarNaRede(@RequestBody IdsEquipamentos dado){
        boolean resul = trancaService.adicionaTrancaRede(dado);
        if(resul) {
            mensage.setMensage("Dados cadastrados");
            return new ResponseEntity<>(mensage, HttpStatus.OK);
        } else {
            mensage.setMensage("Dados Inválidos");
            return new ResponseEntity<>(mensage, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PostMapping("/tranca/retirarDaRede")
    public ResponseEntity<Erro> retirarDaRede(@RequestBody IdsEquipamentos dado){
        boolean resul = trancaService.removerTrancaRede(dado);
        if(resul) {
            mensage.setMensage("Dados cadastrados");
            return new ResponseEntity<>(mensage, HttpStatus.OK);
        } else {
            mensage.setMensage("Dados invalidos");
            return new ResponseEntity<>(mensage, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}