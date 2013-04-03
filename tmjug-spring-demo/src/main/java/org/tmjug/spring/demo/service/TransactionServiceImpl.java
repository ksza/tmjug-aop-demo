package org.tmjug.spring.demo.service;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tmjug.spring.demo.dao.TransactionDAO;
import org.tmjug.spring.demo.entities.Transaction;

public class TransactionServiceImpl implements TransactionService {

	private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

	@Autowired
	private TransactionDAO transactionDao;
	@Autowired
	private JmsTemplate jmsTemplate;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveTransaction() {
		logger.info("Reading transactionfrom queue");
		ObjectMessage receivedMessage = (ObjectMessage) jmsTemplate.receive();
		Transaction txn;
		try {
			txn = (Transaction) receivedMessage.getObject();
		} catch (JMSException e) {
			logger.info("Error while reading transaction from queue");
			return;
		}

		logger.info("Saving a new transaction for pan {}...", txn.getPan());
		transactionDao.saveTransaction(txn);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public long getTransactionCountByPan(String pan) {
		logger.info("Counting transactions for pan {}...", pan);
		return transactionDao.getTransactionCountByPan(pan);
	}

}
