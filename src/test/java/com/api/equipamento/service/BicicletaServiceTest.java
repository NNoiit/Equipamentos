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

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
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
        bicicletaService.cadastrar(bicicleta);
        Mockito.verify(bicicletaRep, Mockito.times(1)).save(ArgumentMatchers.any(Bicicleta.class));
    }
    @Test
    @DisplayName("Deve excluir uma bicicleta")
    void excluirBicicletaTest(){
        int bicicletaId = 9;

        bicicleta = criarBicicleta();
        Mockito.when(bicicletaRep.findById(ArgumentMatchers.eq(bicicletaId))).thenReturn(bicicleta);
        Mockito.when(bicicletaRep.countById(bicicletaId)).thenReturn(1);
        mensage = bicicletaService.excluirBicicleta(bicicletaId);
        Mockito.verify(bicicletaRep, Mockito.times(1)).delete(ArgumentMatchers.any(Bicicleta.class));

    }
    @Test
    @DisplayName("Não deve excluir a bicicleta")
    void erroExcluirBicicletaTest(){
        int bicicletaId = 9;

        bicicleta = criarBicicleta();
        Mockito.when(bicicletaRep.findById(ArgumentMatchers.eq(bicicletaId))).thenReturn(bicicleta);
        mensage = bicicletaService.excluirBicicleta(bicicletaId);
        Assertions.assertEquals("Não encontrado", mensage.getMensage());

    }

    @Test
    void listarBicicletas(){

        Mockito.when(bicicletaRep.findAll()).thenReturn(Collections.emptyList());
        bicicletaService.listarBicicletas();

        Mockito.verify(bicicletaRep, Mockito.times(1)).findAll();
    }

    private Bicicleta criarBicicleta() {
        bicicleta = Mockito.mock(Bicicleta.class);
        Mockito.when(bicicleta.getId()).thenReturn(BigDecimal.ROUND_DOWN);
        Mockito.when(bicicleta.getModelo()).thenReturn(String.valueOf("zoe"));
        Mockito.when(bicicleta.getAno()).thenReturn(String.valueOf("2023"));
        Mockito.when(bicicleta.getMarca()).thenReturn(String.valueOf("renault"));
        Mockito.when(bicicleta.getStatus()).thenReturn(Status.LIVRE);
        return bicicleta;
    }
}
