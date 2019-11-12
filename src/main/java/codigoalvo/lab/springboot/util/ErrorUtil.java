package codigoalvo.lab.springboot.util;

import codigoalvo.lab.springboot.entities.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
public class ErrorUtil {

	private static final String TEST_MSG_PREFIX = "__TEST__ ";

	public static final String LINE_BREAK = "\r\n";

	public static String getErrorMessage(Exception ex) {
		if (ex == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		if (ex.getMessage() != null) {
			sb.append(ex.getMessage());
		}
		if (ex.getCause() != null) {
			System.err.println("Exception Cause: " + Objects.toString(ex.getCause()));
			if (!sb.toString().trim().isEmpty()) {
				sb.append(" ; Cause: ");
			}
			sb.append(ex.getCause());
		}
		return sb.toString();
	}

	public static List<ErrorResponse> singleErrorAsList(String msgUsuario, String msgDesenvolvedor) {
		return Arrays.asList(new ErrorResponse(msgUsuario, msgDesenvolvedor));
	}

	public static String getTestMessage(Class classe, Object esperado, Object resultado, boolean log) {
		StringBuilder message = new StringBuilder(TEST_MSG_PREFIX);
		message.append(classe.getSimpleName()).append(".");
		message.append(ErrorUtil.getCurrentClassAndMethod());
		message.append("() - Esperado: (%s), Resultado: (%s)");
		String strMessage = String.format(message.toString(), Objects.toString(esperado), Objects.toString(resultado));
		if (log) {
			logObject(strMessage);
		}
		return strMessage;
	}

	public static String getCurrentClassAndMethod() {
		return Thread.currentThread().getStackTrace()[3].getMethodName();
	}

	public static void logObject(Object logObject) {
		if (log != null) {
			log.info(Objects.toString(logObject));
		} else {
			System.out.println(Objects.toString(logObject));
		}
	}

	public static String getErrorMessage(Throwable ex) {
		return getErrorMessage(ex, null);
	}

	public static String getErrorMessage(Throwable ex, String aditionalErrorMessage) {
		if (ex == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		if (aditionalErrorMessage != null) {
			sb.append(aditionalErrorMessage).append("  -  ");
		}
		if (ex.getMessage() != null) {
			sb.append(ex.getMessage());
		}
		if (ex.getCause() != null) {
			System.err.println("Exception Cause: " + Objects.toString(ex.getCause()));
			if (!sb.toString().trim().isEmpty()) {
				sb.append(" ; Cause: ");
			}
			sb.append(ex.getCause());
		}
		return sb.toString();
	}

}
