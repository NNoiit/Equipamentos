package com.api.equipamento;

import com.api.equipamento.controller.BicicletaController;
import com.api.equipamento.controller.TotemController;
import com.api.equipamento.controller.TrancaController;
import com.api.equipamento.service.BicicletaService;
import com.api.equipamento.service.TotemService;
import com.api.equipamento.service.TrancaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = EquipamentoApplication.class)
class EquipamentoApplicationTests {

    @Autowired
    private BicicletaController bicicletaController;
    @Autowired
    private BicicletaService bicicletaService;

    @Autowired
    private TrancaController trancaController;
    @Autowired
    private TrancaService trancaService;

    @Autowired
    private TotemController totemController;
    @Autowired
    private TotemService totemService;


    @Test
    void contextLoadsBicicleta() throws Exception {
        assertThat(bicicletaController).isNotNull();
        assertThat(bicicletaService).isNotNull();
    }

    @Test
    void contextLoadsTranca() throws Exception {
        assertThat(trancaController).isNotNull();
        assertThat(trancaService).isNotNull();
    }

    @Test
    void contextLoadsTotem() throws Exception {
        assertThat(totemController).isNotNull();
        assertThat(totemService).isNotNull();
    }

}
