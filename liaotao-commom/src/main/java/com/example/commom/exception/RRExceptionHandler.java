package com.example.commom.exception;

import com.example.commom.model.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.lang.reflect.Field;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 异常处理器
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年10月27日 下午10:16:19
 */
@RestControllerAdvice
public class RRExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(RRException.class)
	public R handleRRException(RRException e){
		return R.error(e.getCode(),e.getMessage());
	}

	/**
	 * sql异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public R handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e){
		logger.error(e.getMessage(), e);
		return R.error(e.getMessage());
	}

	/**
	 * 校验异常
	 */
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public R handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		String errorMsg = e.getBindingResult().getFieldErrors()
				.stream()
				.map(FieldError::getDefaultMessage)
				.collect(Collectors.joining(","));
		return R.error(errorMsg);
	}

	/**
	 * 校验异常
	 */
	@ExceptionHandler(value = BindException.class)
	public R handleBindException(BindException e) {
		String errorMsg = e.getBindingResult().getFieldErrors()
				.stream()
				.map(FieldError::getDefaultMessage)
				.collect(Collectors.joining(","));
		return R.error(errorMsg);
	}

	/**
	 * 校验异常
	 */
	@ExceptionHandler(value = ConstraintViolationException.class)
	public R handleConstraintViolationException(ConstraintViolationException ex) {
		String errorMsg = ex.getConstraintViolations()
				.stream()
				.map(ConstraintViolation::getMessage)
				.collect(Collectors.joining(","));
		return R.error(errorMsg);
	}




	@ExceptionHandler(Exception.class)
	public R handleException(Exception e){
		logger.error(e.getMessage(), e);
		return R.error(e.getMessage());
	}
}
