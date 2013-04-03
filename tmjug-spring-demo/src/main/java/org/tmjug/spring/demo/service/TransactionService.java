package org.tmjug.spring.demo.service;

public interface TransactionService {

	public void saveTransaction();

	long getTransactionCountByPan(String pan);
}
