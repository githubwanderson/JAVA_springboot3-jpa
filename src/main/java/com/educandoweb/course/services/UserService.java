package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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
		try {
			repository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
		
	}
	
	/**
	 * aula316
	 * update
	 */
	public User update(Long id, User obj) {
		try {
			User entity = repository.getReferenceById(id); // Não busca imediatamente o obj, apenas prepara
			updateData(entity, obj); // Line54 // Atualizar os dados do entity com os dados do obj
			return repository.save(entity); // salvar
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
	
	
	
}
