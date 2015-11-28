package br.com.richard.springdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.richard.springdata.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
	
	
	
	
	//busca idade igual ao parametro fornecido
	List<Person> findByAge(Integer age);
	
	//busca idade diferente ao parametro fornecido
	List<Person> findByAgeNot(Integer age);
	
	//busca FirstName igual ao parametro fornecido
	List<Person> findByFirstNameLike(String FirstName);
	
	//busca FirstName diferente ao parametro fornecido
	List<Person> findByFirstNameNotLike(String FirstName);
}