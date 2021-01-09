package com.pablovicz.dsdeliver.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pablovicz.dsdeliver.entities.Product;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long>{
	
	
	public List<Product> findAllByOrderByNameAsc();

}
