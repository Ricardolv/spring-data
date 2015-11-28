package br.com.richard.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.richard.springdata.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}