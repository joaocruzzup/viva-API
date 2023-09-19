package br.com.catalisa.viva;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Viva API", version = "1", description = "Uma API para suporte à saúde mental, permitindo registros de atividades de bem-estar, agendamento de consultas com profissionais, acompanhamento de consultas e avaliações. Uma ferramenta simples para promover o cuidado emocional e facilitar o acesso à assistência profissional."))
public class VivaApplication {
	public static void main(String[] args) {
		SpringApplication.run(VivaApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("1111"));
	}
}
