package com.educandoweb.course.resources;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;

// informando que esta classe é um recurso WEB que é implementado por um controlador REST // @RestController
// informando o nome do recurso // @RequestMapping( value = "caminho do recurso" )
@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	// informando uma dependencia com o services
	@Autowired
	private UserService service;
	
	// informando que é uma requisição do tipo GET // @GetMapping
	// metodo para acessar o retorno dos usuarios // ResponseEntity<Classe>
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		
		List<User> list = service.findAll();
		// retorna a resposta de sucesso do http com a list de Users
		return ResponseEntity.ok().body(list);	
	}
	
	// informando que é uma requisição do tipo GET // @GetMapping
	// e que recebe um paramentro do tipo id @GetMapping(value = "/{id}")
	// e que esse paramentro é @PathVariable Long id
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);		
	}
	
	

}
