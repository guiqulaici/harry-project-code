package com.yeahwap.spring;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.TransactionDefinition;

/**
 * 
 * @author Harry
 *
 */
public class ManagedHibernateTransactionManager extends
		HibernateTransactionManager {

	private static final long serialVersionUID = 429390242163654243L;

	public ManagedHibernateTransactionManager() {
	}

	public ManagedHibernateTransactionManager(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	protected void doBegin(Object transaction, TransactionDefinition definition) {
		// should invoke before transaction is created
		if (definition.isReadOnly()) {
			OperationHolder.setOperation(OperationHolder.READONLY);
		}
		super.doBegin(transaction, definition);
	}

	@Override
	protected void doCleanupAfterCompletion(Object transaction) {
		super.doCleanupAfterCompletion(transaction);
		// should invoke after transaction is completed
		OperationHolder.remove();
	}
}
