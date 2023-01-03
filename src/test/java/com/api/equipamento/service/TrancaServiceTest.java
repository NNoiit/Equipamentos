package com.api.equipamento.service;

import com.api.equipamento.EquipamentoApplicationTests;
import com.api.equipamento.model.Status;
import com.api.equipamento.model.Tranca;
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
import java.util.Collections;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("TrancaServiceTest")
class TrancaServiceTest extends EquipamentoApplicationTests {

    @Autowired
    private TrancaService service;
    @MockBean
    private RepTranca trancaRep;
    @MockBean
    private Tranca tranca;

    @Test
    @DisplayName("Testa se um objeto do tipo tranca está sendo passado no metodo save")
    void cadastrarTranca(){
        tranca = criarTranca();
        service.cadastrarTranca(tranca);
        Mockito.verify(trancaRep, Mockito.times(1)).save(ArgumentMatchers.any(Tranca.class));
    }

    @Test
    @DisplayName("Testa se um objeto do tipo tranca está com parametros em branco")
    void erroCadastrarTranca(){
        tranca = criarTranca();
        Mockito.when(tranca.getModelo()).thenReturn("");
        service.cadastrarTranca(tranca);
        Mockito.verify(trancaRep, Mockito.times(0)).save(ArgumentMatchers.any(Tranca.class));
        Assertions.assertNull(service.cadastrarTranca(tranca));
    }

    @Test
    @DisplayName("verifica se o metodo para listar esta sendo corretamente chamado")
    void listarTrancas(){
        Mockito.when(trancaRep.findAll()).thenReturn(Collections.emptyList());
        service.listarTrancas();
        Mockito.verify(trancaRep, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Verifica se o metodo de busca esta sendo chamado corretamente")
    void trancaFindId(){
        int trancaId = 0;
        Mockito.when(trancaRep.countById(trancaId)).thenReturn(1);
        service.trancaFindId(trancaId);
        Mockito.verify(trancaRep, Mockito.times(1)).findById(trancaId);
    }

    @Test
    @DisplayName("Verifica se quando ID passado não existe, um null e retornado")
    void erroTrancaFindId(){
        int trancaId = 0;
        Mockito.when(trancaRep.countById(trancaId)).thenReturn(0);
        service.trancaFindId(trancaId);
        Mockito.verify(trancaRep, Mockito.times(0)).findById(trancaId);
        Assertions.assertNull(service.trancaFindId(trancaId));
    }
    @Test
    @DisplayName("Verificando se o metodo para salvar uma tranca alterada está sendo chamado corretamente")
    void alterarTranca(){
        int trancaId = 0;
        tranca = criarTranca();
        Mockito.when(trancaRep.findById(trancaId)).thenReturn(tranca);
        Mockito.when(trancaRep.countById(trancaId)).thenReturn(1);
        service.alterarTranca(tranca, trancaId);
        Mockito.verify(trancaRep, Mockito.times(1)).save(ArgumentMatchers.any(Tranca.class));
    }

    @Test
    @DisplayName("Verificando se a tranca nova possui parametros em branco")
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
    @DisplayName("Verifica se o metodo delete está sendo chamado corretamente dentro da classe")
    void excluirTranca(){
        int trancaId = 0;
        tranca = criarTranca();
        Mockito.when(trancaRep.countById(trancaId)).thenReturn(1);
        Mockito.when(trancaRep.findById(trancaId)).thenReturn(tranca);
        service.excluirTranca(trancaId);
        Mockito.verify(trancaRep, Mockito.times(1)).delete(ArgumentMatchers.any(Tranca.class));
    }

    @Test
    @DisplayName("Verifica se o metodo delete não pode ser chamado dentro da classe")
    void erroExcluirTranca(){
        int trancaId = 0;
        Mockito.when(trancaRep.countById(trancaId)).thenReturn(0);
        service.excluirTranca(trancaId);
        Mockito.verify(trancaRep, Mockito.times(1)).findById(trancaId);
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
