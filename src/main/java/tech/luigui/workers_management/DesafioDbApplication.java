package tech.luigui.workers_management;

import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioDbApplication implements CommandLineRunner{
	
	@Autowired
	InformacaoContaService informacaoContaService;
	
	private static Logger LOG = LoggerFactory.getLogger(DesafioDbApplication.class);

	public static void main(String[] args) {
        try {
			SpringApplication.run(DesafioDbApplication.class, args);
		} catch(FileNotFoundException fe) {
			LOG.error("Arquivo Inexistente!");
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}

	@Override
	public void run(String... args) throws Exception {
		informacaoContaService.processar();
	}

}
