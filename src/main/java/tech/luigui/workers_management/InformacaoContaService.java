package tech.luigui.workers_management;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@Service
public class InformacaoContaService {
	
	@Autowired
	CsvService csvService;
	
	@Autowired
	ReceitaService receitaService;
	
	private static Logger log = LoggerFactory.getLogger(InformacaoContaService.class);
	
	public void proccess(String path) throws IllegalStateException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		var informacaoContaList = csvService.readCsv(path);
		var informacaoContaAtualizadaList = informacaoContaList.stream()
				.map(this::atualizarConta)
				.toList();
		csvService.writeCsv(informacaoContaAtualizadaList);	
	}
	
	private InformacaoContaAtualizada atualizarConta(InformacaoConta informacaoConta) {
		var informacaoContaFormatada = preprocessInformacaoConta(informacaoConta);
		while(true) {
			try {
				var atualizada = receitaService.atualizarConta(
						informacaoContaFormatada.getAgencia(),
						informacaoContaFormatada.getConta(),
						informacaoContaFormatada.getSaldo().doubleValue(), 
						informacaoContaFormatada.getStatus());
				return InformacaoContaAtualizada.gerarInformacaoContaAtualizada(informacaoConta, atualizada);
			} catch (RuntimeException | InterruptedException e) {
				log.error("Erro ReceitaService: {}", informacaoContaFormatada);
			}
		}
	}
	
	private InformacaoConta preprocessInformacaoConta(InformacaoConta informacaoConta) {
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
