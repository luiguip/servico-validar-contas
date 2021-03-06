package tech.luigui.workers_management.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import tech.luigui.workers_management.pojo.InformacaoConta;
import tech.luigui.workers_management.pojo.InformacaoContaAtualizada;

@Service
public class InformacaoContaService {
	
	@Autowired
	private CsvService csvService;
	
	@Autowired
	private ReceitaService receitaService;
	
	private static Logger log = LoggerFactory.getLogger(InformacaoContaService.class);
	
	public void processar(String path) {
		var informacaoContaList = csvService.readCsv(path);
		var informacaoContaAtualizadaList = informacaoContaList.parallelStream()
				.map(this::atualizarConta)
				.toList();
		csvService.writeCsv(informacaoContaAtualizadaList);	
	}
	
	public InformacaoContaAtualizada atualizarConta(InformacaoConta informacaoConta) {
		var informacaoContaFormatada = preprocessarInformacaoConta(informacaoConta);
			try {
				var atualizada = receitaService.atualizarConta(
						informacaoContaFormatada.getAgencia(),
						informacaoContaFormatada.getConta(),
						informacaoContaFormatada.getSaldo().doubleValue(), 
						informacaoContaFormatada.getStatus());
				log.info("Conta atualizada {}", informacaoConta);
				return InformacaoContaAtualizada.gerarInformacaoContaAtualizada(informacaoConta, atualizada, false);
			} catch(InterruptedException ie) {
				log.error("InterruptionsException ReceitaService: {}", informacaoConta);
				Thread.currentThread().interrupt();
			} catch(RuntimeException re) {
				log.error("RuntimeException ReceitaService: {}", informacaoConta);
			}
		return InformacaoContaAtualizada.gerarInformacaoContaAtualizada(informacaoConta, null, true);
	}
	
	private InformacaoConta preprocessarInformacaoConta(InformacaoConta informacaoConta) {
		var informacaoContaFormatada = new InformacaoConta(
				informacaoConta.getAgencia(),
				informacaoConta.getConta(),
				informacaoConta.getSaldo(),
				informacaoConta.getStatus());
		var campo = formatarCampoConta(informacaoContaFormatada.getConta());
		informacaoContaFormatada.setConta(campo);
		return informacaoContaFormatada;
	}
	
	private String formatarCampoConta(String conta) {
		return conta.replace("-", "");
	}

}
