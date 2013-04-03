package org.tmjug.spring.demo.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tmjug.spring.demo.entities.Transaction;

@Repository
public class TransactionDAOJdbcImpl implements TransactionDAO {

	public static final String INSERT_SQL = "INSERT INTO TRANSACTION "
			+ "(AMOUNT, PAN, ACCOUNT_ID, CUSTOMER_ID, CUSTOMER_NAME, MERCHANT_ID, MERCHANT_NAME, TRANSACTION_DATE) "
			+ "VALUES (:amount, :pan, :accountId, :customerId, :customerName, :merchantId, :merchantName, :transactionDate)";

	public static final String COUNT_BY_PAN_SQL = "SELECT COUNT(*) FROM TRANSACTION WHERE PAN = :pan";

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveTransaction(Transaction txn) {
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("amount", txn.getAmount());
		namedParameters.put("pan", txn.getPan());
		namedParameters.put("accountId", txn.getAccountId());
		namedParameters.put("customerId", txn.getCustomerId());
		namedParameters.put("customerName", txn.getCustomerName());
		namedParameters.put("merchantId", txn.getMerchantId());
		namedParameters.put("merchantName", txn.getMerchantName());
		namedParameters.put("transactionDate", txn.getTransactionDate());

		jdbcTemplate.update(INSERT_SQL, namedParameters);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public long getTransactionCountByPan(String pan) {
		Map<String, String> namedParameters = Collections.singletonMap("pan", pan);

		return jdbcTemplate.queryForInt(COUNT_BY_PAN_SQL, namedParameters);
	}

}
