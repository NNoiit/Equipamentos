package com.api.equipamento.service;

import com.api.equipamento.EquipamentoApplicationTests;
import com.api.equipamento.model.*;
import com.api.equipamento.repositori.RepBicicleta;
import com.api.equipamento.repositori.RepRede;
import com.api.equipamento.repositori.RepTranca;
import org.junit.jupiter.api.Assertions;
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
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
class TrancaServiceTest extends EquipamentoApplicationTests {

    @Autowired
    private TrancaService service;
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

        UUID trancaId = UUID.randomUUID();
        Mockito.when(trancaRep.countByUuid(trancaId)).thenReturn(1);
        service.trancaFindId(trancaId);
        Mockito.verify(trancaRep, Mockito.times(1)).findByUuid(trancaId);
    }

    @Test
    void erroTrancaFindId(){
        UUID trancaId = UUID.randomUUID();
        Mockito.when(trancaRep.countByUuid(trancaId)).thenReturn(0);
        service.trancaFindId(trancaId);
        Mockito.verify(trancaRep, Mockito.times(0)).findByUuid(trancaId);
        Assertions.assertNull(service.trancaFindId(trancaId));
    }
    @Test
    void alterarTranca(){
        UUID trancaId = UUID.randomUUID();
        tranca = criarTranca();
        Mockito.when(trancaRep.findByUuid(trancaId)).thenReturn(tranca);
        Mockito.when(trancaRep.countByUuid(trancaId)).thenReturn(1);
        service.alterarTranca(tranca, trancaId);
        Mockito.verify(trancaRep, Mockito.times(1)).save(ArgumentMatchers.any(Tranca.class));
    }

    @Test
    void novAlterarTranca(){
        UUID trancaId = UUID.randomUUID();
        tranca = criarTranca();
        Mockito.when(trancaRep.findByUuid(trancaId)).thenReturn(tranca);
        Mockito.when(tranca.getModelo()).thenReturn("");
        Mockito.when(trancaRep.countByUuid(trancaId)).thenReturn(0);
        service.alterarTranca(tranca, trancaId);
        Mockito.verify(trancaRep, Mockito.times(0)).save(ArgumentMatchers.any(Tranca.class));
        Assertions.assertNull(service.alterarTranca(tranca, trancaId));
    }
    @Test
    void excluirTranca(){
        UUID trancaId = UUID.randomUUID();
        tranca = criarTranca();
        Mockito.when(trancaRep.countByUuid(trancaId)).thenReturn(1);
        Mockito.when(trancaRep.findByUuid(trancaId)).thenReturn(tranca);
        service.excluirTranca(trancaId);
        Mockito.verify(trancaRep, Mockito.times(1)).delete(ArgumentMatchers.any(Tranca.class));
    }

    @Test
    void erroExcluirTranca(){
        UUID uuid = UUID.randomUUID();
        Mockito.when(trancaRep.countByUuid(uuid)).thenReturn(1);
        service.excluirTranca(uuid);
        Mockito.verify(trancaRep, Mockito.times(1)).findByUuid(uuid);
    }

    @Test
    void erroExcluirTrancaFalse(){
        UUID uuid = UUID.randomUUID();
        Mockito.when(trancaRep.countByUuid(uuid)).thenReturn(0);
        Assertions.assertFalse(service.excluirTranca(uuid));
    }
    @Test
    void trancarTranca(){
        UUID uuid = UUID.randomUUID();
        Mockito.when(trancaRep.findByUuid(uuid)).thenReturn(tranca);
        Mockito.when(tranca.getStatus()).thenReturn(Status.LIVRE);

        Assertions.assertTrue(service.trancarTranca(uuid,uuid));
    }

    @Test
    void destrancarTramca(){
        UUID uuid = UUID.randomUUID();
        Mockito.when(trancaRep.findByUuid(uuid)).thenReturn(tranca);
        Mockito.when(tranca.getBicicleta()).thenReturn(uuid);
        Assertions.assertTrue(service.destrancarTranca(uuid,uuid));
    }
    @Test
    void adicionaTrancaRede(){
        rede = Mockito.mock(Rede.class);
        List<UUID> listaFake = new ArrayList<>();
        UUID uuid = UUID.randomUUID();
        Mockito.when(repRede.findByIdTotem(uuid)).thenReturn(rede);
        Mockito.when(trancaRep.countByUuid(uuid)).thenReturn(1);
        Mockito.when(idsEquipamentos.getIdTotem()).thenReturn(uuid);
        Mockito.when(idsEquipamentos.getIdTranca()).thenReturn(uuid);
        Mockito.when(rede.getIdTranca()).thenReturn(listaFake);

        service.adicionaTrancaRede(idsEquipamentos);
        Mockito.verify(repRede, Mockito.times(1)).save(ArgumentMatchers.any(Rede.class));
    }
    @Test
    void removerTrancaRede(){
        List<UUID> listaFake = new ArrayList<>();
        UUID uuid = UUID.randomUUID();

        Mockito.when(repRede.findByIdTotem(uuid)).thenReturn(rede);
        Mockito.when(rede.getIdTranca()).thenReturn(listaFake);
        service.removerTrancaRede(idsEquipamentos);
        Mockito.verify(repRede, Mockito.times(1)).save(ArgumentMatchers.any(Rede.class));
    }

    @Test
    void getBicicleta(){
        UUID uuid = UUID.randomUUID();
        Mockito.when(trancaRep.findByUuid(uuid)).thenReturn(tranca);
        Mockito.when(tranca.getStatus()).thenReturn(Status.OCUPADO);
        Mockito.when(tranca.getBicicleta()).thenReturn(uuid);
        Mockito.when(repBicicleta.findByUuid(uuid)).thenReturn(bicicleta);
        Assertions.assertNotNull(service.getBicicleta(uuid));

    }
    private Tranca criarTranca() {
        tranca = Mockito.mock(Tranca.class);

        Mockito.when(tranca.getAnoDeFabricacao()).thenReturn("2023");
        Mockito.when(tranca.getModelo()).thenReturn("star");
        Mockito.when(tranca.getNumero()).thenReturn(10);
        Mockito.when(tranca.getLocalizacao()).thenReturn("Rio de Janeiro");
        Mockito.when(tranca.getBicicleta()).thenReturn(UUID.randomUUID());
        Mockito.when(tranca.getStatus()).thenReturn(Status.LIVRE);

        return tranca;
    }
}
