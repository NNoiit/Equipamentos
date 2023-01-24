package com.api.equipamento.service;

import com.api.equipamento.model.Acao;
import com.api.equipamento.model.Status;
import com.api.equipamento.model.Tranca;
import com.api.equipamento.repositori.RepTranca;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

@SpringBootTest
class StatusServiceTest {
    @Autowired
    private StatusService statusService;

    @MockBean
    private RepTranca repTranca;
    @MockBean
    private Tranca tranca;

    @Test
    void alterarStatusTranca(){
        UUID uuid = UUID.randomUUID();
        Mockito.when(repTranca.findByUuid(uuid)).thenReturn(tranca);
        statusService.alterarStatusTranca(uuid, Acao.TRANCAR);
        Mockito.verify(repTranca, Mockito.times(1)).save(ArgumentMatchers.any(Tranca.class));
    }
    @Test
    void alterarStatusTrancaDestrancar(){
        UUID uuid = UUID.randomUUID();
        Mockito.when(repTranca.findByUuid(uuid)).thenReturn(tranca);
        statusService.alterarStatusTranca(uuid, Acao.DESTRANCAR);
        Mockito.verify(repTranca, Mockito.times(1)).save(ArgumentMatchers.any(Tranca.class));
    }
    @Test
    void inserirBicicletaTranca(){
        UUID uuid = UUID.randomUUID();
        Mockito.when(repTranca.findByUuid(uuid)).thenReturn(tranca);
        Mockito.when(repTranca.findByUuid(uuid)).thenReturn(tranca);
        Mockito.when(tranca.getStatus()).thenReturn(Status.DISPONIVEL);
        statusService.inserirBicicletaTranca(uuid, uuid);
        Mockito.verify(repTranca, Mockito.times(1)).save(ArgumentMatchers.any(Tranca.class));
    }
}