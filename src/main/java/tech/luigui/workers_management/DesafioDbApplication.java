package tech.luigui.workers_management;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tech.luigui.workers_management.exception.BusinessException;
import tech.luigui.workers_management.exception.RequiredArgumentException;
import tech.luigui.workers_management.service.InformacaoContaService;

@SpringBootApplication
public class DesafioDbApplication implements CommandLineRunner{
	
	@Autowired
	InformacaoContaService informacaoContaService;
	
	private static Logger log = LoggerFactory.getLogger(DesafioDbApplication.class);

	public static void main(String[] args) {
		try{
			SpringApplication.run(DesafioDbApplication.class, args);
		} catch(RequiredArgumentException | BusinessException e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public void run(String... args) throws Exception {
		var argument = recuperarArgumento(args);
		informacaoContaService.processar(argument);
	}
	
	private String recuperarArgumento(String... args) throws RequiredArgumentException{
		if(args.length == 0) {
			throw new RequiredArgumentException("Argumento com o caminho do csv é necessário!");
		}
		return args[0];
	}

}
