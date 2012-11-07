package com.yeahwap.netgame.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yeahwap.netgame.domain.NgFromStatus;
import com.yeahwap.netgame.domain.OrderStatus;
import com.yeahwap.netgame.domain.UserStatus;
import com.yeahwap.netgame.domain.pojo.Balance;
import com.yeahwap.netgame.domain.pojo.NgFrom;
import com.yeahwap.netgame.domain.pojo.Order;
import com.yeahwap.netgame.domain.pojo.SzfOrder;
import com.yeahwap.netgame.domain.pojo.User;
import com.yeahwap.netgame.util.StringUtil;

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
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("ngFromService")
	private NgFromService ngFromService;
	
	@Autowired
	@Qualifier("balanceService")
	private BalanceService balanceService;
	
	private final static Logger logger = Logger.getLogger("revenueDataLog");
	
	// 保证Oder和SzfOrder状态一致
	@Transactional
	public void updateAccountStatus(SzfOrder szfOrder, Order order, byte type, String privateField) {
		switch (type) {
		case OrderStatus.PASSPAY:
			if (szfOrder.getStatus() != OrderStatus.PASSPAY || order.getStatus() != OrderStatus.PASSPAY) {
				szfOrder.setStatus(OrderStatus.PASSPAY);
				szfOrderService.update(szfOrder);
				order.setStatus(OrderStatus.PASSPAY);
				orderService.update(order);
				logger.info("accountId=" + order.getId() + ";orderId=" + szfOrder.getOrderId()  + " is pass pay ok!!");
				
				// 给用户添加金额, 验证privateFiled
				validFiled(privateField, order);
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


	synchronized private boolean validFiled(String privateField, Order order) {
		// uid + "_" + fromid + "_" + orderId;
		if (privateField == null || ("").equals(privateField)) {
			return false;
		}
		
		String[] privateArray = privateField.split("_");
		
		if (privateArray.length != 3) {
			return false;
		}
		
		int uid = StringUtil.getInt(privateArray[0], 0);
		int fromid = StringUtil.getInt(privateArray[1], 0);
		String orderId = privateArray[2];
		
		User user = userService.get(uid);
		if(user == null || user.getStatus() != UserStatus.NORMAL) {
			logger.info("Balance add, user is null or user status is  not normal !! ");
			return false;
		} 	
		
		NgFrom ngFrom = ngFromService.get(fromid);
		if (ngFrom == null || ngFrom.getStatus() != NgFromStatus.NORMAL) {
			logger.info("Balance add, ngFrom is null or ngFrom status is  not normal !! ");
			return false;
		}
		
		SzfOrder szfOrder =  szfOrderService.get(orderId);
		if (szfOrder == null || szfOrder.getStatus() != OrderStatus.PASSPAY) {
			logger.info("Balance add, szfOrder is null or szfOrder status is  not passpay !! ");
			return false;
		}
		
		synchronized(balanceService) {
			Balance b = new Balance();
			b.setUid(uid);
			b.setBalance(order.getPayMoney());
			balanceService.addBalance(b);
		}
		
		return true;
	}
	
	
}
