package com.api.equipamento.controller;

import com.api.equipamento.model.Bicicleta;
import com.api.equipamento.model.Erro;
import com.api.equipamento.model.IdsEquipamentos;
import com.api.equipamento.model.Status;
import com.api.equipamento.repositori.RepBicicleta;
import com.api.equipamento.service.BicicletaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("BicicletaControllerTest")
class BicicletaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Erro mensage;

    @MockBean
    private Bicicleta bicicleta;

    @MockBean
    private BicicletaService bicicletaService;

    @MockBean
    private RepBicicleta repBicicleta;

    @Test
    void teste() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status()
                .isOk()).andExpect(content().string("Bem Vindos a Nossa Bike &#9773;"));
    }

    @Test
    void postBicicleta() throws Exception {
        Bicicleta bicicletaTest = new Bicicleta();

        bicicletaTest.setModelo("tester");
        bicicletaTest.setAno("tester");
        //bicicletaTest.setNumero(0);
        bicicletaTest.setStatusBike(Status.NOVA);
        Mockito.when(bicicletaService.cadastrar(any(Bicicleta.class))).thenReturn(bicicletaTest);
        Mockito.when(repBicicleta.save(any(Bicicleta.class))).thenReturn(bicicletaTest);

        this.mockMvc.perform(post("/bicicleta").contentType(MediaType.APPLICATION_JSON_VALUE).content(
                "{\"marca\": \"tester\",\"modelo\": \"tester\",\"ano\":\"tester\",\"numero\":0,\"status\":\"NOVA\"}").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void postBicicletaFail() throws Exception {
        Bicicleta bicicletaTest = new Bicicleta();

        Mockito.when(bicicletaService.cadastrar(any(Bicicleta.class))).thenReturn(null);
        Mockito.when(repBicicleta.save(any(Bicicleta.class))).thenReturn(null);

        this.mockMvc.perform(post("/bicicleta").contentType(MediaType.APPLICATION_JSON_VALUE).content(
                        "{\"marca\": \"tester\",\"modelo\": \"tester\",\"ano\":\"tester\",\"numero\":0,\"status\":\"NOVA\"}").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

    }

    @Test
    void getBicicleta() throws Exception {
        Mockito.when(bicicletaService.listarBicicletas()).thenReturn(Collections.emptyList());
        this.mockMvc.perform(get("/bicicleta")).andDo(print()).andExpect(status().isOk());
    }
    @Test
    void getBicicletaUuid() throws Exception {
        UUID uuid = UUID.randomUUID();
        Bicicleta bike = new Bicicleta();
        Mockito.when(bicicletaService.bicicletaFindId(uuid)).thenReturn(bike);
        this.mockMvc.perform(get("/bicicleta/{id}", uuid)).andDo(print()).andExpect(status().isOk());
    }
    @Test
    void getBicicletaUuidFail() throws Exception {
        UUID uuid = UUID.randomUUID();
        Mockito.when(bicicletaService.bicicletaFindId(uuid)).thenReturn(null);
        this.mockMvc.perform(get("/bicicleta/{id}", uuid)).andDo(print()).andExpect(status().isNotFound());
    }
    @Test
    void  putBicicleta() throws Exception {
        UUID uuid = UUID.randomUUID();
        bicicleta = criarBicicleta();
        Mockito.when(bicicletaService.alterarBicicleta(any(Bicicleta.class), any(UUID.class))).thenReturn(bicicleta);
        this.mockMvc.perform(put("/bicicleta/{id}", uuid).contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"marca\":\"tester\",\"modelo\":\"tester\",\"ano\":\"tester\",\"numero\":0,\"status\":\"NOVA\"}").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void  putBicicletaFail() throws Exception {
        UUID uuid = UUID.randomUUID();
        bicicleta = criarBicicleta();
        Mockito.when(bicicletaService.alterarBicicleta(any(Bicicleta.class), any(UUID.class))).thenReturn(null);
        this.mockMvc.perform(put("/bicicleta/{id}", uuid).contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"marca\":\"tester\",\"modelo\":\"tester\",\"ano\":\"tester\",\"numero\":0,\"status\":\"NOVA\"}").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isNotFound());
    }
    @Test
    void deletarBicicleta() throws Exception {
        UUID uuid = UUID.randomUUID();
        mensage.setMensage("Ok");
        Mockito.when(bicicletaService.excluirBicicleta(any(UUID.class))).thenReturn(mensage);
        this.mockMvc.perform(delete("/bicicleta/{id}", uuid)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void  integrarNaRede() throws Exception {
        Mockito.when(bicicletaService.integrarNaRede(any(IdsEquipamentos.class))).thenReturn(true);
        this.mockMvc.perform(post("/bicicleta/integrarNaRede").contentType(MediaType.APPLICATION_JSON).content("{\"idTanca\":\"f7b77c80-4f43-477f-817a-63ce45a8f40b\",\"idBicicleta\":\"f7b77c80-4f43-477f-817a-63ce45a8f40b\"}").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void  integrarNaRedeFail() throws Exception {
        Mockito.when(bicicletaService.integrarNaRede(any(IdsEquipamentos.class))).thenReturn(false);
        this.mockMvc.perform(post("/bicicleta/integrarNaRede").contentType(MediaType.APPLICATION_JSON).content(
                "{\"idTanca\":\"f7b77c80-4f43-477f-817a-63ce45a8f40b\",\"idBicicleta\":\"f7b77c80-4f43-477f-817a-63ce45a8f40b\"}"
        ).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isUnprocessableEntity());
    }

    @Test
    void retirarDaRede() throws Exception {
        Mockito.when(bicicletaService.retirarDaRede(any(IdsEquipamentos.class))).thenReturn(true);
        this.mockMvc.perform(post("/bicicleta/retirarDaRede").contentType(MediaType.APPLICATION_JSON).content(
                "{\"idTanca\":\"f7b77c80-4f43-477f-817a-63ce45a8f40b\",\"idBicicleta\":\"f7b77c80-4f43-477f-817a-63ce45a8f40b\"}"
        ).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void retirarDaRedeFail() throws Exception {
        Mockito.when(bicicletaService.retirarDaRede(any(IdsEquipamentos.class))).thenReturn(false);
        this.mockMvc.perform(post("/bicicleta/retirarDaRede").contentType(MediaType.APPLICATION_JSON).content(
                "{\"idTanca\":\"f7b77c80-4f43-477f-817a-63ce45a8f40b\",\"idBicicleta\":\"f7b77c80-4f43-477f-817a-63ce45a8f40b\"}"
        ).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isUnprocessableEntity());
    }

    @Test
    void putStatusBicicleta() throws Exception {
        UUID uuid = UUID.randomUUID();
        Mockito.when(bicicletaService.alterarStatusBicicleta(uuid, Status.DISPONIVEL)).thenReturn(mensage);
        Mockito.when(mensage.getMensage()).thenReturn("Ação bem sucedida");
        this.mockMvc.perform(put("/bicicleta/{id}/status/{acao}", uuid, Status.DISPONIVEL)).andDo(print()).andExpect(status().isOk());
    }
    @Test
    void putStatusBicicletaFail() throws Exception {
        UUID uuid = UUID.randomUUID();
        Mockito.when(bicicletaService.alterarStatusBicicleta(uuid, Status.DISPONIVEL)).thenReturn(mensage);
        Mockito.when(mensage.getMensage()).thenReturn("Não encontrado");
        this.mockMvc.perform(put("/bicicleta/{id}/status/{acao}", uuid, Status.DISPONIVEL)).andDo(print()).andExpect(status().isNotFound());
    }
    /*private String converterJson(){
    }*/
    private Bicicleta criarBicicleta() {
        bicicleta = Mockito.mock(Bicicleta.class);
        Mockito.when(bicicleta.getModelo()).thenReturn("tester");
        Mockito.when(bicicleta.getAno()).thenReturn("testr");
        Mockito.when(bicicleta.getMarca()).thenReturn("tester");
        Mockito.when(bicicleta.getStatus()).thenReturn(Status.NOVA);
        return bicicleta;
    }
}
