package org.tmjug.spring.demo.dao;

import org.tmjug.spring.demo.entities.Transaction;

public interface TransactionDAO {

	public void saveTransaction(Transaction txn);

	long getTransactionCountByPan(String pan);
}
