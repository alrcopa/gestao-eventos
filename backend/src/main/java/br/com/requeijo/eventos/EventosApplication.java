package br.com.requeijo.eventos;

import br.com.requeijo.eventos.model.Evento;
import br.com.requeijo.eventos.repository.EventoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class EventosApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventosApplication.class, args);
	}


	@Bean
	@Profile("dev")
	CommandLineRunner initDatabase(EventoRepository eventoRepository) {
		return args -> extracted(eventoRepository);
	}

	private void extracted(EventoRepository eventoRepository) {
		eventoRepository.deleteAll();
		for (int i = 1; i < 11; i++) {
			Evento c = new Evento();
			c.setDescription("Description " + i);
			c.setTitle("Event " + i);
			c.setLocation("Location " + i);
			c.setEventDate(LocalDateTime.now().plusDays(i * 2L));

			eventoRepository.save(c);
		}
	}


}
