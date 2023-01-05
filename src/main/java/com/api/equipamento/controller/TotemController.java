package com.api.equipamento.controller;


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

    /*@GetMapping("/totem")
    public ResponseEntity<List> getTotem(){
        service.removerRedes();
        return new ResponseEntity<>(service.listaTotem(), HttpStatus.OK);
    }*/

    @PostMapping("/totem")
    public ResponseEntity<Totem> setTotem(@RequestBody Totem totem){

        return new ResponseEntity<>(service.cadastrarTotem(totem), HttpStatus.CREATED);
    }
    @GetMapping("/totem/{id}")
    public ResponseEntity<Totem> putTotem(@PathVariable int id){
        return new ResponseEntity<>(service.mostrarTotem(id), HttpStatus.OK);
    }
    @PutMapping("/totem/{id}")
    public ResponseEntity<Totem> putTotem(@RequestBody Totem totem, @PathVariable int id){
        return new ResponseEntity<>(service.alterarTotem(totem, id), HttpStatus.OK);
    }

    @DeleteMapping("/totem/{id}")
    public void deletTotem(@PathVariable int id){
        service.excluirTotem(id);
    }

}
