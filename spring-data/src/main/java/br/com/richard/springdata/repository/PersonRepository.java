package br.com.richard.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.richard.springdata.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}