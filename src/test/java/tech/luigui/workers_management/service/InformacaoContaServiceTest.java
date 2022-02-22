package tech.luigui.workers_management.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import tech.luigui.workers_management.pojo.InformacaoConta;
import tech.luigui.workers_management.pojo.InformacaoContaAtualizada;

@SpringBootTest
class InformacaoContaServiceTest {

	@MockBean
	private ReceitaService receitaService;

	@InjectMocks
	@Autowired
	private InformacaoContaService informacaoContaService;
	
	private InformacaoConta informacaoConta;
	private InformacaoConta informacaoContaFormatada;
	private InformacaoContaAtualizada informacaoContaAtualizada;
	private InformacaoContaAtualizada informacaoContaAtualizadaComErro;
	
	@BeforeEach
	void beforeEach() {
		informacaoConta = new InformacaoConta("0001", "48576-1", new BigDecimal("53.50"), "P");
		informacaoContaFormatada = new InformacaoConta("0001", "485761", new BigDecimal("53.50"), "P");
		informacaoContaAtualizada = InformacaoContaAtualizada.gerarInformacaoContaAtualizada(informacaoConta, true, false);
		informacaoContaAtualizadaComErro = InformacaoContaAtualizada.gerarInformacaoContaAtualizada(informacaoConta, null, true);
	}

	@Test
	void atualizarConta_shouldReturnErroFalse_InterruptedException() throws RuntimeException, InterruptedException {
		doThrow(InterruptedException.class)
			.when(receitaService)
			.atualizarConta(
					informacaoContaFormatada.getAgencia(),
					informacaoContaFormatada.getConta(),
					informacaoContaFormatada.getSaldo().doubleValue(),
					informacaoContaFormatada.getStatus()
					);
		var resultado = informacaoContaService.atualizarConta(informacaoConta);
		assertEquals(informacaoContaAtualizadaComErro, resultado);
	}
	
	@Test
	void atualizarConta_shouldReturnErroFalse_RuntimeException() throws RuntimeException, InterruptedException {
		doThrow(RuntimeException.class)
			.when(receitaService)
			.atualizarConta(
					informacaoContaFormatada.getAgencia(),
					informacaoContaFormatada.getConta(),
					informacaoContaFormatada.getSaldo().doubleValue(),
					informacaoContaFormatada.getStatus());
		var resultado = informacaoContaService.atualizarConta(informacaoConta);
		assertEquals(informacaoContaAtualizadaComErro, resultado);
	}
	
	@Test
	void atualizarConta_shouldReturnErroTrue() throws RuntimeException, InterruptedException {
		doReturn(true)
			.when(receitaService)
			.atualizarConta(
					informacaoContaFormatada.getAgencia(),
					informacaoContaFormatada.getConta(),
					informacaoContaFormatada.getSaldo().doubleValue(),
					informacaoContaFormatada.getStatus());
		var resultado = informacaoContaService.atualizarConta(informacaoConta);
		assertEquals(informacaoContaAtualizada, resultado);
	}
}
