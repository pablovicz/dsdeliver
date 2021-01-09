package com.pablovicz.dsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pablovicz.dsdeliver.dto.ProductDTO;
import com.pablovicz.dsdeliver.entities.Product;
import com.pablovicz.dsdeliver.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	
	@Transactional(readOnly = true)
	public List<ProductDTO> findAll(){
		
		List<Product> productsList = productRepo.findAllByOrderByNameAsc();
		
		return productsList.stream().map(p -> new ProductDTO(p)).collect(Collectors.toList());
	}
	
	
}
