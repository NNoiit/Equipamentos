package com.api.equipamento.service;

import com.api.equipamento.model.*;
import com.api.equipamento.repositori.RepBicicleta;
import com.api.equipamento.repositori.RepRede;
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
import java.util.UUID;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("BicicletaServiceTest")
class BicicletaServiceTest {

    @Autowired
    private BicicletaService bicicletaService;

    @MockBean
    private RepBicicleta bicicletaRep;
    @MockBean
    private RepRede repRede;
    @MockBean
    private Bicicleta bicicleta;
    @MockBean
    private Rede rede;
    @MockBean
    private IdsEquipamentos idsEquipamentos;

    @Test
    void testCadastro(){
        bicicleta = criarBicicleta();
        Mockito.when(bicicletaRep.save(bicicleta)).thenReturn(bicicleta);
        bicicletaService.cadastrar(bicicleta);
        Mockito.verify(bicicletaRep, Mockito.times(1)).save(ArgumentMatchers.any(Bicicleta.class));
    }
    @Test
    void testErroCadastro(){
        Mockito.when(bicicleta.getModelo()).thenReturn("");
        Assertions.assertNull(bicicletaService.cadastrar(bicicleta));
    }
    @Test
    void listarBicicletas(){

        Mockito.when(bicicletaRep.findAll()).thenReturn(Collections.emptyList());
        bicicletaService.listarBicicletas();

        Mockito.verify(bicicletaRep, Mockito.times(1)).findAll();
    }

    @Test
    void bicicletaFindId(){
        UUID bicicletaId = UUID.randomUUID();

        Mockito.when(bicicletaRep.findByUuid(ArgumentMatchers.eq(bicicletaId))).thenReturn(bicicleta);
        Mockito.when(bicicletaRep.countByUuid(ArgumentMatchers.eq(bicicletaId))).thenReturn(1);
        bicicletaService.bicicletaFindId(bicicletaId);
        Mockito.verify(bicicletaRep, Mockito.times(1)).findByUuid(bicicletaId);
    }
    @Test
    void bicicletaFindIdInexistente(){
        UUID bicicletaId = UUID.randomUUID();

        Mockito.when(bicicletaRep.findByUuid(ArgumentMatchers.eq(bicicletaId))).thenReturn(bicicleta);
        bicicletaService.bicicletaFindId(bicicletaId);
        Mockito.verify(bicicletaRep, Mockito.times(0)).findByUuid(bicicletaId);
        Assertions.assertNull(bicicletaService.bicicletaFindId(bicicletaId));
    }

    @Test
    void alterarBicicleta(){
        UUID bicicletaId = UUID.randomUUID();
        bicicleta = criarBicicleta();
        Mockito.when(bicicletaRep.findByUuid(ArgumentMatchers.eq(bicicletaId))).thenReturn(bicicleta);
        bicicletaService.alterarBicicleta(bicicleta, bicicletaId);
        Mockito.verify(bicicletaRep, Mockito.times(1)).save(ArgumentMatchers.any(Bicicleta.class));
    }

    @Test
    void ErroAlterarBicicleta(){
        UUID bicicletaId = UUID.randomUUID();
        bicicleta = criarBicicleta();
        Mockito.when(bicicletaRep.findByUuid(ArgumentMatchers.eq(bicicletaId))).thenReturn(bicicleta);
        Mockito.when(bicicleta.getModelo()).thenReturn("");
        bicicletaService.alterarBicicleta(bicicleta, bicicletaId);
        Mockito.verify(bicicletaRep, Mockito.times(0)).save(ArgumentMatchers.any(Bicicleta.class));
        Assertions.assertNull(bicicletaService.alterarBicicleta(bicicleta, bicicletaId));
    }


    @Test
    void excluirBicicletaTest(){
        UUID bicicletaId = UUID.randomUUID();

        Mockito.when(bicicletaRep.findByUuid(ArgumentMatchers.eq(bicicletaId))).thenReturn(bicicleta);
        Mockito.when(bicicletaRep.countByUuid(bicicletaId)).thenReturn(1);
        bicicletaService.excluirBicicleta(bicicletaId);
        Mockito.verify(bicicletaRep, Mockito.times(1)).delete(ArgumentMatchers.any(Bicicleta.class));

    }
    @Test
    void erroExcluirBicicletaTest(){
        UUID bicicletaId = UUID.randomUUID();

        Mockito.when(bicicletaRep.findByUuid(ArgumentMatchers.eq(bicicletaId))).thenReturn(bicicleta);
        bicicletaService.excluirBicicleta(bicicletaId);
        Mockito.verify(bicicletaRep, Mockito.times(0)).delete(ArgumentMatchers.any(Bicicleta.class));

    }

    @Test
    void alterarStatusBicicleta(){
        UUID uuid = UUID.randomUUID();
        Mockito.when(bicicletaRep.countByUuid(uuid)).thenReturn(1);
        Mockito.when(bicicletaRep.findByUuid(uuid)).thenReturn(bicicleta);
        String textoMensage = bicicletaService.alterarStatusBicicleta(uuid,Status.LIVRE).getMensage();
        Assertions.assertEquals("Ação bem sucedida", textoMensage);
    }

    @Test
    void alterarStatusBicicletaFalse(){
        UUID uuid = UUID.randomUUID();
        Mockito.when(bicicletaRep.countByUuid(uuid)).thenReturn(0);
        String textoMensage = bicicletaService.alterarStatusBicicleta(uuid,Status.LIVRE).getMensage();
        Assertions.assertEquals("Não encontrado", textoMensage);
    }
    @Test
    void integrarNaRede(){
        List<Rede> listaRedeTest = new ArrayList<>();
        listaRedeTest.add(rede);
        List<UUID> listaIdsTest = new ArrayList<>();
        UUID uuid = UUID.randomUUID();
        listaIdsTest.add(uuid);

        Mockito.when(repRede.findAll()).thenReturn(listaRedeTest);
        Mockito.when(rede.getIdTranca()).thenReturn(listaIdsTest);
        Mockito.when(rede.getIdBicicleta()).thenReturn(listaIdsTest);
        Mockito.when(idsEquipamentos.getIdTranca()).thenReturn(uuid);

        bicicletaService.integrarNaRede(idsEquipamentos);
        Mockito.verify(repRede, Mockito.times(1)).save(ArgumentMatchers.any(Rede.class));
    }
    @Test
    void retirarDaRede(){
        List<Rede> listaRedeTest = new ArrayList<>();
        listaRedeTest.add(rede);
        List<UUID> listaIdsTest = new ArrayList<>();
        UUID uuid = UUID.randomUUID();
        listaIdsTest.add(uuid);

        Mockito.when(repRede.findAll()).thenReturn(listaRedeTest);
        Mockito.when(rede.getIdTranca()).thenReturn(listaIdsTest);
        Mockito.when(rede.getIdBicicleta()).thenReturn(listaIdsTest);
        Mockito.when(idsEquipamentos.getIdTranca()).thenReturn(uuid);
        Mockito.when(idsEquipamentos.getIdBicicleta()).thenReturn(uuid);

        bicicletaService.retirarDaRede(idsEquipamentos);
        Mockito.verify(repRede, Mockito.times(1)).save(ArgumentMatchers.any(Rede.class));
    }
    private Bicicleta criarBicicleta() {
        bicicleta = Mockito.mock(Bicicleta.class);
        Mockito.when(bicicleta.getModelo()).thenReturn("zoe");
        Mockito.when(bicicleta.getAno()).thenReturn("2023");
        Mockito.when(bicicleta.getMarca()).thenReturn("renault");
        Mockito.when(bicicleta.getStatus()).thenReturn(Status.LIVRE);
        return bicicleta;
    }
}
