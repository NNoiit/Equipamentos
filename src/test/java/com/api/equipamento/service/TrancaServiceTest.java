package com.api.equipamento.service;

import com.api.equipamento.EquipamentoApplicationTests;
import com.api.equipamento.repositori.RepTranca;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("TrancaServiceTest")
class TrancaServiceTest extends EquipamentoApplicationTests {

    @Autowired
    private TrancaService service;
    @MockBean
    private RepTranca tranca;


}
