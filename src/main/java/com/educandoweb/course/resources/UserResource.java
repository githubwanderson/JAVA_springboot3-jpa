package com.educandoweb.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;

/**
 * 
 * @author WANDERSON
 * informando que esta classe é um recurso WEB que é implementado por um controlador REST // @RestController
 * informando o nome do recurso // end Point // @RequestMapping( value = "caminho do recurso" )
 */
@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	/**
	 * informando uma dependencia com o services
	 */
	@Autowired
	private UserService service;
	
	/**
	 * informando que é uma requisição do tipo GET // @GetMapping
	 * metodo responsavel por listar os Usuarios do banco de dados
	 */
	@GetMapping
	public ResponseEntity<List<User>> findAll(){		
		List<User> list = service.findAll();		
		return ResponseEntity.ok().body(list);	// retorna a resposta de sucesso do http com a list de Users
	}
	
	/**
	 * informando que é uma requisição do tipo GET // @GetMapping
	 * e que recebe um paramentro do tipo id @GetMapping(value = "/{id}")
	 * Para ser reconhecida como uma variavel da URL // @PathVariable
	 * GET
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);		
	}
	
	/**
	 * metodo responsavel por inserir no DB
	 * POST
	 */
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj){ // Informando que o obj chegara na forma JSON @RequestBody
		obj = service.insert(obj);
		/**
		 * Criando um obj do tipo URI
		 * necessario para retornar 201 no http 
		 * ResponseEntity.created() - ele espera um obj uri contendo um cabeçalho "location" com endereço do novo recurso
		 */
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}") // informando caminho inserido
				.buildAndExpand(obj.getId()) // informando o id inserido
				.toUri(); // covertendo em obj uri
		return ResponseEntity.created(uri).body(obj); // created: Infomando que inserimos um novo recurso para resposta do http ser  igual a 201
	}
	
	/**
	 * metodo responsavel por deletar no DB
	 * DELETE
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build(); // Retorna uma resposta vazia cod http 204
	}
	
	/**
	 * metodo responsavel por atualizar
	 * PUT
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	
	
}
