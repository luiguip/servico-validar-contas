package tech.luigui.workers_management.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReceitaServiceTest {
	
	@Autowired
	private ReceitaService receitaService;
	
	@Test
	void atualizarContaTest_shouldReturnFalse_AgenciaNull() throws RuntimeException, InterruptedException {
		var resultado = receitaService.atualizarConta(null, "837492", 101.04, "P");
		assertFalse(resultado);
	}
	
	@Test
	void atualizarContaTest_shouldReturnFalse_ContaNull() throws RuntimeException, InterruptedException {
		var resultado = receitaService.atualizarConta("0001", null, 33.55, "A");
		assertFalse(resultado);
	}
	
	@Test
	void atualizarContaTest_shouldReturnFalse_statusNull() throws RuntimeException, InterruptedException {
		var resultado = receitaService.atualizarConta("0001", "837492", 0.0, null);
		assertFalse(resultado);
	}
	
	@Test
	void atualizarContaTest_shouldReturnFalse_statusInvalido() throws RuntimeException, InterruptedException {
		var resultado = receitaService.atualizarConta("0001", "837492", 0.50, "Z");
		assertFalse(resultado);
	}
	
	@Test
	void atualizarContaTest_shouldReturnTrueOrThrowException() throws InterruptedException {
		try {
			var resultado = receitaService.atualizarConta("0001", "837492", 12.15, "I");
			assertTrue(resultado);
		} catch (RuntimeException e) {
			assertTrue(e instanceof RuntimeException);
		}	
	}
}
