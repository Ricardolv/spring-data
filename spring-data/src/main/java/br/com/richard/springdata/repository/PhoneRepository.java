package br.com.richard.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.richard.springdata.entity.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long>{

}