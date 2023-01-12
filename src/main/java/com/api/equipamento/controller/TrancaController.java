package com.api.equipamento.controller;

import com.api.equipamento.model.*;
import com.api.equipamento.service.StatusService;
import com.api.equipamento.service.TrancaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrancaController {
    @Autowired
    private TrancaService trancaService;

    @Autowired
    private StatusService statusService;
    @Autowired
    private Erro mensage;

    @PostMapping("/tranca")
    public ResponseEntity<?> postTranca(@RequestBody Tranca trc){
        if(trancaService.cadastrarTranca(trc) != null){
            mensage.setMensage("Tranca cadastrada");
            return new ResponseEntity<>(trc, HttpStatus.OK);
        }else {
            mensage.setMensage("Tranca não cadastrada");
            return new ResponseEntity<>(mensage, HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

    @GetMapping("/tranca")
    public ResponseEntity<List<Tranca>> getTranca(){
        return new ResponseEntity<>(trancaService.listarTrancas(), HttpStatus.OK);
    }

    @GetMapping("/tranca/{id}")
    public ResponseEntity<?> getTranca(@PathVariable int id){

        if(trancaService.trancaFindId(id) == null){
            mensage.setMensage("Tranca não encontrada");
            return new ResponseEntity<>(mensage, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(trancaService.trancaFindId(id), HttpStatus.OK);
        }
    }

    @PutMapping("/tranca/{id}")
    public ResponseEntity<?> putTranca(@RequestBody Tranca novaTranca, @PathVariable int id){
        Tranca tranca = trancaService.alterarTranca(novaTranca, id);
        if(tranca == null){
            mensage.setMensage("Alteração malssucedida");
            return new ResponseEntity<>(mensage, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(tranca, HttpStatus.CREATED);
        }

    }

    @DeleteMapping("/tranca/{id}")
    public ResponseEntity<?> deleteTrancaId(@PathVariable int id){
        boolean resul = trancaService.excluirTranca(id);
        if(resul){
            mensage.setMensage("Tranca removida");
            return new ResponseEntity<>(mensage.getMensage(), HttpStatus.OK);
        } else {
            mensage.setMensage("Não encontrado");
            return new ResponseEntity<>(mensage, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PostMapping("/tranca/{id}/trancar")
    public ResponseEntity<Erro> trancarTranca(@PathVariable int id, @RequestBody int idBicicleta){
        boolean resul = trancaService.trancarTranca(id, idBicicleta);
        if(resul) {
            mensage.setMensage("Ação bem sucedida");
            return new ResponseEntity<>(mensage, HttpStatus.OK);
        }else {
            mensage.setMensage("Dado não encontrado");
            return new ResponseEntity<>(mensage, HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/tranca/{id}/destrancar")
    public ResponseEntity<Erro> destrancarTranca(@PathVariable int id, @RequestBody int idBicicleta){
        boolean resul = trancaService.destrancarTranca(id, idBicicleta);
        if(resul){
            mensage.setMensage("Ação bem sucedida");
            return new ResponseEntity<>(mensage, HttpStatus.OK);
        }else {
            mensage.setMensage("Não encontrado");
            return new ResponseEntity<>(mensage, HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/tranca/{id}/status/{acao}")
    public ResponseEntity<String> alterarStatusTranca(@PathVariable int id, @PathVariable Acao acao){
        statusService.alterarStatusTranca(id, acao);
        return new ResponseEntity<>("alterou", HttpStatus.OK);
    }

    @GetMapping("/tranca/{id}/bicicleta")
    public ResponseEntity<?> bicicletaTranca(@PathVariable int id){
        if(trancaService.getBicicleta(id) != null) {
            return new ResponseEntity<>(trancaService.getBicicleta(id), HttpStatus.OK);
        } else {
            mensage.setMensage("Bicicleta não encontrada");
            return new ResponseEntity<>(mensage, HttpStatus.NOT_FOUND);
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