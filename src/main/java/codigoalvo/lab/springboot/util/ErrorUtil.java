package codigoalvo.lab.springboot.util;

import java.util.Objects;

public class ErrorUtil {

	public static String getErrorMessage(Exception ex) {
		if (ex == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		if (ex.getMessage() != null) {
			sb.append(ex.getMessage());
		}
		if (ex.getCause() != null) {
			System.err.println("Exception Cause: "+Objects.toString(ex.getCause()));
			if (!sb.toString().trim().isEmpty()) {
				sb.append(" ; Cause: ");
			}
			sb.append(ex.getCause());
		}
		return sb.toString();
	}
}
