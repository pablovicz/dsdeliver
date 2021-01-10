package com.pablovicz.dsdeliver.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pablovicz.dsdeliver.dto.OrderDTO;
import com.pablovicz.dsdeliver.dto.ProductDTO;
import com.pablovicz.dsdeliver.entities.Order;
import com.pablovicz.dsdeliver.entities.OrderStatus;
import com.pablovicz.dsdeliver.entities.Product;
import com.pablovicz.dsdeliver.repositories.OrderRepository;
import com.pablovicz.dsdeliver.repositories.ProductRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private ProductRepository productRepo;

	@Transactional(readOnly = true)
	public List<OrderDTO> findAll() {

		List<Order> ordersList = orderRepo.findOrdersWithProducts();

		return ordersList.stream().map(o -> new OrderDTO(o)).collect(Collectors.toList());
	}

	@Transactional
	public OrderDTO insert(OrderDTO dto) {

		Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(), Instant.now(),
				OrderStatus.PENDING);

		for (ProductDTO p : dto.getProducts()) {
			Product product = productRepo.getOne(p.getId());
			order.getProducts().add(product);
		}

		order = orderRepo.save(order);

		return new OrderDTO(order);
	}
	
	
	@Transactional
	public OrderDTO setDelivered(Long id) {
		
		Order order = orderRepo.getOne(id);
		order.setStatus(OrderStatus.DELIVERED);
		order = orderRepo.save(order);
		
		return new OrderDTO(order);
	}

}
