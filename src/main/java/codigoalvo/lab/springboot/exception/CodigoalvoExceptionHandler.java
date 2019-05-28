package codigoalvo.lab.springboot.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@ControllerAdvice
public class CodigoalvoExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String msgUsuario = messageSource.getMessage("mensagem.requisicao-invalida", null, LocaleContextHolder.getLocale());
        String msgDesenvolvedor = Objects.nonNull(ex.getRootCause()) ? ex.getRootCause().getMessage() : ex.getMessage();
        List<Erro> erros = Arrays.asList(new Erro(msgUsuario, msgDesenvolvedor));
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Erro> erros = criarListaDeErros(ex.getBindingResult(), true);
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    private List<Erro> criarListaDeErros(BindingResult bindingResult) {
        return criarListaDeErros(bindingResult, false);
    }

    private List<Erro> criarListaDeErros(BindingResult bindingResult, boolean incluirNomeCampo) {
        List<Erro> erros = new ArrayList<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String msgCampo = incluirNomeCampo ? obterMsgNomeCampo(fieldError.getObjectName(), fieldError.getField()) : "";
            String msgUsuario = msgCampo + messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            erros.add(new Erro(msgUsuario, fieldError.toString(), fieldError.getObjectName()+"."+fieldError.getField()));
        }
        return erros;
    }

    private String obterMsgNomeCampo(String nomeObjeto, String nomeCampo) {
        try {
            nomeCampo = messageSource.getMessage(nomeObjeto+"."+nomeCampo, null, LocaleContextHolder.getLocale());
        } catch (Exception ex) {
        }
        nomeCampo += " : ";
        return nomeCampo;
    }

    public static class Erro {

        private String mensagemUsuario;
        private String mensagemDesenvolvedor;
        private String nomeCampo;

        public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
            this.mensagemUsuario = mensagemUsuario;
            this.mensagemDesenvolvedor = mensagemDesenvolvedor;
        }

        public Erro(String mensagemUsuario, String mensagemDesenvolvedor, String nomeCampo) {
            this.mensagemUsuario = mensagemUsuario;
            this.mensagemDesenvolvedor = mensagemDesenvolvedor;
            this.nomeCampo = nomeCampo;
        }

        public String getMensagemUsuario() {
            return mensagemUsuario;
        }

        public String getMensagemDesenvolvedor() {
            return mensagemDesenvolvedor;
        }

        public String getNomeCampo() { return nomeCampo; }

        @Override
        public String toString() {
            return "Erro{" +
                    "mensagemUsuario='" + mensagemUsuario + '\'' +
                    ", mensagemDesenvolvedor='" + mensagemDesenvolvedor + '\'' +
                    ", nomeCampo='" + nomeCampo + '\'' +
                    '}';
        }
    }
}
