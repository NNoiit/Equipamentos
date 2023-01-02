package com.api.equipamento.service;


import com.api.equipamento.EquipamentoApplicationTests;
import com.api.equipamento.model.Bicicleta;
import com.api.equipamento.model.Mensage;
import com.api.equipamento.model.Status;
import com.api.equipamento.repositori.RepBicicleta;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("BicicletaServiceTessst")
class BicicletaServiceTest extends EquipamentoApplicationTests {

    @Autowired
    private BicicletaService bicicletaService;
    @Autowired
    private Mensage mensage;

    @MockBean
    private RepBicicleta bicicletaRep;

    @MockBean
    private Bicicleta bicicleta;

    @Test
    @DisplayName("Realiza o cadastro de uma nova bicicleta")
    void testCadastro(){
        bicicleta = criarBicicleta();
        Mockito.when(bicicletaRep.save(bicicleta)).thenReturn(bicicleta);
        Assertions.assertEquals(bicicletaService.cadastrar(bicicleta), bicicleta);

    }
    @Test
    @DisplayName("Deve exxcluir uma bicicleta")
    void excluirBicicletaTest(){
        int bicicletaId = 9;

        bicicleta = criarBicicleta();
        Mockito.when(bicicletaRep.findById(ArgumentMatchers.eq(bicicletaId))).thenReturn(bicicleta);
        Mockito.when(bicicletaRep.countById(bicicletaId)).thenReturn(1);
        mensage = bicicletaService.excluirBicicleta(bicicletaId);
        Assertions.assertEquals("Excluido", mensage.getMensage());

    }

    private Bicicleta criarBicicleta() {
        bicicleta = Mockito.mock(Bicicleta.class);
        Mockito.when(bicicleta.getId()).thenReturn(9);
        Mockito.when(bicicleta.getModelo()).thenReturn("zoe");
        Mockito.when(bicicleta.getAno()).thenReturn("2023");
        Mockito.when(bicicleta.getMarca()).thenReturn("renault");
        Mockito.when(bicicleta.getStatus()).thenReturn(Status.LIVRE);
        return bicicleta;
    }
}
