package com.api.equipamento.service;


import com.api.equipamento.model.Bicicleta;
import com.api.equipamento.repositori.Repository;
import jakarta.persistence.Id;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.api.equipamento.model.Bicicleta.Status.LIVRE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EquepamentoServiceTest {

    @InjectMocks
    private EquipamentoService service;

    @Mock
    private Bicicleta bicicleta;

    @Mock
    private Repository bicicletaBD;

    @Test
    public void cadastroBike() {
        service = new EquipamentoService();

        service.cadastrar(bicicleta);

        assertEquals(HttpStatus.OK, service);
    }

    private void inciarBike() {
        Bicicleta.Status bikeStatus = Bicicleta.Status.LIVRE;

        bicicleta = new Bicicleta();

        bicicleta.setMarca("ola");
        bicicleta.setNumero(9);
        bicicleta.setAno("ola");
        bicicleta.setModelo("ola");



    }
}
