package com.yeahwap.netgame.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yeahwap.netgame.domain.pojo.Operator;
import com.yeahwap.spring.GeneralService;

@Service
public class OperatorService extends GeneralService<Operator> {
	@Transactional(readOnly = true)
	public Operator get(int id) {
		System.out.println("aaa");
		Operator operator = super.get(id);
		return operator != null ? operator : null;
	}

	public static void main(String[] args) {
		ApplicationContext cxt = new ClassPathXmlApplicationContext(
				"applicationContext-hibernate.xml");
		OperatorService os = (OperatorService) cxt.getBean("operatorService");
		os.get(1);
	}
}
