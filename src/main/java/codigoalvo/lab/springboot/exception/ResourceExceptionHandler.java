package codigoalvo.lab.springboot.exception;

import codigoalvo.lab.springboot.entities.dto.ErrorResponse;
import codigoalvo.lab.springboot.util.ErrorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler({EmptyResultDataAccessException.class, ResourceNotFoundException.class})
	//@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handleEmptyResultDataAccessException(Exception ex, WebRequest request) {
		String msgDesenvolvedor = ErrorUtil.getErrorMessage(ex);
		String msgUsuario = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
		log.warn(msgUsuario + " - " + msgDesenvolvedor);
		List<ErrorResponse> erros = ErrorUtil.singleErrorAsList(msgUsuario, msgDesenvolvedor);
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String msgUsuario = messageSource.getMessage("mensagem.requisicao-invalida", null, LocaleContextHolder.getLocale());
		String msgDesenvolvedor = Objects.nonNull(ex.getRootCause()) ? ex.getRootCause().getMessage() : ex.getMessage();
		List<ErrorResponse> erros = ErrorUtil.singleErrorAsList(msgUsuario, msgDesenvolvedor);
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ErrorResponse> erros = criarListaDeErros(ex.getBindingResult(), true);
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}


	private List<ErrorResponse> criarListaDeErros(BindingResult bindingResult, boolean incluirNomeCampo) {
		List<ErrorResponse> erros = new ArrayList<>();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String msgCampo = incluirNomeCampo ? obterMsgNomeCampo(fieldError.getObjectName(), fieldError.getField()) : "";
			String msgUsuario = msgCampo + messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			erros.add(new ErrorResponse(msgUsuario, fieldError.toString(), fieldError.getObjectName() + "." + fieldError.getField()));
		}
		return erros;
	}

	private String obterMsgNomeCampo(String nomeObjeto, String nomeCampo) {
		try {
			nomeCampo = messageSource.getMessage(nomeObjeto + "." + nomeCampo, null, LocaleContextHolder.getLocale());
		} catch (Exception ex) {
		}
		nomeCampo += " : ";
		return nomeCampo;
	}

}
