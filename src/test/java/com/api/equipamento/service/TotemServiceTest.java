package com.api.equipamento.service;


import com.api.equipamento.model.Bicicleta;
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
import java.util.UUID;

@SpringBootTest
class TotemServiceTest{

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
        Mockito.when(totem.getUuid()).thenReturn(UUID.randomUUID());
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
        Mockito.when(repTotem.findByUuid(totem.getUuid())).thenReturn(totem);
        Assertions.assertEquals(totemService.mostrarTotem(totem.getUuid()), totem);
    }

    @Test
    void alterarTotem(){
        UUID uuid = UUID.randomUUID();
        totem = Mockito.mock(Totem.class);
        Mockito.when(repTotem.findByUuid(uuid)).thenReturn(totem);
        totemService.alterarTotem(totem, uuid);

        Mockito.verify(repTotem, Mockito.times(1)).save(ArgumentMatchers.any(Totem.class));
    }

    @Test
    void alterarTotemNull(){
        UUID uuid = UUID.randomUUID();
        Mockito.when(repTotem.findByUuid(uuid)).thenReturn(null);
        totemService.alterarTotem(totem, uuid);

        Mockito.verify(repTotem, Mockito.times(0)).save(ArgumentMatchers.any(Totem.class));
    }
    @Test
    void excluirTotem(){
        UUID uuid = UUID.randomUUID();
        totem = Mockito.mock(Totem.class);
        Mockito.when(repTotem.findByUuid(uuid)).thenReturn(totem);
        totemService.excluirTotem(uuid);

        Mockito.verify(repTotem, Mockito.times(1)).delete(ArgumentMatchers.any(Totem.class));
    }

    @Test
    void excluirFalse(){
        UUID uuid = UUID.randomUUID();
        Mockito.when(repTotem.findByUuid(uuid)).thenReturn(null);
        totemService.excluirTotem(uuid);
        Mockito.verify(repTotem, Mockito.times(0)).delete(ArgumentMatchers.any(Totem.class));
    }

    @Test
    void listaTrancaTotem() {
        UUID uuid = UUID.randomUUID();
        rede = Mockito.mock(Rede.class);
        List<UUID> listIdsFake = new ArrayList<>();
        Mockito.when(repRede.findByIdTotem(uuid)).thenReturn(rede);
        Mockito.when(rede.getIdTranca()).thenReturn(listIdsFake);
        Assertions.assertNotNull(totemService.listaTrancaTotem(uuid));
    }
    @Test
    void listaBicicletaTotem() {
        rede = Mockito.mock(Rede.class);
        List<UUID> bicicletaList = new ArrayList<>();
        UUID uuid = UUID.randomUUID();
        Mockito.when(repRede.findByIdTotem(uuid)).thenReturn(rede);
        Mockito.when(rede.getIdBicicleta()).thenReturn(bicicletaList);
        Assertions.assertNotNull(totemService.listaBicicletaTotem(uuid));
    }

    @Test
    void listaBicicletaTotemNull(){
        UUID uuid = UUID.randomUUID();
        Mockito.when(repRede.findByIdTotem(uuid)).thenReturn(null);
        List<Bicicleta> bikeList = totemService.listaBicicletaTotem(uuid);
        Assertions.assertFalse(bikeList.size() > 1);

    }

}
