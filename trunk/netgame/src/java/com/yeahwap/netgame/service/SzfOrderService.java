package com.yeahwap.netgame.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yeahwap.netgame.domain.pojo.SzfOrder;
import com.yeahwap.spring.GeneralService;

@Service
public class SzfOrderService extends GeneralService<SzfOrder> {
	@Transactional
	public int add(SzfOrder order) {
		int orderID = super.add(order);
		return orderID;
	}

	@Transactional
	public void update(SzfOrder order) {
		super.update(order);
	}

	@Transactional
	public SzfOrder get(String orderId) {
		String hql = "from SzfOrder where orderId = ?";
		List<SzfOrder> list = hibernateTemplate.find(hql,
				new Object[] { orderId });
		return list.size() > 0 ? list.get(0) : null;
	}
}
