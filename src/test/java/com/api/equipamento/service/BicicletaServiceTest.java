package com.api.equipamento.service;


import com.api.equipamento.model.Bicicleta;
import com.api.equipamento.model.Mensage;
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
        inciarBike();
        Mensage resul = service.cadastrar(bicicleta);

        assertEquals(resul.getMensage(), "Dados cadastrados");
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
