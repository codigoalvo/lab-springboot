package codigoalvo.lab.springboot.util;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Slf4j
public class StringHelper {

	public static final String LINE_BREAK = "\r\n";

	private static final String TEST_MSG_PREFIX = "__TEST__ ";

	public static String toJson(Object object) {
		return toJson(object, null);
	}

	public static String toJson(Object object, Module module) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			if (module != null) {
				mapper.registerModule(module);
			}
			return mapper.writeValueAsString(object);
		} catch (IOException e) {
			e.printStackTrace();
			return Objects.toString(object);
		}
	}

	public static String fillZeros(Number number, int numberSize) {
		number = number == null ? 0 : number;
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumIntegerDigits(numberSize);
		nf.setMinimumIntegerDigits(numberSize);
		nf.setGroupingUsed(false);
		return nf.format(number);
	}

	public static int safeIntParse(String number) {
		try {
			return Integer.parseInt(number);
		} catch (Throwable ex) {
			return 0;
		}
	}

	public static long safeLongParse(String number) {
		try {
			return Long.parseLong(number);
		} catch (Throwable ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	public static BigInteger safeBigIntegerParse(String number) {
		try {
			return number == null ? BigInteger.ZERO : new BigInteger(number);
		} catch (Throwable ex) {
			return BigInteger.ZERO;
		}
	}

	public static long flatBigDecimalToLong(BigDecimal bdNumber) {
		return flatBigDecimalToLong(bdNumber, false);
	}

	public static long flatBigDecimalToLong(BigDecimal bdNumber, boolean safe) {
		if (bdNumber == null) {
			return safe ? 0l : null;
		}
		try {
			return bdNumber.movePointRight(bdNumber.scale()).longValue();
		} catch (Throwable ex) {
			if (safe) {
				return 0l;
			}
			throw ex;
		}
	}

	public static BigDecimal toBigDecimalSafe(String flatStrNumber) {
		return toBigDecimal(flatStrNumber, 2, true);
	}

	public static BigDecimal toBigDecimal(String flatStrNumber, int decimalDigits, boolean safe) {
		try {
			return flatStrNumber == null ? (safe ? BigDecimal.ZERO : null) : new BigDecimal(flatStrNumber).movePointLeft(decimalDigits);
		} catch (Throwable ex) {
			if (safe) {
				return BigDecimal.ZERO;
			}
			throw ex;
		}
	}

	/**
	 * Uses "ddMMyyyy" as default format for this project
	 *
	 * @param strDate
	 * @return LocalDate
	 */
	public static LocalDate safeDateParse(String strDate) {
		return safeDateParse(strDate, "ddMMyyyy");
	}

	public static LocalDate safeDateParse(String strDate, String format) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
			return LocalDate.parse(strDate, formatter);
		} catch (Throwable ex) {
			return (LocalDate) null;
		}
	}

	public static String hashMonthParse(int month) {
		if (!isBetween(month, 1, 12)) {
			throw new IllegalArgumentException("Mês deve ser de 1 à 12");
		}
		if (isBetween(month, 1, 9)) {
			return "" + month;
		}
		return month == 10 ? "A" : (month == 11 ? "B" : (month == 12 ? "C" : null));
	}

	public static boolean isBetween(int x, int lower, int upper) {
		return lower <= x && x <= upper;
	}

	public static String getTestMessage(Class classe, Object esperado, Object resultado, boolean log) {
		StringBuilder message = new StringBuilder(TEST_MSG_PREFIX);
		message.append(classe.getSimpleName()).append(".");
		message.append(StringHelper.getCurrentClassAndMethod());
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

	public BigDecimal toBigDecimal(String flatStrNumber) {
		return toBigDecimal(flatStrNumber, 2);
	}

	public BigDecimal toBigDecimal(String flatStrNumber, int decimalDigits) {
		return toBigDecimal(flatStrNumber, decimalDigits, false);
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
			System.err.println("Exception Cause: "+Objects.toString(ex.getCause()));
			if (!sb.toString().trim().isEmpty()) {
				sb.append(" ; Cause: ");
			}
			sb.append(ex.getCause());
		}
		return sb.toString();
	}

}
