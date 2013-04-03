package org.tmjug.spring.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.tmjug.spring.demo.entities.Transaction;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/transaction-app-ctx.xml" })
public class TransactionServiceTest {

	@Autowired
	private TransactionService transactionService;
	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private TransactionTemplate txnTemplate;

	@Test
	public void testSaveTransaction_withSuccessfulSave() throws Exception {
		final Transaction txn = createTestTransactionForPan("12345");
		sendTransactionToQueue(txn);

		transactionService.saveTransaction();

		// verifying that message was saved to database
		long count = transactionService.getTransactionCountByPan("12345");
		assertEquals(1, count);
	}

	@Test
	public void testSaveTransaction_withExceptionOnSave() throws Exception {
		final Transaction txn = createTestTransactionForPan("12340");
		txn.setAmount(null); // Amount is mandatory - insert will fail
		sendTransactionToQueue(txn);

		try {
			transactionService.saveTransaction();
		} catch (Exception e) {
			assertTrue(e instanceof DataIntegrityViolationException);
		}

		// verifying that message was NOT saved to database
		long count = transactionService.getTransactionCountByPan("12340");
		assertEquals(0, count);
		// verifying message still on queue
		Transaction receivedTxn = readTransactionFromQueue();
		assertNotNull(receivedTxn);
	}

	private void sendTransactionToQueue(final Transaction txn) {
		// sending message to queue
		txnTemplate.execute(new TransactionCallback<Void>() {
			public Void doInTransaction(TransactionStatus txStatus) {
				jmsTemplate.send(new MessageCreator() {
					public Message createMessage(Session session) throws JMSException {
						return session.createObjectMessage(txn);
					}
				});
				return null;
			}
		});
	}

	private Transaction readTransactionFromQueue() {
		// sending message to queue
		jmsTemplate.setReceiveTimeout(-1);
		return txnTemplate.execute(new TransactionCallback<Transaction>() {
			public Transaction doInTransaction(TransactionStatus txStatus) {
				ObjectMessage message = (ObjectMessage) jmsTemplate.receive();
				try {
					return (Transaction) message.getObject();
				} catch (JMSException e) {
					e.printStackTrace();
					return null;
				}
			}
		});
	}

	private Transaction createTestTransactionForPan(String pan) {
		Transaction txn = new Transaction();
		txn.setAmount(1000L);
		txn.setPan(pan);
		txn.setAccountId(12345L);
		txn.setCustomerId(123L);
		txn.setCustomerName("aCustomerName");
		txn.setMerchantId(1234L);
		txn.setMerchantName("aMerchantName");
		txn.setTransactionDate(new Date());
		return txn;
	}

}
