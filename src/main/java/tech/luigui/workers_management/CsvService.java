package tech.luigui.workers_management;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.comparators.FixedOrderComparator;
import org.springframework.stereotype.Service;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategyBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.bean.comparator.LiteralComparator;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import tech.luigui.workers_management.pojo.InformacaoConta;
import tech.luigui.workers_management.pojo.InformacaoContaAtualizada;

@Service
public class CsvService {

	public List<InformacaoConta> readCsv(String path) throws IllegalStateException, FileNotFoundException {
		return new CsvToBeanBuilder<InformacaoConta>(new FileReader(path)).withType(InformacaoConta.class)
				.withSeparator(';').build().parse();
	}

	public void writeCsv(List<InformacaoContaAtualizada> informacaoContaAtualizadaList)
			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		var writer = new FileWriter("contas-validadas.csv");
		var strategy = new HeaderColumnNameMappingStrategyBuilder<InformacaoContaAtualizada>().build();
		strategy.setType(InformacaoContaAtualizada.class);
		var beanToCsv = new StatefulBeanToCsvBuilder<InformacaoContaAtualizada>(writer).withMappingStrategy(strategy)
				.build();
		beanToCsv.write(informacaoContaAtualizadaList);
		writer.close();
	}
}
