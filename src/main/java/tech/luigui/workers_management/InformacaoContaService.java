package tech.luigui.workers_management;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InformacaoContaService {
	
	@Autowired
	CsvService csvService;
	
	public void proccess(String path) throws IllegalStateException, FileNotFoundException {
		List<InformacaoConta> informacaoContaList= csvService.readCsv(path);
		informacaoContaList.forEach(System.out::println);
	}

}
