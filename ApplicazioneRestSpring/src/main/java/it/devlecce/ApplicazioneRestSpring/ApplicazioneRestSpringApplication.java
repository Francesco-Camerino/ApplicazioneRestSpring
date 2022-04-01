package it.devlecce.ApplicazioneRestSpring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class ApplicazioneRestSpringApplication {
	/* creazione attributo log */
	private static final Logger logger = LoggerFactory.getLogger(ApplicazioneRestSpringApplication.class);

	/* utilizzo di una chiave delle proprietà di Spring */
	@Value("${it.devlecce.ApplicazioneRestSpring.saluti}")
	public String  salutoIniziale;

	public static void main(String[] args) {
		SpringApplication.run(ApplicazioneRestSpringApplication.class, args);
	}
	/* il nostro primo bean */
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			logger.debug(salutoIniziale);
			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beans = ctx.getBeanDefinitionNames();
			Arrays.sort(beans);
			/*for (String bean : beans) {
				logger.debug(bean);
			}
			for(int i = 0; i < beans.length; i++) {
				logger.warn(beans[i]);
			}*/

			for(int i = 0; i < 10; i++) {
				logger.warn(beans[i]);
			}

		};
	}

}
