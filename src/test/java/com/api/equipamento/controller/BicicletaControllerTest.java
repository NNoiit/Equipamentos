package com.api.equipamento.controller;

import com.api.equipamento.model.Bicicleta;
import com.api.equipamento.model.Erro;
import com.api.equipamento.service.BicicletaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    @Test
    void teste() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status()
                .isOk()).andExpect(content().string("Bem Vindos a Nossa Bike &#9773;"));
    }

    @Test
    void postBicicleta(){
        Mockito.when(bicicletaService.cadastrar(bicicleta)).thenReturn(bicicleta);

    }
}