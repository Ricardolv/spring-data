package br.com.richard.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.richard.springdata.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long>{

}