package com.api.equipamento.controller;


import com.api.equipamento.model.Erro;
import com.api.equipamento.model.Totem;
import com.api.equipamento.service.TotemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


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
    public ResponseEntity<?> setTotem(@RequestBody Totem totem){
        if(service.cadastrarTotem(totem) != null){
            return new ResponseEntity<>(service.cadastrarTotem(totem), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(mensage, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @GetMapping("/totem/{id}")
    public ResponseEntity<Totem> mostraTotem(@PathVariable int id){
        return new ResponseEntity<>(service.mostrarTotem(id), HttpStatus.OK);
    }
    @PutMapping("/totem/{id}")
    public ResponseEntity<?> putTotem(@RequestBody Totem totem, @PathVariable int id){
        if(service.alterarTotem(totem, id) != null) {
            return new ResponseEntity<>(service.alterarTotem(totem, id), HttpStatus.OK);
        }else if(totem.getLocalizacao() == null){
            return new ResponseEntity<>(mensage, HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            return new ResponseEntity<>(mensage, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/totem/{id}")
    public ResponseEntity<Erro> deletTotem(@PathVariable int id){
        if(service.excluirTotem(id)){
            return new ResponseEntity<>(mensage, HttpStatus.OK);
        }
        return new ResponseEntity<>(mensage, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/totem/{id}/trancas")
    public ResponseEntity<?> listaTrancasTotem(@PathVariable int id){
        if(service.listaTrancaTotem(id) != null){
            return new ResponseEntity<>(service.listaTrancaTotem(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(mensage, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/totem/{id}/bicicletas")
    public ResponseEntity<?> listaBicicletaTotem(@PathVariable int id){
        if(service.listaBicicletaTotem(id) != null) {
            return new ResponseEntity<>(service.listaBicicletaTotem(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(mensage, HttpStatus.NOT_FOUND);
    }
}
