package com.api.equipamento.service;

import com.api.equipamento.EquipamentoApplicationTests;
import com.api.equipamento.model.*;
import com.api.equipamento.repositori.RepBicicleta;
import com.api.equipamento.repositori.RepRede;
import com.api.equipamento.repositori.RepTranca;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class TrancaServiceTest extends EquipamentoApplicationTests {

    @Autowired
    private TrancaService service;

    @MockBean
    private BicicletaService bicicletaService;
    @MockBean
    private RepTranca trancaRep;
    @MockBean
    private RepBicicleta repBicicleta;
    @MockBean
    private RepRede repRede;
    @MockBean
    private Tranca tranca;
    @MockBean
    private  Bicicleta bicicleta;
    @MockBean
    private Totem totem;
    @MockBean
    private IdsEquipamentos idsEquipamentos;

    @MockBean
    private Rede rede;

    @Test
    void cadastrarTranca(){
        tranca = criarTranca();
        service.cadastrarTranca(tranca);
        Mockito.verify(trancaRep, Mockito.times(1)).save(ArgumentMatchers.any(Tranca.class));
    }

    @Test
    void erroCadastrarTranca(){
        tranca = criarTranca();
        Mockito.when(tranca.getModelo()).thenReturn("");
        service.cadastrarTranca(tranca);
        Mockito.verify(trancaRep, Mockito.times(0)).save(ArgumentMatchers.any(Tranca.class));
        Assertions.assertNull(service.cadastrarTranca(tranca));
    }

    @Test
    void listarTrancas(){
        Mockito.when(trancaRep.findAll()).thenReturn(Collections.emptyList());
        service.listarTrancas();
        Mockito.verify(trancaRep, Mockito.times(1)).findAll();
    }

    @Test
    void trancaFindId(){
        int trancaId = 0;
        Mockito.when(trancaRep.countById(trancaId)).thenReturn(1);
        service.trancaFindId(trancaId);
        Mockito.verify(trancaRep, Mockito.times(1)).findById(trancaId);
    }

    @Test
    void erroTrancaFindId(){
        int trancaId = 0;
        Mockito.when(trancaRep.countById(trancaId)).thenReturn(0);
        service.trancaFindId(trancaId);
        Mockito.verify(trancaRep, Mockito.times(0)).findById(trancaId);
        Assertions.assertNull(service.trancaFindId(trancaId));
    }
    @Test
    void alterarTranca(){
        int trancaId = 0;
        tranca = criarTranca();
        Mockito.when(trancaRep.findById(trancaId)).thenReturn(tranca);
        Mockito.when(trancaRep.countById(trancaId)).thenReturn(1);
        service.alterarTranca(tranca, trancaId);
        Mockito.verify(trancaRep, Mockito.times(1)).save(ArgumentMatchers.any(Tranca.class));
    }

    @Test
    void novAlterarTranca(){
        int trancaId = 0;
        tranca = criarTranca();
        Mockito.when(trancaRep.findById(trancaId)).thenReturn(tranca);
        Mockito.when(tranca.getModelo()).thenReturn("");
        Mockito.when(trancaRep.countById(trancaId)).thenReturn(0);
        service.alterarTranca(tranca, trancaId);
        Mockito.verify(trancaRep, Mockito.times(0)).save(ArgumentMatchers.any(Tranca.class));
        Assertions.assertNull(service.alterarTranca(tranca, trancaId));
    }
    @Test
    void excluirTranca(){
        int trancaId = 0;
        tranca = criarTranca();
        Mockito.when(trancaRep.countById(trancaId)).thenReturn(1);
        Mockito.when(trancaRep.findById(trancaId)).thenReturn(tranca);
        service.excluirTranca(trancaId);
        Mockito.verify(trancaRep, Mockito.times(1)).delete(ArgumentMatchers.any(Tranca.class));
    }

    @Test
    void erroExcluirTranca(){
        Mockito.when(trancaRep.countById(1)).thenReturn(1);
        service.excluirTranca(1);
        Mockito.verify(trancaRep, Mockito.times(1)).findById(1);
    }

    @Test
    void erroExcluirTrancaFalse(){
        Mockito.when(trancaRep.countById(1)).thenReturn(0);
        Assertions.assertFalse(service.excluirTranca(1));
    }
    @Test
    void trancarTranca(){
        Mockito.when(trancaRep.findById(0)).thenReturn(tranca);
        Mockito.when(tranca.getStatus()).thenReturn(Status.LIVRE);

        Assertions.assertTrue(service.trancarTranca(0,0));
    }

    @Test
    void destrancarTramca(){
        Mockito.when(trancaRep.findById(0)).thenReturn(tranca);
        Mockito.when(tranca.getBicicleta()).thenReturn(0);
        service.destrancarTranca(0,0);
    }
    @Test
    void adicionaTrancaRede(){
        rede = Mockito.mock(Rede.class);
        List<Integer> listaFake = new ArrayList<>();
        Mockito.when(repRede.findByIdTotem(1)).thenReturn(rede);
        Mockito.when(trancaRep.countById(1)).thenReturn(1);
        Mockito.when(idsEquipamentos.getIdTotem()).thenReturn(1);
        Mockito.when(idsEquipamentos.getIdTranca()).thenReturn(1);
        Mockito.when(rede.getIdTranca()).thenReturn(listaFake);

        service.adicionaTrancaRede(idsEquipamentos);
        Mockito.verify(repRede, Mockito.times(1)).save(ArgumentMatchers.any(Rede.class));
    }
    @Test
    @DisplayName("Verifica a retirada da tranca do totem")
    void removerTrancaRede(){
        List<Integer> listaFake = new ArrayList<>();
        Mockito.when(repRede.findByIdTotem(0)).thenReturn(rede);
        Mockito.when(rede.getIdTranca()).thenReturn(listaFake);
        service.removerTrancaRede(idsEquipamentos);
        Mockito.verify(repRede, Mockito.times(1)).save(ArgumentMatchers.any(Rede.class));
    }

    @Test
    void getBicicleta(){
        Mockito.when(trancaRep.findById(0)).thenReturn(tranca);
        Mockito.when(tranca.getStatus()).thenReturn(Status.OCUPADO);
        Mockito.when(tranca.getBicicleta()).thenReturn(0);
        Mockito.when(repBicicleta.findById(0)).thenReturn(bicicleta);
        service.getBicicleta(0);
    }
    private Tranca criarTranca() {
        tranca = Mockito.mock(Tranca.class);

        Mockito.when(tranca.getAnoDeFabricacao()).thenReturn("2023");
        Mockito.when(tranca.getModelo()).thenReturn("star");
        Mockito.when(tranca.getNumero()).thenReturn(10);
        Mockito.when(tranca.getLocalizacao()).thenReturn("Rio de Janeiro");
        Mockito.when(tranca.getBicicleta()).thenReturn(0);
        Mockito.when(tranca.getStatus()).thenReturn(Status.LIVRE);

        return tranca;
    }
}
