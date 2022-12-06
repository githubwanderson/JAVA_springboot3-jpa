package com.educandoweb.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.UserRepository;

// informando ao spring que esta é uma classe de configuração // @Configuration
// informando ao spring que tambem é uma classe de teste // @Profile( Nome do profile no arquivo application.properties )
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	// injeção de dependencia com spring  // Declara a dependencia e add @Autowired
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;	

	// excutar quando o programa for iniciado // implements CommandLineRunner // metodo da interface
	@Override
	public void run(String... args) throws Exception {

		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1);
		
		// Salvando os obj no banco de dados
		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1,o2,o3));
	}
	
	

}
