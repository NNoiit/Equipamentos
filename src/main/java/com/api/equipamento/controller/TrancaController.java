package com.api.equipamento.controller;

import com.api.equipamento.model.*;
import com.api.equipamento.service.TrancaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrancaController {
    @Autowired
    private TrancaService service;
    @Autowired
    private Mensage mensage;

    @PostMapping("/tranca")
    public ResponseEntity<Mensage> postTranca(@RequestBody Tranca trc){
        if(service.cadastrarTranca(trc) != null){
            mensage.setMensage("Tranca cadastrada");
            return new ResponseEntity<>(mensage, HttpStatus.OK);
        }else {
            mensage.setMensage("Tranca não cadastrada");
            return new ResponseEntity<>(mensage, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/tranca")
    public ResponseEntity<List<Tranca>> getTranca(){
        return new ResponseEntity<>(service.listarTrancas(), HttpStatus.OK);
    }

    @GetMapping("/tranca/{id}")
    public ResponseEntity<?> getTranca(@PathVariable int id){

        if(service.trancaFindId(id) == null){
            mensage.setMensage("Tranca não encontrada");
            return new ResponseEntity<>(mensage, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(service.trancaFindId(id), HttpStatus.OK);
        }
    }

    @PutMapping("/tranca/{id}")
    public ResponseEntity<?> putTranca(@RequestBody Tranca novaTranca, @PathVariable int id){
        Tranca tranca = service.alterarTranca(novaTranca, id);
        if(tranca == null){
            mensage.setMensage("Alteração malssucedida");
            return new ResponseEntity<>(mensage, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(tranca, HttpStatus.CREATED);
        }

    }

    @DeleteMapping("/tranca/{id}")
    public void deleteTrancaId(@PathVariable int id){
        service.excluirTranca(id);
    }

    @PostMapping("/tranca/{id}/trancar")
    public ResponseEntity<String> trancarTranca(@PathVariable int id, @RequestBody int idBicicleta){
        service.trancarTranca(id, idBicicleta);
        return new ResponseEntity<>("Trancoyu", HttpStatus.OK);
    }
    @PostMapping("/tranca/{id}/destrancar")
    public ResponseEntity<String> destrancarTranca(@PathVariable int id, @RequestBody int idBicicleta){
        service.destrancarTranca(id, idBicicleta);
        return new ResponseEntity<>("Destrancou", HttpStatus.OK);
    }
    @PostMapping("/tranca/{id}/status/{acao}")
    public ResponseEntity<String> alterarStatusTranca(@PathVariable int id, @PathVariable Acao acao){
        service.alterarStatusTranca(id, acao);
        return new ResponseEntity<>("alterou", HttpStatus.OK);
    }

    @GetMapping("/tranca/{id}/bicicleta")
    public ResponseEntity<Bicicleta> bicicletaTranca(@PathVariable int id){
        return new ResponseEntity<>(service.getBicicleta(id), HttpStatus.OK);
    }
    @PostMapping("/tranca/integrarNaRede")
    public ResponseEntity<String> integrarNaRede(@RequestBody IdsEquipamentos dado){
        service.adicionaTrancaRede(dado);
        return new ResponseEntity<>("Dados cadastrados", HttpStatus.OK);
    }

    @PostMapping("/tranca/retirarDaRede")
    public ResponseEntity<String> retirarDaRede(@RequestBody IdsEquipamentos dado){
        service.removerTrancaRede(dado);
        return new ResponseEntity<>("Dados cadastrados", HttpStatus.OK);
    }

    @GetMapping("/tranca/retirarDaRede")
    public List<Rede> listaRede(){
        return service.listaRede();
    }
}