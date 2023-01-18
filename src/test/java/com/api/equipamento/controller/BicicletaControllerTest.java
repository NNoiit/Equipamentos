package com.api.equipamento.controller;

import com.api.equipamento.model.Bicicleta;
import com.api.equipamento.model.Erro;
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
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("BicicletaControllerTest")
public class BicicletaControllerTest {
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

    /*@Test
    void postBicicleta() throws Exception {
        bicicleta = criarBicicleta();
        Mockito.when(bicicletaService.cadastrar(bicicleta)).thenReturn(bicicleta);
        Mockito.when(repBicicleta.save(bicicleta)).thenReturn(bicicleta);

        this.mockMvc.perform(post("/bicicleta").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "  \"marca\": \"tester\",\n" +
                "  \"modelo\": \"tester\",\n" +
                "  \"ano\": \"tester\",\n" +
                "  \"numero\": 0,\n" +
                "  \"status\": \"NOVA\"\n" +
                "}").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()).andExpect(content().json("{ " +
                        "marca : tester, " +
                        "modelo: tester, " +
                        "ano: tester, " +
                        "numero:0, status: NOVA"));

    }*/

    @Test
    void getBicicleta() throws Exception {
        Mockito.when(bicicletaService.listarBicicletas()).thenReturn(Collections.emptyList());
        this.mockMvc.perform(get("/bicicleta")).andDo(print()).andExpect(status().isOk());
    }
    private Bicicleta criarBicicleta() {
        bicicleta = Mockito.mock(Bicicleta.class);
        Mockito.when(bicicleta.getModelo()).thenReturn("tester");
        Mockito.when(bicicleta.getAno()).thenReturn("testr");
        Mockito.when(bicicleta.getMarca()).thenReturn("tester");
        Mockito.when(bicicleta.getStatus()).thenReturn(Status.NOVA);
        return bicicleta;
    }
}
