package com.api.equipamento.service;

import com.api.equipamento.EquipamentoApplicationTests;
import com.api.equipamento.model.Rede;
import com.api.equipamento.model.Totem;
import com.api.equipamento.repositori.RepRede;
import com.api.equipamento.repositori.RepTotem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TotemServiceTest extends EquipamentoApplicationTests {

    @Autowired
    private TotemService totemService;
    @MockBean
    private RepTotem repTotem;
    @MockBean
    private Rede rede;

    @MockBean
    private Totem totem;
    @MockBean
    private RepRede repRede;

    @Test
    void listaTotem(){
        Assertions.assertNotNull(totemService.listaTotem());
    }
    @Test
    void cadastrarTotem(){
        totem = Mockito.mock(Totem.class);
        Mockito.when(repTotem.save(totem)).thenReturn(totem);
        Mockito.when(totem.getLocalizacao()).thenReturn("Rio de Janeiro");
        Mockito.when(totem.getId()).thenReturn(0);
        totemService.cadastrarTotem(totem);
        Mockito.verify(repTotem, Mockito.times(1)).save(ArgumentMatchers.any(Totem.class));
    }

    @Test
    void cadastrarTotemNull(){
        Mockito.when(totem.getLocalizacao()).thenReturn("");
        totemService.cadastrarTotem(totem);
        Mockito.verify(repTotem, Mockito.times(0)).save(ArgumentMatchers.any(Totem.class));
    }
    @Test
    void mostrarTotem(){
        Mockito.when(repTotem.findById(totem.getId())).thenReturn(totem);
        Assertions.assertEquals(totemService.mostrarTotem(totem.getId()), totem);
    }

    @Test
    public void alterarTotem(){
        totem = Mockito.mock(Totem.class);
        Mockito.when(repTotem.findById(0)).thenReturn(totem);
        totemService.alterarTotem(totem, 0);

        Mockito.verify(repTotem, Mockito.times(1)).save(ArgumentMatchers.any(Totem.class));
    }

    @Test
    public void alterarTotemNull(){
        Mockito.when(repTotem.findById(0)).thenReturn(null);
        totemService.alterarTotem(totem, 0);

        Mockito.verify(repTotem, Mockito.times(0)).save(ArgumentMatchers.any(Totem.class));
    }
    @Test
    void excluirTotem(){
        totem = Mockito.mock(Totem.class);
        Mockito.when(repTotem.findById(0)).thenReturn(totem);
        totemService.excluirTotem(0);

        Mockito.verify(repTotem, Mockito.times(1)).delete(ArgumentMatchers.any(Totem.class));
    }

    @Test
    public void excluirFalse(){
        Mockito.when(repTotem.findById(0)).thenReturn(null);
        totemService.excluirTotem(0);
        Mockito.verify(repTotem, Mockito.times(0)).delete(ArgumentMatchers.any(Totem.class));
    }

    @Test
    public void listaTrancaTotem() {
        rede = Mockito.mock(Rede.class);
        List<Integer> listIdsFake = new ArrayList<>();
        Mockito.when(repRede.findByIdTotem(0)).thenReturn(rede);
        Mockito.when(rede.getIdTranca()).thenReturn(listIdsFake);
        Assertions.assertNotNull(totemService.listaTrancaTotem(0));
    }

    @Test
    public void listaTrancaTotemNull(){

    }
    @Test
    public void listaBicicletaTotem() {
        rede = Mockito.mock(Rede.class);
        List<Integer> bicicletaList = new ArrayList<>();
        Mockito.when(repRede.findByIdTotem(0)).thenReturn(rede);
        Mockito.when(rede.getIdBicicleta()).thenReturn(bicicletaList);
        Assertions.assertNotNull(totemService.listaBicicletaTotem(0));
    }

    @Test
    public void listaBicicletaTotemNull(){
        Mockito.when(repRede.findByIdTotem(0)).thenReturn(null);
        Assertions.assertNull(totemService.listaBicicletaTotem(0));
    }

}
