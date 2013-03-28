package com.tpg.tmjug.aop.aspect;

import org.apache.log4j.Logger;

public aspect DesignConstraints {

	declare error: call(* Logger.*(..)) && !within(LoggingAspect) : "Logging should be performed only from aspects!";
}
