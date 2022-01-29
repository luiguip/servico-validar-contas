package tech.luigui.workers_management;

import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;
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
		try{
			SpringApplication.run(DesafioDbApplication.class, args);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			var argument = recuperarArgumento(args);
			informacaoContaService.proccess(argument);
		} catch(RequiredArgumentException rae) {
			LOG.error("Argumento com o caminho do csv necessário!");
		} catch(FileNotFoundException fnfe) {
			LOG.error("Arquivo inexistente!");
		} catch(IllegalStateException ise) {
			LOG.error("ocorreu uma exceção de estado ilegal!");
		}
				
	}
	
	private String recuperarArgumento(String... args) throws RequiredArgumentException{
		if(args.length == 0) {
			throw new RequiredArgumentException();
		}
		return args[0];
	}

}
