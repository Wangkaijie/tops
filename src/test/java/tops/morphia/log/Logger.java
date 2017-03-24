package tops.morphia.log;

public interface Logger {
	 void  debug(String msg);
	 void  debug(String msg,Object ...arg);
	 void  debug(String msg,Throwable t);

	 void error(String msg);
	 void error(String mag,Object...arg);
	 void error(String msg,Throwable t);

	 void info(String msg);
	 void info(String msg,Object...arg);
	 void info(String mag,Throwable t);

	 boolean isDebugEnabled();
	 boolean isErrorEnabled();
	 boolean isInfoEnabled();
	 boolean isTraceEnabled();
	 boolean isWrningEnabled();
	 void trace(String msg);
	 void trace(String msg,Object...arg);
	 void trace(String msg,Throwable t);

	 void warning(String msg);
	 void warning(String msg,Object...arg);
	 void warning(String mag,Throwable t);
}
