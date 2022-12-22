package com.api.equipamento.service;


import com.api.equipamento.model.Bicicleta;
import com.api.equipamento.model.Mensage;
import com.api.equipamento.model.StatusBike;
import com.api.equipamento.repositori.RepBicicleta;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class BicicletaServiceTest {

    @InjectMocks
    private BicicletaService service;

    @InjectMocks
    private Bicicleta bicicleta;

    @Mock
    private RepBicicleta bicicletaRep;

    @Mock
    private Mensage mensage;
    @Test
    public void cadastroBike() {
        inciarBike();


        assertEquals(bicicleta, service.cadastrar(bicicleta));
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
