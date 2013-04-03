package org.tmjug.spring.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ErrorHandler;

public class TransactionErrorHandler implements ErrorHandler {

	private static final Logger logger = LoggerFactory.getLogger(TransactionErrorHandler.class);

	@Override
	public void handleError(Throwable ex) {
		logger.error("Exception on saving transaction. ");
	}

}
