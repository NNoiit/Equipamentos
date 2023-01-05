package com.api.equipamento.controller;

import com.api.equipamento.model.IdsEquipamentos;
import com.api.equipamento.model.Mensage;
import com.api.equipamento.model.Rede;
import com.api.equipamento.model.Tranca;
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
        try {
            mensage.setMensage("Tranca cadastrada");
            service.cadastrarTranca(trc);
            return new ResponseEntity<>(mensage, HttpStatus.OK);
        }catch (Exception e){
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