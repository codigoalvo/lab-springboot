package codigoalvo.lab.springboot.type;

public interface Tipo<T extends Tipo, K, V> {

	T[] getAll();

	K getKey();

	V getValue();

	T getDefault();

	default T getByKey(K key) {
		key = trimIgnoreCaseKey(key);
		for (Tipo tipo : getAll()) {
			K currentKey = trimIgnoreCaseKey((K) tipo.getKey());
			if (currentKey.equals(key)) {
				return (T) tipo;
			}
		}
		return getDefault();
	}

	default T getByValue(V value) {
		value = trimIgnoreCaseValue(value);
		for (Tipo tipo : getAll()) {
			V currentValue = trimIgnoreCaseValue((V) tipo.getValue());
			if (currentValue.equals(value)) {
				return (T) tipo;
			}
		}
		return getDefault();
	}

	default K trimIgnoreCaseKey(K key) {
		if (key != null && key instanceof String) {
			return (K) ((String) key).trim().toLowerCase();
		}
		return key;
	}

	default V trimIgnoreCaseValue(V value) {
		if (value != null && value instanceof String) {
			return (V) ((String) value).trim().toLowerCase();
		}
		return value;
	}

}
