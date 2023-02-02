package com.api.equipamento;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = EquipamentoApplication.class)
public
class EquipamentoApplicationTests {

	@Test
	void contextLoads() {
		String test = "testado";
		Assertions.assertEquals(test, "testado");
	}

}
