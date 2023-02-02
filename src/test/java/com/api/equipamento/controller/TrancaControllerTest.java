package com.api.equipamento.controller;

import com.api.equipamento.model.*;
import com.api.equipamento.repositori.RepBicicleta;
import com.api.equipamento.repositori.RepRede;
import com.api.equipamento.repositori.RepTotem;
import com.api.equipamento.repositori.RepTranca;
import com.api.equipamento.service.BicicletaService;
import com.api.equipamento.service.StatusService;
import com.api.equipamento.service.TrancaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("TotemControllerTest")
public class TrancaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Tranca tranca;

    @MockBean
    private Bicicleta bicicleta;
    @MockBean
    private RepTranca repTranca;
    @MockBean
    private RepRede repRede;
    @MockBean
    private RepBicicleta repBicicleta;
    @MockBean
    private RepTotem repTotem;
    @MockBean
    private StatusService statusService;
    @MockBean
    private BicicletaService bicicletaService;

    @MockBean
    private TrancaService trancaService;


    @Test
    void postTranca() throws Exception {
        tranca = criarTranca();
        Mockito.when(trancaService.cadastrarTranca(any(Tranca.class))).thenReturn(tranca);
        this.mockMvc.perform(post("/tranca").contentType(MediaType.APPLICATION_JSON).content(
                        "{\"numero\": 1,\"localizacao\":\"Rio de Janeiro\",\"anoDeFabricacao\":\"01/02/2023\",\"modelo\":\"nort\",\"status\":\"NOVA\"}").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    void postTrancaFail() throws Exception {
        Mockito.when(trancaService.cadastrarTranca(tranca)).thenReturn(tranca);
        this.mockMvc.perform(post("/tranca").contentType(MediaType.APPLICATION_JSON).content(
                        "{\n" +
                                "        \"numero\": 1,\n" +
                                "        \"localizacao\": \"Rio de Janeiro\",\n" +
                                "        \"anoDeFabricacao\": \"01/02/2023\",\n" +
                                "        \"modelo\": \"nort\",\n" +
                                "        \"status\": \"NOVA\"\n" +
                                "    }").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void getTrancaList() throws Exception {
        Mockito.when(trancaService.listarTrancas()).thenReturn(Collections.emptyList());
        this.mockMvc.perform(get("/tranca")).andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    void getTranca() throws Exception {
        UUID uuid = UUID.randomUUID();
        tranca = criarTranca();
        Mockito.when(trancaService.trancaFindId(any(UUID.class))).thenReturn(tranca);
        this.mockMvc.perform(get("/tranca/{id}", uuid)).andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    void getTrancaFail() throws Exception {
        UUID uuid = UUID.randomUUID();
        Mockito.when(trancaService.trancaFindId(any(UUID.class))).thenReturn(null);
        this.mockMvc.perform(get("/tranca/{id}", uuid)).andDo(print())
                .andExpect(status().isNotFound());
    }
    @Test
    void putTranca() throws Exception{
        tranca = criarTranca();
        UUID uuid = UUID.randomUUID();
        Mockito.when(trancaService.alterarTranca(any(Tranca.class), any(UUID.class))).thenReturn(tranca);
        this.mockMvc.perform(put("/tranca/{id}", uuid).contentType(MediaType.APPLICATION_JSON).content(
                        "{\n" +
                                "        \"numero\": 1,\n" +
                                "        \"localizacao\": \"Rio de Janeiro\",\n" +
                                "        \"anoDeFabricacao\": \"01/02/2023\",\n" +
                                "        \"modelo\": \"nort\",\n" +
                                "        \"status\": \"NOVA\"\n" +
                                "    }").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    void putTrancaFail() throws Exception{
        UUID uuid = UUID.randomUUID();
        Mockito.when(trancaService.alterarTranca(any(Tranca.class), any(UUID.class))).thenReturn(null);
        this.mockMvc.perform(put("/tranca/{id}", uuid).contentType(MediaType.APPLICATION_JSON).content(
                        "{\n" +
                                "        \"numero\": 1,\n" +
                                "        \"localizacao\": \"Rio de Janeiro\",\n" +
                                "        \"anoDeFabricacao\": \"01/02/2023\",\n" +
                                "        \"modelo\": \"nort\",\n" +
                                "        \"status\": \"NOVA\"\n" +
                                "    }").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteTrancaId() throws Exception{
        UUID uuid = UUID.randomUUID();
        Mockito.when(trancaService.excluirTranca(uuid)).thenReturn(true);
        this.mockMvc.perform(delete("/tranca/{id}", uuid)).andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    void deleteTrancaIdFail() throws Exception{
        UUID uuid = UUID.randomUUID();
        Mockito.when(trancaService.excluirTranca(uuid)).thenReturn(false);
        this.mockMvc.perform(delete("/tranca/{id}", uuid)).andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void trancarTranca() throws Exception{
        UUID uuid = UUID.randomUUID();
        Mockito.when(trancaService.trancarTranca(any(UUID.class), any(UUID.class))).thenReturn(true);
        this.mockMvc.perform(post("/tranca/{id}/trancar", uuid).contentType(MediaType.APPLICATION_JSON).content(
                        "{\n" +
                                "    \"bicicleta\":\"328a9942-f7ce-4244-9ae8-447693a78f52\"\n" +
                                "}").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    void trancarTrancaFail() throws Exception{
        UUID uuid = UUID.randomUUID();
        Mockito.when(trancaService.trancarTranca(any(UUID.class), any(UUID.class))).thenReturn(false);
        this.mockMvc.perform(post("/tranca/{id}/trancar", uuid).contentType(MediaType.APPLICATION_JSON).content(
                        "{\n" +
                                "    \"bicicleta\":\"328a9942-f7ce-4244-9ae8-447693a78f52\"\n" +
                                "}").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isNotFound());
    }
    @Test
    void destrancarTranca() throws Exception{
        UUID uuid = UUID.randomUUID();
        Mockito.when(trancaService.destrancarTranca(any(UUID.class), any(UUID.class))).thenReturn(true);
        this.mockMvc.perform(post("/tranca/{id}/destrancar", uuid).contentType(MediaType.APPLICATION_JSON).content(
                        "{\n" +
                                "    \"bicicleta\":\"328a9942-f7ce-4244-9ae8-447693a78f52\"\n" +
                                "}").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    void destrancarTrancaFail() throws Exception{
        UUID uuid = UUID.randomUUID();
        Mockito.when(trancaService.destrancarTranca(any(UUID.class), any(UUID.class))).thenReturn(false);
        this.mockMvc.perform(post("/tranca/{id}/destrancar", uuid).contentType(MediaType.APPLICATION_JSON).content(
                        "{\n" +
                                "    \"bicicleta\":\"328a9942-f7ce-4244-9ae8-447693a78f52\"\n" +
                                "}").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isNotFound());
    }
    @Test
    void alterarStatusTranca() throws Exception{
        UUID uuid = UUID.randomUUID();
        this.mockMvc.perform(post("/tranca/{id}/status/{acao}", uuid, Acao.DESTRANCAR))
                .andDo(print()).andExpect(status().isOk());
    }
    @Test
    void bicicletaTranca() throws Exception {
        bicicleta = Mockito.mock(Bicicleta.class);
        UUID uuid = UUID.randomUUID();
        Mockito.when(trancaService.getBicicleta(any(UUID.class))).thenReturn(bicicleta);
        this.mockMvc.perform(get("/tranca/{id}/bicicleta", uuid))
                .andDo(print()).andExpect(status().isOk());
    }
    @Test
    void bicicletaTrancaFail() throws Exception {
        bicicleta = Mockito.mock(Bicicleta.class);
        UUID uuid = UUID.randomUUID();
        Mockito.when(trancaService.getBicicleta(any(UUID.class))).thenReturn(null);
        this.mockMvc.perform(get("/tranca/{id}/bicicleta", uuid))
                .andDo(print()).andExpect(status().isNotFound());
    }
    @Test
    void integrarNaRede() throws Exception{
        Mockito.when(trancaService.adicionaTrancaRede(any(IdsEquipamentos.class))).thenReturn(true);
        this.mockMvc.perform(post("/tranca/integrarNaRede").contentType(MediaType.APPLICATION_JSON).content(
                        "{\n" +
                                "    \"idTotem\": \"e04cd5d7-4400-478b-9c15-0bb84494ad02\",\n" +
                                "    \"idTranca\":\"f460a877-2e50-440b-b170-79d3fa829786\"\n" +
                                "}").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    void integrarNaRedeFail() throws Exception{
        Mockito.when(trancaService.adicionaTrancaRede(any(IdsEquipamentos.class))).thenReturn(false);
        this.mockMvc.perform(post("/tranca/integrarNaRede").contentType(MediaType.APPLICATION_JSON).content(
                        "{\n" +
                                "    \"idTotem\": \"e04cd5d7-4400-478b-9c15-0bb84494ad02\",\n" +
                                "    \"idTranca\":\"f460a877-2e50-440b-b170-79d3fa829786\"\n" +
                                "}").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void ritirarDaRede() throws Exception{
        Mockito.when(trancaService.removerTrancaRede(any(IdsEquipamentos.class))).thenReturn(true);
        this.mockMvc.perform(post("/tranca/retirarDaRede").contentType(MediaType.APPLICATION_JSON).content(
                        "{\n" +
                                "    \"idTotem\": \"e04cd5d7-4400-478b-9c15-0bb84494ad02\",\n" +
                                "    \"idTranca\":\"f460a877-2e50-440b-b170-79d3fa829786\"\n" +
                                "}").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    void retirarDaRedeFail() throws Exception{
        Mockito.when(trancaService.removerTrancaRede(any(IdsEquipamentos.class))).thenReturn(false);
        this.mockMvc.perform(post("/tranca/retirarDaRede").contentType(MediaType.APPLICATION_JSON).content(
                        "{\n" +
                                "    \"idTotem\": \"e04cd5d7-4400-478b-9c15-0bb84494ad02\",\n" +
                                "    \"idTranca\":\"f460a877-2e50-440b-b170-79d3fa829786\"\n" +
                                "}").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
    private Tranca criarTranca(){
        tranca = Mockito.mock(Tranca.class);

        Mockito.when(tranca.getStatus()).thenReturn(Status.NOVA);
        Mockito.when(tranca.getBicicleta()).thenReturn(null);
        Mockito.when(tranca.getModelo()).thenReturn("test");
        Mockito.when(tranca.getNumero()).thenReturn(0);
        Mockito.when(tranca.getLocalizacao()).thenReturn("test");
        Mockito.when(tranca.getAnoDeFabricacao()).thenReturn("test");
        Mockito.when(tranca.getUuid()).thenReturn(UUID.randomUUID());
        return tranca;
    }
}
