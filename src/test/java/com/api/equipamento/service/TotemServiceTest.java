package com.api.equipamento.service;

import com.api.equipamento.EquipamentoApplicationTests;
import com.api.equipamento.model.Rede;
import com.api.equipamento.model.Totem;
import com.api.equipamento.repositori.RepTotem;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class TotemServiceTest extends EquipamentoApplicationTests {

    @Autowired
    private TotemService totemService;
    @MockBean
    private RepTotem repTotem;
    @MockBean
    private RedeService serviceRede;

    @MockBean
    private Rede rede;

    @MockBean
    private Totem totem;

    @Test
    void cadastrarTotem(){
        totem = Mockito.mock(Totem.class);
        //Mockito.when(serviceRede.criarRedeId(0)).thenReturn(rede);
        Mockito.when(repTotem.save(totem)).thenReturn(totem);
        Mockito.when(totem.getId()).thenReturn(0);
        totemService.cadastrarTotem(totem);
        Mockito.verify(repTotem, Mockito.times(1)).save(ArgumentMatchers.any(Totem.class));
    }

    @Test
    void mostrarTotem(){
        Mockito.when(repTotem.findById(totem.getId())).thenReturn(totem);
        Assertions.assertEquals(totemService.mostrarTotem(totem.getId()), totem);
    }

    @Test
    void alterarTotem(){
        totem = Mockito.mock(Totem.class);
        Mockito.when(repTotem.findById(0)).thenReturn(totem);
        totemService.alterarTotem(totem, 0);

        Mockito.verify(repTotem, Mockito.times(1)).save(ArgumentMatchers.any(Totem.class));
    }

    @Test
    void excluirTotem(){
        totem = Mockito.mock(Totem.class);
        Mockito.when(repTotem.findById(0)).thenReturn(totem);
        totemService.excluirTotem(0);

        Mockito.verify(repTotem, Mockito.times(1)).delete(ArgumentMatchers.any(Totem.class));
    }
}
