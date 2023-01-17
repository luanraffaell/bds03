package com.lrcs.controllers.handler;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.lrcs.service.exceptions.BusinessError;
import com.lrcs.service.exceptions.EntityNotFoundException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable cause = ex.getCause();
		if(cause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException)cause, headers, status, request);
		}
		if(cause instanceof PropertyBindingException) {
			return handlePropertyBindingException((PropertyBindingException) cause, headers, status, request);
		}
		
		String details = "Invalid request. Sintaxe error!";
		StandardError standardError = createStandardError(details, 
				ProblemType.MENSAGEM_INCOMPREENSIVEL,
				status.value(), 
				null);
		return handleExceptionInternal(ex, standardError,new HttpHeaders(), status, request);
	}
	
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String detail = "One or more fields are invalid. Fill in correctly and try again.";
		ValidationError err = new ValidationError();
		String path = ((ServletWebRequest) request).getRequest().getRequestURI();
		
		err.setTimeStamp(Instant.now());
		err.setMessage(detail);
		err.setPath(path);
		err.setStatus(status.value());
		err.setError(ProblemType.DADOS_INVALIDOS.getTitle());
		ex.getBindingResult().getFieldErrors().forEach(x -> err.addErrors(x.getField(), x.getDefaultMessage()));
		return handleExceptionInternal(ex, err,new HttpHeaders(), status, request);
	}



	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String requestURL = ex.getRequestURL();
		String detail = String.format("The resource '%s', which you tried to access, does not exist.", requestURL);
		StandardError createstandardError = createStandardError(detail, ProblemType.RECURSO_NAO_ENCONTRADO, status.value(), requestURL);
		return handleExceptionInternal(ex,createstandardError, headers, status, request);
	}



	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String path = ex.getPath().stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));
		String requestPath = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
		String message = String.format("Property '%s' received value '%s', which is of an invalid type. "+
		"Correct and enter a value compatible with type '%s'.", path,ex.getValue(),ex.getTargetType().getSimpleName());
		StandardError standardError = createStandardError(message, problemType, status.value(), requestPath);
		return handleExceptionInternal(ex, standardError,new HttpHeaders(), status, request);
	}
	private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String requestPath = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
		String path = ex.getPath().stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));
		String message = String.format("Property '%s' does not exist. Correct or remove this property and try again.", path);
		StandardError standardError = createStandardError(message, problemType, status.value(), requestPath);
		return handleExceptionInternal(ex, standardError,new HttpHeaders(), status, request);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> handlerEntityNotFoundException(EntityNotFoundException e, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		String requestPath = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
		StandardError err = new StandardError();
		err.setTimeStamp(Instant.now());
		err.setMessage(e.getMessage());
		err.setError(ProblemType.RECURSO_NAO_ENCONTRADO.getTitle());
		err.setStatus(status.value());
		err.setPath(requestPath);
		
		return handleExceptionInternal(e, err,new HttpHeaders(), status, request);
	}
	@ExceptionHandler(BusinessError.class)
	public ResponseEntity<?> handlerBusinessException(BusinessError ex, WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String requestPath = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
		StandardError standardError = createStandardError(ex.getMessage(), ProblemType.ERRO_NEGOCIO, status.value(), requestPath);
		return handleExceptionInternal(ex, standardError,new HttpHeaders(), status, request);
	}
	
	private StandardError createStandardError(String message,ProblemType problem,Integer status, String path) {
		return new StandardError(status,problem.getTitle(),message,path);
	}
}
