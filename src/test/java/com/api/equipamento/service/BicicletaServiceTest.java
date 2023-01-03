package com.api.equipamento.service;


import com.api.equipamento.EquipamentoApplicationTests;
import com.api.equipamento.model.Bicicleta;
import com.api.equipamento.model.Status;
import com.api.equipamento.repositori.RepBicicleta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Collections;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("BicicletaServiceTessst")
class BicicletaServiceTest extends EquipamentoApplicationTests {

    @Autowired
    private BicicletaService bicicletaService;

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
    @DisplayName("Devolve um null para tentativa de cadastro")
    void testErroCadastro(){
        Mockito.when(bicicleta.getModelo()).thenReturn("");
        Assertions.assertNull(bicicletaService.cadastrar(bicicleta));
    }
    @Test
    @DisplayName("Verifica se o metodo de listagem esta sendo chamado")
    void listarBicicletas(){

        Mockito.when(bicicletaRep.findAll()).thenReturn(Collections.emptyList());
        bicicletaService.listarBicicletas();

        Mockito.verify(bicicletaRep, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Verifica se o metodo está buscando pelo ID")
    void bicicletaFindId(){
        int bicicletaId = 9;

        Mockito.when(bicicletaRep.findById(ArgumentMatchers.eq(bicicletaId))).thenReturn(bicicleta);
        Mockito.when(bicicletaRep.countById(ArgumentMatchers.eq(bicicletaId))).thenReturn(1);
        bicicletaService.bicicletaFindId(bicicletaId);
        Mockito.verify(bicicletaRep, Mockito.times(1)).findById(bicicletaId);
    }
    @Test
    @DisplayName("Verifica se o metodo está dando erro quando busca por um ID que não existe")
    void bicicletaFindIdInexistente(){
        int bicicletaId = 9;

        Mockito.when(bicicletaRep.findById(ArgumentMatchers.eq(bicicletaId))).thenReturn(bicicleta);
        bicicletaService.bicicletaFindId(bicicletaId);
        Mockito.verify(bicicletaRep, Mockito.times(0)).findById(bicicletaId);
        Assertions.assertNull(bicicletaService.bicicletaFindId(bicicletaId));
    }

    @Test
    @DisplayName("Verifica se o metodo save esta endo chamado passando um novo obj bicicleta")
    void alterarBicicleta(){
        int bicicletaId = 9;
        bicicleta = criarBicicleta();
        Mockito.when(bicicletaRep.findById(ArgumentMatchers.eq(bicicletaId))).thenReturn(bicicleta);
        bicicletaService.alterarBicicleta(bicicleta, bicicletaId);
        Mockito.verify(bicicletaRep, Mockito.times(1)).save(ArgumentMatchers.any(Bicicleta.class));
    }

    @Test
    @DisplayName("Verifica se está retornando nul para quando um parametro não é passado")
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
    @DisplayName("Deve excluir uma bicicleta")
    void excluirBicicletaTest(){
        int bicicletaId = 9;

        Mockito.when(bicicletaRep.findById(ArgumentMatchers.eq(bicicletaId))).thenReturn(bicicleta);
        Mockito.when(bicicletaRep.countById(bicicletaId)).thenReturn(1);
        bicicletaService.excluirBicicleta(bicicletaId);
        Mockito.verify(bicicletaRep, Mockito.times(1)).delete(ArgumentMatchers.any(Bicicleta.class));

    }
    @Test
    @DisplayName("Não deve excluir a bicicleta")
    void erroExcluirBicicletaTest(){
        int bicicletaId = 9;


        Mockito.when(bicicletaRep.findById(ArgumentMatchers.eq(bicicletaId))).thenReturn(bicicleta);
        bicicletaService.excluirBicicleta(bicicletaId);
        Mockito.verify(bicicletaRep, Mockito.times(0)).delete(ArgumentMatchers.any(Bicicleta.class));

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
