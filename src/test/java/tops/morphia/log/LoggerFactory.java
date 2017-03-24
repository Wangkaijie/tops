package tops.morphia.log;

public interface LoggerFactory {
	Logger get(Class<?> c);
}
