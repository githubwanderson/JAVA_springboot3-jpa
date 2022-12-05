package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.User;

// Os repositórios são interfaces porque o JpaRepository tambem é uma interface
// JpaRepository<Entidade, tipoId>
public interface UserRepository extends JpaRepository<User, Long>{
	// Não é necessario implementar essa interface 
	// O springDataJpa ja contem uma uma imlementação padrão	
}