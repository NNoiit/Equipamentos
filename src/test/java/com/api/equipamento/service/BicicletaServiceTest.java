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
    private StatusService statusService;
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
        int bicicletaId = 9;

        Mockito.when(bicicletaRep.findById(ArgumentMatchers.eq(bicicletaId))).thenReturn(bicicleta);
        Mockito.when(bicicletaRep.countById(ArgumentMatchers.eq(bicicletaId))).thenReturn(1);
        bicicletaService.bicicletaFindId(bicicletaId);
        Mockito.verify(bicicletaRep, Mockito.times(1)).findById(bicicletaId);
    }
    @Test
    void bicicletaFindIdInexistente(){
        int bicicletaId = 9;

        Mockito.when(bicicletaRep.findById(ArgumentMatchers.eq(bicicletaId))).thenReturn(bicicleta);
        bicicletaService.bicicletaFindId(bicicletaId);
        Mockito.verify(bicicletaRep, Mockito.times(0)).findById(bicicletaId);
        Assertions.assertNull(bicicletaService.bicicletaFindId(bicicletaId));
    }

    @Test
    void alterarBicicleta(){
        int bicicletaId = 9;
        bicicleta = criarBicicleta();
        Mockito.when(bicicletaRep.findById(ArgumentMatchers.eq(bicicletaId))).thenReturn(bicicleta);
        bicicletaService.alterarBicicleta(bicicleta, bicicletaId);
        Mockito.verify(bicicletaRep, Mockito.times(1)).save(ArgumentMatchers.any(Bicicleta.class));
    }

    @Test
    void ErroAlterarBicicleta(){
        int bicicletaId = 9;
        bicicleta = criarBicicleta();
        Mockito.when(bicicletaRep.findById(ArgumentMatchers.eq(bicicletaId))).thenReturn(bicicleta);
        Mockito.when(bicicleta.getModelo()).thenReturn("");
        bicicletaService.alterarBicicleta(bicicleta, bicicletaId);
        Mockito.verify(bicicletaRep, Mockito.times(0)).save(ArgumentMatchers.any(Bicicleta.class));
        Assertions.assertNull(bicicletaService.alterarBicicleta(bicicleta, bicicletaId));
    }


    @Test
    void excluirBicicletaTest(){
        int bicicletaId = 9;

        Mockito.when(bicicletaRep.findById(ArgumentMatchers.eq(bicicletaId))).thenReturn(bicicleta);
        Mockito.when(bicicletaRep.countById(bicicletaId)).thenReturn(1);
        bicicletaService.excluirBicicleta(bicicletaId);
        Mockito.verify(bicicletaRep, Mockito.times(1)).delete(ArgumentMatchers.any(Bicicleta.class));

    }
    @Test
    void erroExcluirBicicletaTest(){
        int bicicletaId = 9;


        Mockito.when(bicicletaRep.findById(ArgumentMatchers.eq(bicicletaId))).thenReturn(bicicleta);
        bicicletaService.excluirBicicleta(bicicletaId);
        Mockito.verify(bicicletaRep, Mockito.times(0)).delete(ArgumentMatchers.any(Bicicleta.class));

    }

    @Test
    void alterarStatusBicicleta(){
        Mockito.when(bicicletaRep.countById(0)).thenReturn(1);
        Mockito.when(bicicletaRep.findById(0)).thenReturn(bicicleta);
        String textoMensage = bicicletaService.alterarStatusBicicleta(0,Status.LIVRE).getMensage();
        Assertions.assertEquals("Ação bem sucedida", textoMensage);
    }

    @Test
    void alterarStatusBicicletaFalse(){
        Mockito.when(bicicletaRep.countById(0)).thenReturn(0);
        String textoMensage = bicicletaService.alterarStatusBicicleta(0,Status.LIVRE).getMensage();
        Assertions.assertEquals("Não encontrado", textoMensage);
    }
    @Test
    void integrarNaRede(){
        List<Rede> listaRedeTest = new ArrayList<>();
        listaRedeTest.add(rede);
        List<Integer> listaIdsTest = new ArrayList<>();
        listaIdsTest.add(0);

        Mockito.when(repRede.findAll()).thenReturn(listaRedeTest);
        Mockito.when(rede.getIdTranca()).thenReturn(listaIdsTest);
        Mockito.when(rede.getIdBicicleta()).thenReturn(listaIdsTest);
        //Mockito.when(statusService.inserirBicicletaTranca(0,0)).equals(true);
        Mockito.when(idsEquipamentos.getIdTranca()).thenReturn(0);

        bicicletaService.integrarNaRede(idsEquipamentos);
        Mockito.verify(repRede, Mockito.times(1)).save(ArgumentMatchers.any(Rede.class));
    }
    @Test
    void retirarDaRede(){
        List<Rede> listaRedeTest = new ArrayList<>();
        listaRedeTest.add(rede);
        List<Integer> listaIdsTest = new ArrayList<>();
        listaIdsTest.add(0);

        Mockito.when(repRede.findAll()).thenReturn(listaRedeTest);
        Mockito.when(rede.getIdTranca()).thenReturn(listaIdsTest);
        Mockito.when(rede.getIdBicicleta()).thenReturn(listaIdsTest);
        Mockito.when(idsEquipamentos.getIdTranca()).thenReturn(0);

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
