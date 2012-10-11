package com.yeahwap.netgame.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yeahwap.netgame.domain.pojo.Operator;
import com.yeahwap.spring.GeneralService;

@Service
public class OperatorService extends GeneralService<Operator> {
	@Transactional(readOnly = true)
	public Operator get(int id) {
		Operator operator = super.get(id);
		return operator != null ? operator : null;
	}
}
