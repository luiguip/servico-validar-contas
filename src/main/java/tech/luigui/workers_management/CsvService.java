package tech.luigui.workers_management;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.springframework.stereotype.Service;

import com.opencsv.bean.CsvToBeanBuilder;

@Service
public class CsvService {

	List<InformacaoConta> readCsv(String path) throws IllegalStateException, FileNotFoundException {
		return new CsvToBeanBuilder<InformacaoConta>(new FileReader(path))
			       .withType(InformacaoConta.class).build().parse();
	}
}
