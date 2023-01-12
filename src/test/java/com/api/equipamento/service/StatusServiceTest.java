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
        Mockito.when(repTranca.findById(0)).thenReturn(tranca);
        statusService.alterarStatusTranca(0, Acao.TRANCAR);
        Mockito.verify(repTranca, Mockito.times(1)).save(ArgumentMatchers.any(Tranca.class));
    }
    @Test
    void alterarStatusTrancaDestrancar(){
        Mockito.when(repTranca.findById(0)).thenReturn(tranca);
        statusService.alterarStatusTranca(0, Acao.DESTRANCAR);
        Mockito.verify(repTranca, Mockito.times(1)).save(ArgumentMatchers.any(Tranca.class));
    }
    @Test
    void inserirBicicletaTranca(){
        Mockito.when(repTranca.findById(0)).thenReturn(tranca);
        Mockito.when(repTranca.findById(0)).thenReturn(tranca);
        Mockito.when(tranca.getStatus()).thenReturn(Status.LIVRE);
        statusService.inserirBicicletaTranca(0, 0);
        Mockito.verify(repTranca, Mockito.times(1)).save(ArgumentMatchers.any(Tranca.class));
    }
}