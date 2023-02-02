package com.api.equipamento.controller;


import com.api.equipamento.model.Bicicleta;
import com.api.equipamento.model.Erro;
import com.api.equipamento.model.Totem;
import com.api.equipamento.model.Tranca;
import com.api.equipamento.service.TotemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;


@RestController
public class TotemController {
    @Autowired
    private TotemService service;
    @Autowired
    private Erro mensage;

    @GetMapping("/totem")
    public ResponseEntity<List<Totem>> getTotem(){
        return new ResponseEntity<>(service.listaTotem(), HttpStatus.OK);
    }

    @PostMapping("/totem")
    public ResponseEntity<Totem> setTotem(@RequestBody Totem totem){
        Totem totemNovo = service.cadastrarTotem(totem);
        if(totemNovo != null){
            return new ResponseEntity<>(totemNovo, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(totemNovo, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @GetMapping("/totem/{id}")
    public ResponseEntity<Totem> mostraTotem(@PathVariable UUID id){
        return new ResponseEntity<>(service.mostrarTotem(id), HttpStatus.OK);
    }
    @PutMapping("/totem/{id}")
    public ResponseEntity<Totem> putTotem(@RequestBody Totem totem, @PathVariable UUID id){
        Totem totemAlterado = service.alterarTotem(totem, id);
        if(totemAlterado != null) {
            return new ResponseEntity<>(totemAlterado, HttpStatus.OK);
        }else if(totem.getLocalizacao() == null){
            return new ResponseEntity<>(totemAlterado, HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            return new ResponseEntity<>(totemAlterado, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/totem/{id}")
    public ResponseEntity<Erro> deletTotem(@PathVariable UUID id){
        boolean resul = service.excluirTotem(id);
        if(resul){
            return new ResponseEntity<>(mensage, HttpStatus.OK);
        }
        return new ResponseEntity<>(mensage, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/totem/{id}/trancas")
    public ResponseEntity<List<Tranca>> listaTrancasTotem(@PathVariable UUID id){
        List<Tranca> listaTranca = service.listaTrancaTotem(id);
        if(listaTranca != null){
            return new ResponseEntity<>(listaTranca, HttpStatus.OK);
        }
        return new ResponseEntity<>(listaTranca, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/totem/{id}/bicicletas")
    public ResponseEntity<List<Bicicleta>> listaBicicletaTotem(@PathVariable UUID id){
        List<Bicicleta> listaBicicleta = service.listaBicicletaTotem(id);
        if(listaBicicleta != null) {
            return new ResponseEntity<>(listaBicicleta, HttpStatus.OK);
        }
        return new ResponseEntity<>(listaBicicleta, HttpStatus.NOT_FOUND);
    }
}
