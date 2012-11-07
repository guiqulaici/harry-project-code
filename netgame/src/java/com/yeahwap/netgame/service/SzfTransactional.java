package com.yeahwap.netgame.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yeahwap.netgame.domain.OrderStatus;
import com.yeahwap.netgame.domain.pojo.Order;
import com.yeahwap.netgame.domain.pojo.SzfOrder;

/**
 * 
 * @author Harry Ye
 *
 */
@Service
public class SzfTransactional {
	@Autowired
	@Qualifier("szfOrderService")
	private SzfOrderService szfOrderService;
	
	@Autowired
	@Qualifier("orderService")
	private OrderService orderService;
	
	private final static Logger logger = Logger.getLogger("revenueDataLog");
	
	// 保证Oder和SzfOrder状态一致
	@Transactional
	public void updateAccountStatus(SzfOrder szfOrder, Order order, byte type) {
		switch (type) {
		case OrderStatus.PASSPAY:
			if (szfOrder.getStatus() != OrderStatus.PASSPAY || order.getStatus() != OrderStatus.PASSPAY) {
				szfOrder.setStatus(OrderStatus.PASSPAY);
				szfOrderService.update(szfOrder);
				order.setStatus(OrderStatus.PASSPAY);
				orderService.update(order);
				logger.info("accountId=" + order.getId() + ";orderId=" + szfOrder.getOrderId()  + " is pass pay ok!!");
			}
			break;
		
		case OrderStatus.PAYERROR:
			if (szfOrder.getStatus() != OrderStatus.PASSPAY && order.getStatus() == OrderStatus.PASSPAY && (order.getStatus() == OrderStatus.WAITPAY || szfOrder.getStatus() == OrderStatus.WAITPAY)) {
				szfOrder.setStatus(OrderStatus.PAYERROR);
				szfOrderService.update(szfOrder);
				order.setStatus(OrderStatus.PAYERROR);
				orderService.update(order);
				logger.info("accountId=" + order.getId() + ";orderId=" + szfOrder.getOrderId()  + " is pay error!!");
			} 
			break;
		case OrderStatus.VALIDERROR:
			if (szfOrder.getStatus() != OrderStatus.PASSPAY && order.getStatus() == OrderStatus.PASSPAY && (order.getStatus() == OrderStatus.WAITPAY || szfOrder.getStatus() == OrderStatus.WAITPAY)) {
				szfOrder.setStatus(OrderStatus.VALIDERROR);
				szfOrderService.update(szfOrder);
				order.setStatus(OrderStatus.VALIDERROR);
				orderService.update(order);
				logger.info("accountId=" + order.getId() + ";orderId=" + szfOrder.getOrderId()  + " is valid error!!");
			} 
			break;
		default:
			break;
		}
	}
}
