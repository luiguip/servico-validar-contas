package tech.luigui.workers_management.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategyBuilder;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import tech.luigui.workers_management.exception.BusinessException;
import tech.luigui.workers_management.pojo.InformacaoConta;
import tech.luigui.workers_management.pojo.InformacaoContaAtualizada;

@Service
public class CsvService {

	public List<InformacaoConta> readCsv(String path) {
		try {
			return new CsvToBeanBuilder<InformacaoConta>(new FileReader(path)).withType(InformacaoConta.class)
					.withSeparator(';').build().parse();
		} catch (IllegalStateException | FileNotFoundException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	public void writeCsv(List<InformacaoContaAtualizada> informacaoContaAtualizadaList) {
		try {	
			var writer = new FileWriter("contas-validadas.csv");
			
			var strategy = new HeaderColumnNameMappingStrategyBuilder<InformacaoContaAtualizada>().build();
			strategy.setType(InformacaoContaAtualizada.class);
			var beanToCsv = new StatefulBeanToCsvBuilder<InformacaoContaAtualizada>(writer).withMappingStrategy(strategy)
					.build();
			beanToCsv.write(informacaoContaAtualizadaList);
			writer.close();
		} catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
			throw new BusinessException(e.getMessage());
		}
	}
}
