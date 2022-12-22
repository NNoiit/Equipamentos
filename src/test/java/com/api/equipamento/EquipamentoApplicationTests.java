package com.api.equipamento;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@ContextConfiguration(classes = EquipamentoApplication.class)
class EquipamentoApplicationTests {

	@Test
	void contextLoads() {
	}

}
