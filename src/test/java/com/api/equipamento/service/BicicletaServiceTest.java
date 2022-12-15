package com.api.equipamento.service;


import com.api.equipamento.model.Bicicleta;
import com.api.equipamento.model.StatusBike;
import com.api.equipamento.repositori.RepBicicleta;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BicicletaServiceTest {

    @InjectMocks
    private BicicletaService service;

    @Mock
    private Bicicleta bicicleta;

    @Mock
    private RepBicicleta bicicletaRep;

    @Test
    public void cadastroBike() {

        ResponseEntity<?> bike = service.cadastrar(bicicleta);
        ResponseEntity<?> resul  = new ResponseEntity<>(HttpStatus.OK);
        assertEquals(resul, bike);
    }
    private void inciarBike() {
        bicicleta = new Bicicleta();

        bicicleta.setMarca("ola");
        bicicleta.setNumero(9);
        bicicleta.setAno("ola");
        bicicleta.setModelo("ola");

        bicicleta.setStatusBike(StatusBike.LIVRE);

    }
}
