package com.yeahwap.netgame.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yeahwap.netgame.domain.pojo.Order;
import com.yeahwap.spring.GeneralService;

@Service
public class OrderService extends GeneralService<Order> {
	@Transactional
	public int add(Order order) {
		int orderID = super.add(order);
		return orderID;
	}
	
	@Transactional
	public void update(Order order) {
		super.update(order);
	}
	
	public static void main(String[] args) {

	}
}
