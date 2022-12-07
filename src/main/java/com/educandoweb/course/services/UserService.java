package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;

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
		return obj.get();
	}
	
	/**
	 * aula314
	 * @param obj
	 * @return
	 */
	public User insert(User obj) {
		return repository.save(obj);
	}
	
}
