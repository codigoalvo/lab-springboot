package codigoalvo.lab.springboot.util;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

@Slf4j
public class JsonUtil {

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

}
