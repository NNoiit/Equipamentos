package com.api.equipamento.controller;


import com.api.equipamento.model.Erro;
import com.api.equipamento.model.Totem;
import com.api.equipamento.service.TotemService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("TotemControllerTest")
public class TotemControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private Erro mensage;
    @MockBean
    private TotemService totemService;

    @MockBean
    private  Totem totem;

    @Test
    void getTotem() throws Exception {
        Mockito.when(totemService.listaTotem()).thenReturn(Collections.emptyList());

        this.mockMvc.perform(get("/totem")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void setTotem() throws Exception {
        Mockito.when(totemService.cadastrarTotem(any(Totem.class))).thenReturn(totem);
        this.mockMvc.perform(post("/totem").contentType(MediaType.APPLICATION_JSON).content(
                "{\"localizacao\":\"test\"}").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void setTotemFail() throws Exception {
        Mockito.when(totemService.cadastrarTotem(any(Totem.class))).thenReturn(null);
        this.mockMvc.perform(post("/totem").contentType(MediaType.APPLICATION_JSON_VALUE).content(
                        "{\"localizacao\":\"test\"}").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
    @Test
    void mostraTotem() throws Exception {
        UUID uuid = UUID.randomUUID();
        Mockito.when(totemService.mostrarTotem(uuid)).thenReturn(totem);
        this.mockMvc.perform(get("/totem/{id}", uuid)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void putTotem() throws Exception {
        UUID uuid = UUID.randomUUID();
        totem = criaTotem();
        Mockito.when(totemService.alterarTotem(any(Totem.class), any(UUID.class))).thenReturn(totem);
        this.mockMvc.perform(put("/totem/{id}", uuid).contentType(MediaType.APPLICATION_JSON_VALUE).content(
                        "{\"localizacao\":\"test\"}").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void putTotemFail() throws Exception {
        UUID uuid = UUID.randomUUID();
        Mockito.when(totemService.alterarTotem(any(Totem.class), any(UUID.class))).thenReturn(null);
        Mockito.when(totem.getLocalizacao()).thenReturn(null);
        this.mockMvc.perform(put("/totem/{id}", uuid).contentType(MediaType.APPLICATION_JSON_VALUE).content(
                        "{\"uui\":\"null\"}").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void deletTotem() throws Exception {
        UUID uuid = UUID.randomUUID();
        Mockito.when(totemService.excluirTotem(uuid)).thenReturn(true);
        this.mockMvc.perform(delete("/totem/{id}", uuid)).andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    void deletTotemFail() throws Exception {
        UUID uuid = UUID.randomUUID();
        Mockito.when(totemService.excluirTotem(uuid)).thenReturn(false);
        this.mockMvc.perform(delete("/totem/{id}", uuid)).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void lisstaTrancasTotem() throws Exception {
        UUID uuid = UUID.randomUUID();
        Mockito.when(totemService.listaTrancaTotem(uuid)).thenReturn(Collections.emptyList());
        this.mockMvc.perform(get("/totem/{id}/trancas", uuid)).andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    void lisstaTrancasTotemFail() throws Exception {
        UUID uuid = UUID.randomUUID();
        Mockito.when(totemService.listaTrancaTotem(uuid)).thenReturn(null);
        this.mockMvc.perform(get("/totem/{id}/trancas", uuid)).andDo(print())
                .andExpect(status().isNotFound());
    }
    @Test
    void lisstaBicicletasTotem() throws Exception {
        UUID uuid = UUID.randomUUID();
        Mockito.when(totemService.listaBicicletaTotem(uuid)).thenReturn(Collections.emptyList());
        this.mockMvc.perform(get("/totem/{id}/bicicletas", uuid)).andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    void lisstaBicicletasTotemFail() throws Exception {
        UUID uuid = UUID.randomUUID();
        Mockito.when(totemService.listaBicicletaTotem(uuid)).thenReturn(null);
        this.mockMvc.perform(get("/totem/{id}/bicicletas", uuid)).andDo(print())
                .andExpect(status().isNotFound());
    }
    public Totem criaTotem(){
        totem = Mockito.mock(Totem.class);

        Mockito.when(totem.getLocalizacao()).thenReturn("RJ");
        Mockito.when(totem.getUuid()).thenReturn(UUID.randomUUID());
        return totem;
    }
}
