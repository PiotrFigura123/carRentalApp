package pl.piotrFigura.backendcarrental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class BackendcarrentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendcarrentalApplication.class, args);


	}
	@Bean
	Validator validator() {

		return new LocalValidatorFactoryBean();
	}
//TODO: sekcja 14 zdarzenia eventListener, tabelka z rezerwacjami moze byc czyms takim,
//	jesli uzytkownik zaklepie to wtedy jest ten event listener

}
