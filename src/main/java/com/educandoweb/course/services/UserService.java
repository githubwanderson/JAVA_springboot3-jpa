package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

// Informando que essa classe é um componente do spring // @Component @Service @Repository
@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;

	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id) {
		// Optional JAVA8+
		Optional<User> obj = repository.findById(id);
		// Pegar o objeto dentro do Optional obj
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));
	}
	
	/**
	 * aula314
	 * insert
	 */
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	/**
	 * aula315
	 * delete
	 */
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	/**
	 * aula316
	 * update
	 */
	public User update(Long id, User obj) {
		User entity = repository.getReferenceById(id); // Não busca imediatamente o obj, apenas prepara
		updateData(entity, obj); // Line54 // Atualizar os dados do entity com os dados do obj
		return repository.save(entity); // salvar
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
	
	
	
}
