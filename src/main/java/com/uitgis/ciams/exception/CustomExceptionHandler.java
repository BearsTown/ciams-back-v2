package com.uitgis.ciams.exception;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;


/**
 * 공통 에러 핸들링 클래스.
 */
@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {


	/**
	 * 키 중복 오류
	 */
	@ExceptionHandler(DuplicateKeyException.class)
	public ResponseEntity<?> duplicateKeyException(DuplicateKeyException e){
		log.error("DuplicateKeyException :  message {}", e.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicated PK or Unique Key.");
	}

	/**
	 * 사용자 정의 오류
	 */
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<?> customException(CustomException e){
		log.error("CustomException :  message {}", e.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	}
}
