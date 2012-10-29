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
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-hibernate.xml");
//		OrderService os = (OrderService) ctx.getBean("orderService");
//		Order order = new Order();
//		order.setDateTime(new Date());
//		order.setFromid(1);
//		order.setUid(1);
//		order.setPayMoney(50000);
//		order.setStatus(OrderType.WAITPAY);
//		System.out.println(order);
//		os.add(order);
	}
}
