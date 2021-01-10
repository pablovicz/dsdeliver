package com.pablovicz.dsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pablovicz.dsdeliver.dto.OrderDTO;
import com.pablovicz.dsdeliver.entities.Order;
import com.pablovicz.dsdeliver.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepo;
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll(){
		
		List<Order> ordersList = orderRepo.findOrdersWithProducts();
		
		return ordersList.stream().map(o -> new OrderDTO(o)).collect(Collectors.toList());
	}
	
}
